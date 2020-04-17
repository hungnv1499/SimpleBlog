/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RegistrationDAO;
import entities.Registrations;
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
public class ActivatedAccountController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "authentication.jsp";
    private static final String AUTHEN = "AuthenticationController";
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
        try {
            String code = request.getParameter("txtCode");
            HttpSession session = request.getSession();
            String currentCode = (String) session.getAttribute("CODE");
            if (currentCode != null) {
                if (currentCode.equals(code)) {
                    RegistrationDAO dao = new RegistrationDAO();
                    String email = (String) session.getAttribute("USER");
                    Registrations registration = dao.findById(email);
                    if (registration != null) {
                        registration.setStatus("Actived");
                        dao.edit(registration);
                        session.setAttribute("Activate", registration.getStatus());
                        url = INDEX;
                    } else {
                        url = LOGIN;
                    }
                } else {
                    url = INVALID;
                    request.setAttribute("INVALID", "Code is not be equal");
                }
            } else {
                url = AUTHEN;
            }
        } catch (RollbackException ex) {
            url = INVALID;
            request.setAttribute("INVALID", "Some thing wrong, try again");
        } catch (Exception e) {
            log("Error at ActivatedAccountController: " + e.getMessage());
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
