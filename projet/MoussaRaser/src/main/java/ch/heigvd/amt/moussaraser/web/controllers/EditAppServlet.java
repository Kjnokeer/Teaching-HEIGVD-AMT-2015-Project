/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 30.10.2015
 * Fichier : EditAppServlet.java
 */

package ch.heigvd.amt.moussaraser.web.controllers;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.User;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.UsersDAOLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet s'occupant de l'édition d'une application.
 */
public class EditAppServlet extends HttpServlet {

   @EJB
   ApplicationDAOLocal applicationsDAO;

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

      User u = usersDAO.getUserFromId((Long) request.getSession().getAttribute("userId"));

      String action = request.getParameter("action");

      if (action != null && action.equals("Save changes")) {
         String name = request.getParameter("name");
         String description = request.getParameter("description");

         if (name != null && description != null) {
            Application application = (Application) request.getSession().getAttribute("application");

            application.setName((String) request.getParameter("name"));
            application.setDescription((String) request.getParameter("description"));

            String state = request.getParameter("state");

            if (state != null && state.equals("on")) {
               application.setEnabled(true);
            } else {
               application.setEnabled(false);
            }

            applicationsDAO.update(application);
         }
         request.getSession().removeAttribute("application");

         response.sendRedirect(request.getContextPath() + "/home");
         return;
      }

      Application application = applicationsDAO.getManagedApplicationByApiKey(request.getParameter("app"));
      request.getSession().setAttribute("application", application);

      request.getRequestDispatcher("/WEB-INF/pages/editApp.jsp").forward(request, response);
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
