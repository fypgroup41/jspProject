/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.*;
import ict.db.DB;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author kwok1
 */
@WebServlet(name = "HandleGift", urlPatterns = {"/handleGift"})
@MultipartConfig
public class HandleGift extends HttpServlet {

    private DB db;
    private String deleted = "";
    
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
            ArrayList<GiftBean> gift = db.queryGift();
            request.setAttribute("gift", gift);
            request.setAttribute("deleted", deleted);
            RequestDispatcher rd;
            deleted = "";
            rd = getServletContext().getRequestDispatcher("/listGiftm.jsp");
            rd.forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            String[] id = request.getParameterValues("gid");
            /* PrintWriter out = response.getWriter();
                    out.println(id.length);*/
            for (int i = 0; i < id.length; i++) {
                GiftBean pb = db.queryGiftByID(id[i]);
                if (db.delRecord("Gift", id[i])) {
                    if (i == id.length - 1) {
                        try {
                            ServletContext servletContext = request.getSession().getServletContext();
                            File file = new File(getPath(servletContext), pb.getGiftPhoto());
                            if (file.delete()) {
                                PrintWriter out = response.getWriter();
                                deleted += "<br/>" + pb.getgId() + " is deleted!";

                            } else {
                                PrintWriter out = response.getWriter();
                                deleted = "Delete operation is failed.";

                            }

                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                        response.sendRedirect("handleGift?action=list");
                    }
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("No such Gift");
                }
            }
        } else if ("ledit".equalsIgnoreCase(action)) {
            String gId = "";
            gId = request.getParameter("gId");

            if (!gId.equals("")) {
                GiftBean pb = db.queryGiftByID(gId);
                request.setAttribute("pb", pb);
            } else {
                GiftBean pb = new GiftBean();
                request.setAttribute("pb", pb);
            }
            ArrayList<CategoryBean> cb = db.queryCategory();
            request.setAttribute("cb", cb);
            ArrayList<ManufacturerBean> mb = db.queryManufacturer();
            request.setAttribute("mb", mb);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/editGift.jsp");
            rd.forward(request, response);
            /*PrintWriter out = response.getWriter();
                out.println("99999");*/
        } else if ("edit".equalsIgnoreCase(action)) {
            String pId = request.getParameter("pId");
            String pName = request.getParameter("pName");
            String description = request.getParameter("description");
            String mId = request.getParameter("mId");
            String cId = request.getParameter("cId");
            double pt = Double.parseDouble(request.getParameter("pt"));
            int stockQty = Integer.parseInt(request.getParameter("stockQty"));
            Part part = request.getPart("image");
            String fileName = part.getSubmittedFileName();
            String[] fname = fileName.split("\\.");
            String sfileName = pId + "." + fname[fname.length - 1];
            InputStream fileContent = part.getInputStream();

            ServletContext servletContext = request.getSession().getServletContext();
            File targetFile = new File(getPath(servletContext), sfileName);

            OutputStream outStream = new FileOutputStream(targetFile);

            int i = 0;
		
		byte[] buffer = new byte[512];
		while(true) {
			if(fileContent.available() < 512) {
				while(i != -1) {
					i = fileContent.read();
					outStream.write(i);
				}
				break;
			} else {
				fileContent.read(buffer);
				outStream.write(buffer);
			}
		}

		fileContent.close();
		outStream.close();

            
            GiftBean cb = new GiftBean(pId, pName, pt, description, cId, stockQty, sfileName, mId);
            if (db.editGiftRecord(cb)) {
                response.sendRedirect("handleGift?action=list");
            } else {
                PrintWriter out = response.getWriter();
                out.println("No such ID!");
            }
        } else if ("add".equalsIgnoreCase(action)) {
            String pId = request.getParameter("pId");
            String pName = request.getParameter("pName");
            String description = request.getParameter("description");
            String mId = request.getParameter("mId");
            String cId = request.getParameter("cId");
            double pt = Double.parseDouble(request.getParameter("pt"));
            int stockQty = Integer.parseInt(request.getParameter("stockQty"));
            Part part = request.getPart("image");
            String fileName = part.getSubmittedFileName();
            String[] fname = fileName.split("\\.");
            String sfileName = pId + "." + fname[fname.length - 1];
            
            InputStream fileContent = part.getInputStream();

            ServletContext servletContext = request.getSession().getServletContext();
            File targetFile = new File(getPath(servletContext), sfileName);

            OutputStream outStream = new FileOutputStream(targetFile);

            int i = 0;
		
		byte[] buffer = new byte[512];
		while(true) {
			if(fileContent.available() < 512) {
				while(i != -1) {
					i = fileContent.read();
					outStream.write(i);
				}
				break;
			} else {
				fileContent.read(buffer);
				outStream.write(buffer);
			}
		}

		fileContent.close();
		outStream.close();
            
            if (db.addGiftRecord(pId, pName, pt, description, cId, stockQty, sfileName, mId)) {
                response.sendRedirect("handleGift?action=list");
            } else {
                PrintWriter out = response.getWriter();
                out.println("No correct input!!");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

    public String getPath(ServletContext servletContext) {

        String absolutePathToIndexJSP = servletContext.getRealPath("/img");
        String[] apti = (absolutePathToIndexJSP.replace("build\\", "")).split("");
        String aptijsp = "";
        for (String d : apti) {
            if (d.equals("\\")) {
                aptijsp += "\\\\";
            } else {
                aptijsp += d;
            }
        }
        return aptijsp;
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
