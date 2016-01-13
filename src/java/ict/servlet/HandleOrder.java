/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.*;
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
 * @author kwok1
 */
@WebServlet(name = "HandleOrder", urlPatterns = {"/handleOrder"})
public class HandleOrder extends HttpServlet {

    private DB db;
    private String updated="";

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
        if ("list".equalsIgnoreCase(action)) {
            ArrayList<OrderBean> order = db.queryOrder();
            request.setAttribute("order", order);
            ArrayList<String> om = new ArrayList<String>();
            om.add("Self-Pick-Up");
            om.add("Delivery");
            request.setAttribute("om", om);
            ArrayList<String> os = new ArrayList<String>();
            os.add("Process");
            os.add("Cancel");
            os.add("Delivered");
            os.add("Picked-Up");
            request.setAttribute("os", os);
            request.setAttribute("updated", updated);
            RequestDispatcher rd;
            updated="";
            rd = getServletContext().getRequestDispatcher("pong/listOrder.jsp");
            rd.forward(request, response);
        } else if ("details".equalsIgnoreCase(action)) {
            String id = request.getParameter("oId");
            if (!"".equals(id)) {
                ArrayList<ProductOrderBean> pod = new ArrayList<ProductOrderBean>();
                pod = db.queryProductOrderByID(id);
                request.setAttribute("pod", db.queryProductOrderByID(id));
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("pong/listOrderDetails.jsp");
                rd.forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("No such Order");
            }
        }else if ("update".equalsIgnoreCase(action)) {
            String oId = request.getParameter("oId");
            String uId = request.getParameter("uId");
            String oMode = request.getParameter("oMode");
            String oStatus = request.getParameter("oStatus");
            String oDate = request.getParameter("oDate");
            String deliveryDate = request.getParameter("deliveryDate");
            OrderBean ob = new OrderBean(oId, uId, oMode, oStatus,oDate,deliveryDate);
            /*PrintWriter out = response.getWriter();
            out.println(db.editOrderRecord(ob));*/
            
            if (db.editOrderRecord(ob)) {
                updated =oId+ " is updated.";
                response.sendRedirect("handleOrder?action=list");
                
            } else {
                PrintWriter out = response.getWriter();
                out.println("No such ID!");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
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
