/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 30.10.2015
 * Fichier : ListUserServlet.java
 */
package ch.heigvd.amt.moussaraser.web.controllers;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet s'occupant de l'affichage de la liste des utilisateurs (pour une
 * application).
 */
public class ListUserServlet extends HttpServlet {

    @EJB
    ApplicationDAOLocal applicationsDAO;

    @EJB
    EndUserDAOLocal endUsersDAO;

    @EJB
    ApiKeyDAOLocal apiKeyDAO;

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

        final int NB_ENDUSERS_PER_PAGE = 15;

        String apiKey = request.getParameter("app");
        int pageNumber = Integer.valueOf(request.getParameter("page"));

        Application application = applicationsDAO.getManagedApplicationByApiKey(apiKeyDAO.findByApiKeyString(
                request.getParameter("app"))
        );

        List<EndUser> endUsers = endUsersDAO.getEndUsersInApp(application);
        List<EndUser> effectiveEndUsers = new ArrayList<>();

        for (int i = (pageNumber - 1) * NB_ENDUSERS_PER_PAGE; i < pageNumber * NB_ENDUSERS_PER_PAGE; i++) {
            if (i >= endUsers.size()) {
                break;
            }

            effectiveEndUsers.add(endUsers.get(i));
        }

        int nbPagesRequired = (int) Math.ceil((double) endUsers.size() / NB_ENDUSERS_PER_PAGE);

        request.setAttribute("endUsers", effectiveEndUsers);
        request.setAttribute("nbPagesRequired", nbPagesRequired);
        request.setAttribute("applicationName", application.getName());
        request.setAttribute("app", apiKey);
        request.setAttribute("pageNumber", pageNumber);

        request.getRequestDispatcher("/WEB-INF/pages/listUsers.jsp").forward(request, response);
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
