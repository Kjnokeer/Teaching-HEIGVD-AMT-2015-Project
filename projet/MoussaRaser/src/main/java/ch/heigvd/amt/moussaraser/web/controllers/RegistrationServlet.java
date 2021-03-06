/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 16.10.2015
 * Fichier : RegistrationServlet.java
 */
package ch.heigvd.amt.moussaraser.web.controllers;

import ch.heigvd.amt.moussaraser.model.entities.User;
import ch.heigvd.amt.moussaraser.services.dao.UsersDAOLocal;
import ch.heigvd.amt.moussaraser.web.utils.EncryptionManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet s'occupant l'enregistrement d'un nouvel utilisateur (page
 * d'inscription).
 */
public class RegistrationServlet extends HttpServlet {

    @EJB
    UsersDAOLocal usersDAO;

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

        String email = request.getParameter("email");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String password = request.getParameter("password");
        String rpassword = request.getParameter("rpassword");

        if (email != null && !email.equals("")
                && firstName != null && !firstName.equals("")
                && lastName != null && !lastName.equals("")
                && password.equals(rpassword)) {
            User u = new User();
            u.setEmail(email);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setPassword(EncryptionManager.getHash(password));

            usersDAO.create(u);

            response.sendRedirect(request.getContextPath());
        } else {
            request.setAttribute("loginError", 1);
            request.setAttribute("email", email);
            request.setAttribute("fname", firstName);
            request.setAttribute("lname", lastName);
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
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
