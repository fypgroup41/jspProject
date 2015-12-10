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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Exception/Error Details</title></head><body>");
        if (statusCode != 500) {
            out.write("<h3>Error Details</h3>");
            out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong>Requested URI</strong>:" + requestUri);
        } else {
            out.write("<h3>Exception Details</h3>");
            out.write("<ul><li>Servlet Name:" + servletName + "</li>");
            out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
            out.write("<li>Requested URI22:" + requestUri + "</li>");
            out.write("<li>Exception Message:" + throwable.getMessage() + "</li>");
            out.write("</ul>");
        }

        out.write("<br><br>");
        out.write("<a href=\"index.html\">Home Page</a>");
        out.write("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /*try {
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
         } catch (Exception ex) {*/
        // Analyze the servlet exception
        try {
            Throwable throwable = (Throwable) request
                    .getAttribute("javax.servlet.error.exception");
            Integer statusCode = (Integer) request
                    .getAttribute("javax.servlet.error.status_code");
            String servletName = (String) request
                    .getAttribute("javax.servlet.error.servlet_name");
            if (servletName == null) {
                servletName = "Unknown";
            }
            String requestUri = (String) request
                    .getAttribute("javax.servlet.error.request_uri");
            if (requestUri == null) {
                requestUri = "Unknown";
            }

            // Set response content type
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.write("<html><head><title>Exception/Error Details</title></head><body>");
            if (statusCode != 500) {
                out.write("<h3>Error Details</h3>");
                out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
                out.write("<strong>Requested URI</strong>:" + requestUri);
            } else {
                out.write("<h3>Exception Details</h3>");
                out.write("<ul><li>Servlet Name:" + servletName + "</li>");
                out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
                out.write("<li>Requested URI33:" + requestUri + "</li>");
                out.write("<li>Exception Message:" + throwable.getMessage() + "</li>");
                out.write("</ul>");
            }

            out.write("<br><br>");
            out.write("<a href=\"index.html\">Home Page</a>");
            out.write("</body></html>");
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.write("<a href=\"index.html\">Home Page</a>");
        }

    }

}
