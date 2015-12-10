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
@WebServlet(name = "ShoppingCart", urlPatterns = {"/Cart"})
public class HandleShoppingCart extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        ArrayList<CategoryBean> category = db.queryCategory();
        session.setAttribute("category", category);

        String action = request.getParameter("action");
        if (action.equals("confirm")) {

            UserBean ub = (UserBean) session.getAttribute("userInfo");
            int bounspt = ub.getBounspt();
            if (session.getAttribute("giftSession") != null) {
                GiftCartListBean cartList = (GiftCartListBean) session.getAttribute("giftSession");
                int cartBounspt = 0;
                ArrayList< GiftCartBean> gcb = cartList.getList();
                for (int i = 0; i < gcb.size(); i++) {
                    cartBounspt += gcb.get(i).getGb().getPt() * gcb.get(i).getQty();
                }
                if (bounspt > cartBounspt) {
                    response.sendRedirect("confirmOrder.jsp");
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("Not Enough Bouns Point to buy gifts");
                }

            } else {
                response.sendRedirect("confirmOrder.jsp");
            }

        }
        if (action.equals("update")) {
            String item = request.getParameter("item");
            if (item.equals("product")) {
                String pid = request.getParameter("pid");
                int qty = Integer.parseInt(request.getParameter("qty"));
                if (session.getAttribute("productSession") != null) {
                    ProductCartListBean cartList = (ProductCartListBean) session.getAttribute("productSession");
                    if (cartList.updateProduct(pid, qty)) {
                        session.setAttribute("productSession", cartList);
                        response.sendRedirect(request.getContextPath() + ("/shoppingCart/shoppingCart.jsp"));
                    }

                }
            }
            if (item.equals("gift")) {

                String gid = request.getParameter("gid");
                int qty = Integer.parseInt(request.getParameter("qty"));

                if (session.getAttribute("giftSession") != null) {
                    GiftCartListBean cartList = (GiftCartListBean) session.getAttribute("giftSession");
                    if (cartList.updateProduct(gid, qty)) {
                        session.setAttribute("giftSession", cartList);
                        response.sendRedirect(request.getContextPath() + ("/shoppingCart/shoppingCart.jsp"));
                    }

                }
            }

        }
        if (action.equals("delete")) {

            String item = request.getParameter("item");
            if (item.equals("product")) {
                String pid = request.getParameter("pid");

                if (session.getAttribute("productSession") != null) {
                    ProductCartListBean cartList = (ProductCartListBean) session.getAttribute("productSession");
                    if (cartList.deleteProduct(pid)) {
                        response.sendRedirect(request.getContextPath() + ("/shoppingCart.jsp"));
                        if (cartList.getList().isEmpty()) {
                            session.removeAttribute("productSession");
                        } else {
                            session.setAttribute("productSession", cartList);
                        }
                    }

                }
            }
            if (item.equals("gift")) {

                String gid = request.getParameter("gid");

                if (session.getAttribute("giftSession") != null) {
                    GiftCartListBean cartList = (GiftCartListBean) session.getAttribute("giftSession");
                    if (cartList.deleteProduct(gid)) {
                        response.sendRedirect(request.getContextPath() + ("/shoppingCart/shoppingCart.jsp"));
                        if (cartList.getList().isEmpty()) {
                            session.removeAttribute("giftSession");
                        } else {
                            session.setAttribute("giftSession", cartList);
                        }
                    }

                }
            }

        }
        if (action.equals("add")) {
            String item = request.getParameter("item");

            /*    if (session.getAttribute("userInfo") != null) {
             response.sendRedirect(request.getContextPath() + ("/index.jsp"));
             return;
             }*/
            if (item.equals("product")) {
                String pid = request.getParameter("pid");
                String qty = request.getParameter("qty");
                ProductBean product = db.queryProductByID(pid);

                if (session.getAttribute("productSession") != null) {
                    ProductCartBean productCart = new ProductCartBean();
                    productCart.setPb(product);
                    productCart.setQty(Integer.parseInt(qty));
                    ProductCartListBean cartList = (ProductCartListBean) session.getAttribute("productSession");
                    cartList.addList(productCart);
                    session.setAttribute("productSession", cartList);
                } else {
                    ProductCartBean bean = new ProductCartBean();
                    bean.setPb(product);
                    bean.setQty(Integer.parseInt(qty));
                    ProductCartListBean cartList = new ProductCartListBean();
                    cartList.addList(bean);
                    session.setAttribute("productSession", cartList);
                }
            }
            if (item.equals("gift")) {
                String gid = request.getParameter("gid");
                String qty = request.getParameter("qty");
                GiftBean gift = db.queryGiftById(gid);
                if (session.getAttribute("giftSession") != null) {
                    GiftCartBean giftCart = new GiftCartBean();
                    giftCart.setGb(gift);
                    giftCart.setQty(Integer.parseInt(qty));
                    GiftCartListBean cartList = (GiftCartListBean) session.getAttribute("giftSession");
                    cartList.addList(giftCart);
                    session.setAttribute("giftSession", cartList);
                } else {
                    GiftCartBean giftCart = new GiftCartBean();
                    giftCart.setGb(gift);
                    giftCart.setQty(Integer.parseInt(qty));
                    GiftCartListBean cartList = new GiftCartListBean();
                    cartList.addList(giftCart);
                    session.setAttribute("giftSession", cartList);
                }
            }

            response.sendRedirect(request.getContextPath() + ("/shoppingCart/shoppingCart.jsp"));
        }
    }

}
