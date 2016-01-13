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
@WebServlet(name = "HandleUser", urlPatterns = {"/handleUser1"})
public class HandleUser1 extends HttpServlet {

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
            //PrintWriter out = response.getWriter();
            ArrayList<UserBean> user = new ArrayList<UserBean>();
            user = db.queryUser();
            //out.println(user.size());
            
            request.setAttribute("user", user);
            
            ArrayList<String> yn = new ArrayList<String>();
            yn.add("true");
            yn.add("false");
            request.setAttribute("yn", yn);
            request.setAttribute("updated", updated);
            RequestDispatcher rd;
            updated="";
            rd = getServletContext().getRequestDispatcher("pong/listUser.jsp");
            rd.forward(request, response);
        } else if ("update".equalsIgnoreCase(action)) {
            
            String uId = request.getParameter("uId");
            UserBean user = db.queryUserById(uId);
            String uName = request.getParameter("uName");
            String type = request.getParameter("type");
            String email = request.getParameter("email");
            String deliveryAddress = request.getParameter("deliveryAddress");
            
            int creditAmount = Integer.parseInt(request.getParameter("creditAmount"));
            int bounspt = Integer.parseInt(request.getParameter("bounspt"));
            int available = Integer.parseInt(request.getParameter("available"));
            int a_f_t = Integer.parseInt(request.getParameter("a_f_t"));
            int transactions = Integer.parseInt(request.getParameter("transactions"));
            double total_amount = Double.parseDouble(request.getParameter("total_amount"));
            PrintWriter out = response.getWriter();
            
            UserBean ob = new UserBean(uId, uName, user.getuPasswd(), type, email,creditAmount,bounspt
                    , available, deliveryAddress, a_f_t, 
                    transactions, total_amount);
            
            
            out.println(ob.getuId()+"|"+uName+"|"+type+"|"+email+"|"+ob.getDeliveryAddress()+"|"+creditAmount+"|"+bounspt+"|"+available+"|"+a_f_t+"|"+transactions+"|"+total_amount+"|");
            
            if (db.editUserRecord(ob)) {
                updated =uId+ " is updated.";
                response.sendRedirect("handleUser1?action=list");
                
            } else {
                //PrintWriter out = response.getWriter();
                out.println("No such ID!");
            }
        }else {
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
