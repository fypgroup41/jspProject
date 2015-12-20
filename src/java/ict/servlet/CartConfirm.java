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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CartConfirm", urlPatterns = {"/confirmCart"})
public class CartConfirm extends HttpServlet {

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
        String cardType = request.getParameter("cardType");
        String cardNo = request.getParameter("cardNo");
        String holder = request.getParameter("holder");
        String expiryDate = request.getParameter("expiryDate");
        String password = request.getParameter("password");
        String receive = request.getParameter("receive");
        String ReceiveDate = request.getParameter("ReceiveDate");
        String ReceiveTime = request.getParameter("ReceiveTime");
        HttpSession session = request.getSession(true);
        UserBean user = (UserBean) session.getAttribute("userInfo");
        int check = 0;
        String error = "";
        cardNo = cardNo.replaceAll("\\s", "");
        if (cardNo.length() == 16) {
            for (int i = 0; i < cardNo.length(); i++) {
                if (cardNo.charAt(i) < '0' && cardNo.charAt(i) > '9') {
                    check++;
                    error += " \n Card Number Format is not correct<br>";
                }
            }
        } else {
            check += 16 - cardNo.length();
            error += " \n Card Number Format is not correct<br>";
        }

        if (password.length() == 4) {
            for (int i = 0; i < password.length(); i++) {
                if (cardNo.charAt(i) < '0' && password.charAt(i) > '9') {
                    check++;
                    error += " \n Card Security Code is not correct<br>";
                }
            }
        } else {
            check += 4 - password.length();
            error += " \n Card Security Code is not correct<br>";
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("Error  : " + check);
        out.println("cardType : " + cardType);
        out.println("holder  : " + holder);
        out.println("holder  : " + ReceiveDate);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        if (check == 0) {
            String nextOrder = db.getNextPk("o", "oid", "product_order", 1, 4);
            if (receive.equals("delivery")) {
                receive = "Delivery";
            }
            if (receive.equals("pickUp")) {
                receive = "Self-Pick-Up";
            }
            try {
                db.addOrderRecord(nextOrder, user.getuId(), receive, "Process", ReceiveDate);
            } catch (ParseException ex) {
                Logger.getLogger(CartConfirm.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (session.getAttribute("productSession") != null) {
                ProductCartListBean cartList = (ProductCartListBean) session.getAttribute("productSession");
                ArrayList<ProductCartBean> productCart = (ArrayList<ProductCartBean>) cartList.getList();
                double total = 0;
                for (int i = 0; i < productCart.size(); i++) {
                    db.addProductOrderRecord(nextOrder, productCart.get(i).getPb().getpId(), null, productCart.get(i).getQty(), productCart.get(i).getPb().getPrice());
                    total += (productCart.get(i).getPb().getPrice() * productCart.get(i).getQty());

                }
                int bouns = (int) Math.floor((total / 1000) * 100);
                user.setBounspt(bouns);
                db.editUserRecord(user);

            }
            int total = 0;
            if (session.getAttribute("giftSession") != null) {
                GiftCartListBean cartList2 = (GiftCartListBean) session.getAttribute("giftSession");
                ArrayList<GiftCartBean> giftCart = (ArrayList<GiftCartBean>) cartList2.getList();
                for (int i = 0; i < giftCart.size(); i++) {
                    db.addProductOrderRecord(nextOrder, null, giftCart.get(i).getGb().getCatId(), giftCart.get(i).getQty(), giftCart.get(i).getGb().getPt());
                    total += giftCart.get(i).getQty() * giftCart.get(i).getGb().getPt();
                }

            }

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/shoppingCart/success.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("error", error);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/shoppingCart/fail.jsp");
            rd.forward(request, response);
        }

    }

}
