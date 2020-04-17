/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import daos.ArticlesDAO;
import entities.Articles;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ArticlesModel;
import models.BlogViewModel;

/**
 *
 * @author DELL
 */
public class GetArticleAdminController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INDEX = "index.jsp";
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
        try (PrintWriter out = response.getWriter();) {
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE") + "";
            String activate = (String) session.getAttribute("Activate") + "";
            if (role.equals("Admin") && activate.equals("Actived")) {
                List<Articles> list = new ArrayList<Articles>();
                int pageIndex = Integer.parseInt(request.getParameter("pageIndex").trim());
                int maxSize = Integer.parseInt(request.getParameter("maxS").trim());
                String by = request.getParameter("by");
                String searchMessage = request.getParameter("content");
                String status = request.getParameter("status");
                String sort = request.getParameter("sort").trim();
                Gson gson = new Gson();
                String email = (String) session.getAttribute("USER");
                if ((email != null)) {
                    ArticlesDAO articlesDAO = new ArticlesDAO();
                    // phan biet o day
                    int size;
                    if (searchMessage.equals("")) {
                        if (status.trim().equals("All")) {
                            list = articlesDAO.getArticlesByAdmin(maxSize, pageIndex, sort);
                            size = articlesDAO.countArticlesByAdmin();
                        } else {
                            list = articlesDAO.getArticlesByStatus(maxSize, pageIndex, status.trim(), sort);
                            size = articlesDAO.countArticlesByStatus(status.trim());
                        }
                    } else {
                        // has search Message
                        if (by.trim().equals("content")) {
                            // search by content
                            if (status.trim().equals("All")) {
                                list = articlesDAO.getArticlesByContent(maxSize, pageIndex, searchMessage.trim(), sort);
                                size = articlesDAO.countArticlesByContent(searchMessage.trim());
                            } else {
                                list = articlesDAO.getArticlesByContentStatus(maxSize, pageIndex, status.trim(), searchMessage.trim(), sort);
                                size = articlesDAO.countArticlesByContentStatus(status.trim(), searchMessage.trim());
                            }
                        } else {
                            // search by title
                            if (status.trim().equals("All")) {
                                list = articlesDAO.getArticlesByTitle(maxSize, pageIndex, searchMessage.trim(), sort);
                                size = articlesDAO.countArticlesByTitle(searchMessage.trim());
                            } else {
                                list = articlesDAO.getArticlesByTitleStatus(maxSize, pageIndex, status.trim(), searchMessage.trim(), sort);
                                size = articlesDAO.countArticlesByTitleStatus(status.trim(), searchMessage.trim());
                            }
                        }
                    }

                    List<ArticlesModel> listArticlesModels = new ArrayList<ArticlesModel>();
                    BlogViewModel blogProcessViewModel = null;

                    for (Articles articles : list) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String date = format.format(articles.getDateCreated());
                        ArticlesModel articlesModel = new ArticlesModel(articles.getId(), articles.getTitle(), articles.getShortDescription(), articles.getContents(), articles.getAuthor().getName(), articles.getStatus(), date);
                        listArticlesModels.add(articlesModel);
                    }
                    blogProcessViewModel = new BlogViewModel(size, listArticlesModels);
                    out.print(gson.toJson(blogProcessViewModel));
                }
            } else {
                response.sendRedirect(LOGIN);
            }
        } catch (Exception e) {
            log("Error at GetArticleAdminController: " + e.getMessage());
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
