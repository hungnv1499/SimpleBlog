/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RegistrationDAO;
import entities.Registrations;
import errors.RegistrationError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.MyUtil;

/**
 *
 * @author DELL
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "login.jsp";
    private static final String AUTHEN = "AuthenticationController";
    private static final String INDEX = "index.jsp";

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
        RegistrationError registrationError = new RegistrationError();
        boolean checkValid = true;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            if (email.equals("")) {
                registrationError.setEmailError("Email can not be blank");
                checkValid = false;
            }
            if (password.equals("")) {
                registrationError.setPasswordError("Password can not be blank");
                checkValid = false;
            }
            if (checkValid) {
                RegistrationDAO dao = new RegistrationDAO();
                String passEncrypt = MyUtil.getHash(password, "SHA-512");
                Registrations registrations = dao.login(email, passEncrypt);
                if (registrations != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", registrations.getEmail());
                    session.setAttribute("USERNAME", registrations.getName());
                    session.setAttribute("ROLE", registrations.getRole());
                    session.setAttribute("Activate", registrations.getStatus());
                    if (registrations.getStatus().equals("New")) {
                        url = AUTHEN;
                    } else {
                        url = INDEX;
                    }
                } else {
                    url = INVALID;
                    registrationError.setPasswordError("Email or password is not true");
                    request.setAttribute("INVALID", registrationError);
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", registrationError);
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.getMessage());
        } finally {
            String uri = request.getRequestURI();
            if (uri.contains("/user/") || uri.contains("/admin/")) {
                url = "../" + url;
            }
            if (url.equals(INVALID)) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
            }
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
