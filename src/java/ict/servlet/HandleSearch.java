/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.GiftBean;
import ict.bean.ManufacturerBean;
import ict.bean.ProductBean;
import ict.bean.ProductManufacurerBean;
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

/**
 *
 * @author Anson
 */
@WebServlet(name = "HandleSearch", urlPatterns = {"/HandleSearch"})
public class HandleSearch extends HttpServlet {

    private DB db;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new DB(dbUrl, dbUser, dbPassword);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("searchAll")) {
            String item = request.getParameter("item");
            if (item.equals("product")) {
                ArrayList<ProductBean> products = db.queryProduct();
                ArrayList<ManufacturerBean> manufacturer = new ArrayList<ManufacturerBean>();
                for (int i = 0; i < products.size(); i++) {
                    manufacturer.add(db.queryManufacturerByMID(products.get(i).getmId()));
                }

                ProductManufacurerBean productManufacurer = new ProductManufacurerBean();
                productManufacurer.setManufacturer(manufacturer);
                productManufacurer.setProducts(products);
                request.setAttribute("productManufacurer", productManufacurer);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/search/searchResult.jsp");
                rd.forward(request, response);
            }
            if (item.equals("gift")) {
                ArrayList<GiftBean> gift = db.queryGift();
                ArrayList<ManufacturerBean> manufacturer2 = new ArrayList<ManufacturerBean>();
                for (int i = 0; i < gift.size(); i++) {
                    manufacturer2.add(db.queryManufacturerByMID(gift.get(i).getmId()));
                }

                ProductManufacurerBean productManufacurer2 = new ProductManufacurerBean();
                productManufacurer2.setManufacturer(manufacturer2);
                productManufacurer2.setGifts(gift);
                request.setAttribute("productManufacurer2", productManufacurer2);

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/search/searchResult.jsp");
                rd.forward(request, response);
            }
        } else if (action.equalsIgnoreCase("searchByCatid")) {
            String condition = request.getParameter("catid");
            //String order = request.getParameter("order");
            ArrayList<ProductBean> products = db.queryProductByCatID(condition);
            ArrayList<ManufacturerBean> manufacturer = new ArrayList<ManufacturerBean>();
            for (int i = 0; i < products.size(); i++) {
                manufacturer.add(db.queryManufacturerByMID(products.get(i).getmId()));
            }
            ProductManufacurerBean productManufacurer = new ProductManufacurerBean();
            productManufacurer.setManufacturer(manufacturer);
            productManufacurer.setProducts(products);
            request.setAttribute("productManufacurer", productManufacurer);
            request.setAttribute("products", products);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/search/searchResult.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("searchByPrice")) {
            double condition = Double.parseDouble(request.getParameter("price"));
            String order = request.getParameter("order");
            ArrayList<ProductBean> products = db.queryProductByPrice(condition, order);
            ArrayList<ManufacturerBean> manufacturer = new ArrayList<ManufacturerBean>();
            for (int i = 0; i < products.size(); i++) {
                manufacturer.add(db.queryManufacturerByMID(products.get(i).getmId()));
            }
            ProductManufacurerBean productManufacurer = new ProductManufacurerBean();
            productManufacurer.setManufacturer(manufacturer);
            productManufacurer.setProducts(products);
            request.setAttribute("productManufacurer", productManufacurer);
            request.setAttribute("products", products);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/search/searchResult.jsp");
            rd.forward(request, response);
        } else if (action.equalsIgnoreCase("searchByKeywords")) {
            ArrayList<ProductBean> products;
            String condition = request.getParameter("product");
            if (condition != null) {
                String keywords = request.getParameter("product");
                String price = request.getParameter("price");
                if (condition.equalsIgnoreCase("")) {
                    products = new ArrayList<ProductBean>();
                } else {
                    products = db.queryProductByKeywordPrice(keywords, Double.parseDouble(price));
                }

                ArrayList<ManufacturerBean> manufacturer = new ArrayList<ManufacturerBean>();
                for (int i = 0; i < products.size(); i++) {
                    manufacturer.add(db.queryManufacturerByMID(products.get(i).getmId()));
                }
                ProductManufacurerBean productManufacurer = new ProductManufacurerBean();
                productManufacurer.setManufacturer(manufacturer);
                productManufacurer.setProducts(products);
                request.setAttribute("productManufacurer", productManufacurer);

            }
            condition = request.getParameter("gift");
            if (condition != null) {
                ArrayList<GiftBean> gifts;

                if (condition.equalsIgnoreCase("")) {
                    gifts = new ArrayList<GiftBean>();
                } else {
                    gifts = db.queryGiftByKeyword(condition);
                }
                ArrayList<ManufacturerBean> manufacturer2 = new ArrayList<ManufacturerBean>();
                for (int i = 0; i < gifts.size(); i++) {
                    manufacturer2.add(db.queryManufacturerByMID(gifts.get(i).getmId()));
                }
                ProductManufacurerBean productManufacurer2 = new ProductManufacurerBean();
                productManufacurer2.setManufacturer(manufacturer2);
                productManufacurer2.setGifts(gifts);
                request.setAttribute("productManufacurer2", productManufacurer2);

            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/search/searchResult.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
