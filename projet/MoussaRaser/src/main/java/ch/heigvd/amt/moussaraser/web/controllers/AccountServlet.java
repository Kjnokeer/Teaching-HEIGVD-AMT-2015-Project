/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 30.10.2015
 * Fichier : AccountServler.java
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
 * Servlet s'occupant de l'édition d'un utilisateur (page de gestion de compte).
 */
public class AccountServlet extends HttpServlet {

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

        User u = usersDAO.getManagedUserFromId((long) request.getSession().getAttribute("userId"));

        String action = request.getParameter("action");

        if (action != null && action.equals("Save Changes")) {
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String password = request.getParameter("password");
            String rpassword = request.getParameter("rpassword");

            if (password.equals(rpassword)) {
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setPassword(EncryptionManager.getHash(password));
                usersDAO.update(u);

                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
        }

        request.getSession().setAttribute("firstname", u.getFirstName());
        request.getSession().setAttribute("lastname", u.getLastName());
        request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
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
