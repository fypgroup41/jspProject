/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.ProductCartListBean;
import ict.bean.CategoryBean;
import ict.bean.GiftBean;
import ict.bean.GiftCartBean;
import ict.bean.GiftCartListBean;
import ict.bean.ProductCartBean;
import ict.bean.ManufacturerBean;
import ict.bean.OrderBean;
import ict.bean.ProductBean;
import ict.bean.ProductOrderBean;

import ict.bean.UserBean;
import ict.bean.UserInfo;
import ict.db.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author test
 */
@WebServlet(name = "UpdateUser", urlPatterns = {"/updateUser"})
public class HandleUser extends HttpServlet {

    private DB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new DB(dbUrl, dbUser, dbPassword);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputType = request.getParameter("inputType");

        if (inputType.equals("info")) {
            String uId = request.getParameter("uId");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String deliveryAddress = request.getParameter("deliveryAddress");
            UserBean user = db.queryUserByUID(uId);
            user.setuName(username);
            user.setDeliveryAddress(deliveryAddress);
            user.setEmail(email);
            PrintWriter out = response.getWriter();
            out.println(user.getDeliveryAddress());
            db.editUserRecord(user);
            HttpSession session = request.getSession(true);
            session.setAttribute("userInfo", user);

            response.sendRedirect(request.getContextPath() + ("/editUser.jsp"));
        }
        if (inputType.equals("password")) {
            String uId = request.getParameter("uId");
            String password = request.getParameter("password");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");

            UserBean user = db.queryUserByUID(uId);
            if (password1.equals(password2) && password.equals(user.getuPasswd())) {
                user.setuPasswd(password);
                db.editUserRecord(user);
                HttpSession session = request.getSession(true);
                session.setAttribute("userInfo", user);
                PrintWriter out = response.getWriter();
                out.println("Reset Password OK");
            } else {
                PrintWriter out = response.getWriter();
                out.println("Reset Password FAIL");
            }

        }

    }

}
