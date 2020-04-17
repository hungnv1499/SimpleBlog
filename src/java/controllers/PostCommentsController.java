/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import daos.ArticlesDAO;
import daos.CommentsDAO;
import daos.RegistrationDAO;
import entities.Articles;
import entities.Comments;
import entities.Registrations;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class PostCommentsController extends HttpServlet {

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
            if (activate.equals("Actived")) {
                Gson gson = new Gson();
                String userId = request.getParameter("userId").trim();
                int articleId = Integer.parseInt(request.getParameter("articleId").trim());
                String message = request.getParameter("message").trim();
                ArticlesDAO articlesDAO = new ArticlesDAO();
                Articles articles = articlesDAO.findArticle(articleId);
                RegistrationDAO registrationDAO = new RegistrationDAO();
                Registrations registrations = registrationDAO.findById(userId);
                if ((articles != null) && (registrations != null)) {
                    CommentsDAO commentsDAO = new CommentsDAO();
                    Comments comments = new Comments(articles, registrations, message);
                    commentsDAO.postComment(comments);
                    out.print(gson.toJson("successful"));
                }
            } else {
                response.sendRedirect(LOGIN);
            }

        } catch (Exception e) {
            log("Exception at GetCommentsController: " + e.getMessage());
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
