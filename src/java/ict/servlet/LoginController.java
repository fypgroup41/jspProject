/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.CategoryBean;
import ict.bean.GiftBean;
import ict.bean.ManufacturerBean;
import ict.bean.OrderBean;
import ict.bean.ProductBean;
import ict.bean.ProductOrderBean;
import ict.bean.UserBean;
import ict.bean.UserInfo;
import ict.db.DB;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author user
 */
@WebServlet(name = "LoginController", urlPatterns = {"/main"})
public class LoginController extends HttpServlet {

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

        try {
            String action = request.getParameter("action");
            if (!isAuthenticated(request)
                    && !("authenticate".equals(action))) {
                doLogin(request, response);
                return;
            }
            if ("authenticate".equals(action)) {
                doAuthenticate(request, response);
            } else if ("logout".equals(action)) {
                doLogout(request, response);
                return;
            } else {
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
            }
        } catch (Exception e) {

            request.getRequestDispatcher("/login.jsp").forward(request, response);
            e.printStackTrace();

        }
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        boolean result = false;
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfo") != null) {
            result = true;
        }
        return result;
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String targetURL = "login/login.jsp";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("userInfo");
            session.removeAttribute("list");
            session.invalidate();
        }
        doLogin(request, response);
    }

    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String uid = request.getParameter("username");
            String password = request.getParameter("password");
            String targetURL;
            boolean isValid = db.isValidUser(uid, password);
            if (isValid) {
                HttpSession session = request.getSession(true);
                session.setMaxInactiveInterval(9999);
                UserBean bean = db.queryUserByUID(uid);
                session.setAttribute("userInfo", bean);

                // redirect the result to the listCustomers.jsp
                RequestDispatcher rd;

                //ArrayList<CategoryBean> category = db.queryCategory();
                //session.setAttribute("category", category);
          /*      if (bean.getuType().equals("a")) {
                 rd = getServletContext().getRequestDispatcher("/maintain.jsp");
                 rd.forward(request, response);
                 } else {
                 rd = getServletContext().getRequestDispatcher("/welcome.jsp");
                 rd.forward(request, response);
                 }*/
                rd = getServletContext().getRequestDispatcher("/welcome.jsp");
                rd.forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("Login Fail! Prease try again");
            }
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Login Fail! Prease try again");
        }
    }

}
