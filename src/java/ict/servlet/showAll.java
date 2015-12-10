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
@WebServlet(name = "showAll", urlPatterns = {"/ShowAll"})
public class showAll extends HttpServlet {

    private DB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new DB(dbUrl, dbUser, dbPassword);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ShowAll(request, response);
    }

    private void ShowAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    

        // redirect the result to the listCustomers.jsp
        RequestDispatcher rd;

        ArrayList<ProductBean> product = db.queryProduct();
        request.setAttribute("product", product);

        ArrayList<ProductOrderBean> productOrder = db.queryProductOrder();
        request.setAttribute("productOrder", productOrder);

        ArrayList<OrderBean> order = db.queryOrder();
        request.setAttribute("order", order);

        ArrayList<CategoryBean> category = db.queryCategory();
        request.setAttribute("category", category);

        ArrayList<ManufacturerBean> manufacturer = db.queryManufacturer();
        request.setAttribute("manufacturer", manufacturer);

        ArrayList<UserBean> user = db.queryUser();
        request.setAttribute("user", user);

        ArrayList<GiftBean> gift = db.queryGift();
        request.setAttribute("gift", gift);

        

        rd = getServletContext().getRequestDispatcher("/welcome.jsp");
        rd.forward(request, response);

    }

}
