/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import daos.ArticlesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class ChangeStatusAdminController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "user/createArticle.jsp";
    private static final String BLOG = "index.jsp";
    private static final String LOGIN = "login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE") + "";
            String activate = (String) session.getAttribute("Activate") + "";
            if (role.equals("Admin") && activate.equals("Actived")) {
                String index = request.getParameter("input");
                String status = request.getParameter("status");
                List<String> list = new ArrayList<String>();
                StringTokenizer st = new StringTokenizer(index.trim(), ";");
                while (st.hasMoreTokens()) {
                    list.add(st.nextToken());
                }
                Gson gson = new Gson();
                ArticlesDAO articlesDAO = new ArticlesDAO();
                for (String item : list) {
                    articlesDAO.changeStatus(Integer.parseInt(item.trim()), status.trim());
                }
                out.print(gson.toJson("successful"));
            } else {
                response.sendRedirect(LOGIN);
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Some thing wrong, try again");
            response.sendRedirect("../error.jsp");
            log("Error at ActiveArticlesAdminController: " + e.getMessage());
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
