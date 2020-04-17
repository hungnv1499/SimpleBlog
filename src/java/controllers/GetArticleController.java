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
import models.ArticlesModel;
import models.BlogViewModel;

/**
 *
 * @author DELL
 */
public class GetArticleController extends HttpServlet {

    private static final String ERROR = "error.jsp";

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
        try (PrintWriter out = response.getWriter();) {
            String status = "Actived";
            List<Articles> list = new ArrayList<Articles>();
            int pageIndex = Integer.parseInt(request.getParameter("pageIndex").trim());
            int maxSize = Integer.parseInt(request.getParameter("maxS").trim());
            String searchMessage = request.getParameter("content");
            String sort = request.getParameter("sort").trim();
            Gson gson = new Gson();
            ArticlesDAO articlesDAO = new ArticlesDAO();
            // phan biet o day
            int size;
            if (searchMessage.equals("")) {
                // lay het by status = actived
                list = articlesDAO.getArticlesByStatus(maxSize, pageIndex, status, sort);
                size = articlesDAO.countArticlesByStatus("Actived");
            } else {
                // lay by content && status = actived
                list = articlesDAO.getArticlesByContentStatus(maxSize, pageIndex, status, searchMessage, sort);
                size = articlesDAO.countArticlesByContentStatus(status, searchMessage);
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
        } catch (Exception e) {
            log("Error at GetArticleUserController: " + e.getMessage());
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
