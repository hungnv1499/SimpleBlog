/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SIGNIN = "SignInController";
    private static final String LOGIN = "LoginController";
    private static final String ACTIVATED = "ActivatedAccountController";
    private static final String LOGOUT = "LogoutController";
    private static final String POST_ARTICLE = "CreateArticleUserController";
    private static final String GET_ARTICLE_ADMIN = "GetArticleAdminController";
    private static final String BLOG_PROCESS = "BlogProcessAdminController";
    private static final String CHANGE_STATUS = "ChangeStatusAdminController";
    private static final String GET_ARTICLE_USER = "GetArticleController";
    private static final String DETAIL = "ShowDetailArticleController";
    private static final String AUTHEN = "AuthenticationController";
    private static final String GET_COMMENT = "GetCommentsController";
    private static final String POST_COMMENT = "PostCommentsController";
    private static final String DETAIL_ADMIN = "ShowDetailAdminController";

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
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE");
            String activate = (String) session.getAttribute("Activate");
            String action = request.getParameter("action");
            if (role == null) {
                role = "";
            }
            if (activate == null) {
                activate = "";
            }
            if (action.equals("SignIn")) {
                url = SIGNIN;
            } else {
                if (action.equals("Login")) {
                    url = LOGIN;
                } else {
                    if (action.equals("Activated") && (!role.equals("")) && (activate.equals("New"))) {
                        url = ACTIVATED;
                    } else {
                        if (action.equals("Request Activate") && (!role.equals("")) && (activate.equals("New"))) {
                            url = AUTHEN;
                        } else {
                            if (action.equals("Logout") && (!role.equals(""))) {
                                url = LOGOUT;
                            } else {
                                if (action.equals("Post Article") && role.equals("User") && activate.equals("Actived")) {
                                    url = POST_ARTICLE;
                                } else {
                                    if (action.equals("GetArticleAdmin") && role.equals("Admin") && activate.equals("Actived")) {
                                        url = GET_ARTICLE_ADMIN;
                                    } else {
                                        if (action.equals("GetInforBlog") && role.equals("Admin") && activate.equals("Actived")) {
                                            url = BLOG_PROCESS;
                                        } else {
                                            if (action.equals("ChangeStatus") && role.equals("Admin") && activate.equals("Actived")) {
                                                url = CHANGE_STATUS;
                                            } else {
                                                if (action.equals("GetArticle")) {
                                                    url = GET_ARTICLE_USER;
                                                } else {
                                                    if (action.equals("ShowDetail")) {
                                                        url = DETAIL;
                                                    } else {
                                                        if (action.equals("GetComments")) {
                                                            url = GET_COMMENT;
                                                        } else {
                                                            if (action.equals("PostComments") && activate.equals("Actived")) {
                                                                url = POST_COMMENT;
                                                            } else {
                                                                if (action.equals("ShowDetailAdmin") && role.equals("Admin") && activate.equals("Actived")) {
                                                                    url = DETAIL_ADMIN;
                                                                } else {
                                                                    request.setAttribute("ERROR", "Your action is invalid!");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.getMessage());
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
