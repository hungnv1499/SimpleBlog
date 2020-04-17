/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ArticlesDAO;
import daos.RegistrationDAO;
import entities.Articles;
import entities.Registrations;
import errors.ArticleError;
import java.io.IOException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class CreateArticleUserController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "/user/createArticle.jsp";
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
        String url = ERROR;
        boolean checkValid = true;
        ArticleError articleError = new ArticleError();
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("USER") + "";
            String role = (String) session.getAttribute("ROLE") + "";
            String activate = (String) session.getAttribute("Activate") + "";
            if (role.equals("User") && activate.equals("Actived")) {
                String title = request.getParameter("txtTitle");
                String shortDescription = request.getParameter("txtShort");
                String contents = request.getParameter("txtContents");
                if (title.equals("")) {
                    checkValid = false;
                    articleError.setTitleError("Title can not be blank");
                }
                if (shortDescription.equals("")) {
                    checkValid = false;
                    articleError.setShortDescriptionError("Short description can not be blank");
                }
                if (contents.equals("")) {
                    checkValid = false;
                    articleError.setContentsError("Content can not be blank");
                }
                if (checkValid) {
                    ArticlesDAO articleDAO = new ArticlesDAO();
                    RegistrationDAO registrationDAO = new RegistrationDAO();
                    Registrations registrations = registrationDAO.findById(email);
                    if (registrations != null) {
                        Articles articles = new Articles(title, shortDescription, contents, registrations);
                        articleDAO.create(articles);
                        url = INDEX;
                    } else {
                        url = LOGIN;
                    }
                } else {
                    url = INVALID;
                    request.setAttribute("INVALID", articleError);
                }
            } else {
                response.sendRedirect(LOGIN);
            }
        } catch (RollbackException ex) {
            if (ex.getMessage().indexOf("duplicate") > -1) {
                articleError.setTitleError("Title name is existing. Try another");
                url = INVALID;
                request.setAttribute("INVALID", articleError);
            }
        } catch (Exception e) {
             url = ERROR;
            request.setAttribute("ERROR", "Some thing wrong, try again");
            log("Error at CreateArticleController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
