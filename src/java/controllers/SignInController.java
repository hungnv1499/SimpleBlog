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
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.EmailUtil;
import utils.MyUtil;

/**
 *
 * @author DELL
 */
public class SignInController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "registration.jsp";
    private static final String AUTHEN = "authentication.jsp";

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
        RegistrationError registrationError = new RegistrationError();
        boolean valid = true;
        try {
            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtFullname");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            if (!EmailUtil.validateEMail(email)) {
                registrationError.setEmailError("Email is invalid, try another!");
                valid = false;
            }
            if (password.equals("")) {
                registrationError.setPasswordError("Password is not be blank");
                valid = false;

            }
            if (!confirm.equals(password)) {
                registrationError.setConfirmError("Confirm password is not equal");
                valid = false;
            }
            if (valid) {
                String passEncryp = MyUtil.getHash(password, "SHA-512");
                Registrations registrations = new Registrations(email, name, passEncryp);
                RegistrationDAO dao = new RegistrationDAO();
                dao.create(registrations);
                url = AUTHEN;
            } else {
                url = INVALID;
                request.setAttribute("INVALID", registrationError);
            }
        } catch (RollbackException ex) {
            if (ex.getMessage().indexOf("duplicate") > -1){
                registrationError.setEmailError("Email name is existing. Try another");
                valid = false;
                url = INVALID;
                request.setAttribute("INVALID", registrationError);
            }
        } catch (Exception e) {
            log("Error at SignInController: " + e.getMessage());
            url = ERROR;
            request.setAttribute("ERROR", "Some thing wrong, try again");
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
