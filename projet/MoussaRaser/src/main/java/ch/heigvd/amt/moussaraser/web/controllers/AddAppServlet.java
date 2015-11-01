package ch.heigvd.amt.moussaraser.web.controllers;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.UsersDAOLocal;
import ch.heigvd.amt.moussaraser.web.utils.EncryptionManager;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAppServlet extends HttpServlet {

   @EJB
   ApplicationDAOLocal applicationDAO;

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

      String action = request.getParameter("action");

      if (action != null && action.equals("Register")) {
         String name = request.getParameter("name");
         String description = request.getParameter("description");

         if (name != null && description != null) {
            Application app = new Application();
            app.setName(name);
            app.setDescription(description);
            app.setCreator(usersDAO.getUserFromId((long) request.getSession().getAttribute("userId")));
            app.setApiKey(EncryptionManager.getAPIKey());
            applicationDAO.create(app);
         }
         response.sendRedirect(request.getContextPath() + "/home");
         return;
      }

      request.getRequestDispatcher("/WEB-INF/pages/addApp.jsp").forward(request, response);
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
