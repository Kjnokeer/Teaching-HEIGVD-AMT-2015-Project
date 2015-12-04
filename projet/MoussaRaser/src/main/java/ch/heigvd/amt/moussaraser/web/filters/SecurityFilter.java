/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date
 * : 09.10.2015 Fichier : SecurityFilter.java
 */
package ch.heigvd.amt.moussaraser.web.filters;

import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.UsersDAOLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet qui est appelé sur tous les URLs et qui gère la sécurité. Si la page
 * ou l'utilisateur veut aller nécessite d'être connecté, c'est ce servlet qui
 * gère la redirection (page de login si pas connecté et page demandée s'il est
 * connecté) ...
 */
public class SecurityFilter implements Filter {

    @EJB
    UsersDAOLocal usersDAO;

    @EJB
    ApplicationDAOLocal applicationDAO;
    
    @EJB
    EndUserDAOLocal endUserDAO;

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        /*
         * Let's apply a white list access policy. By default, we will authorize access to the target URI on
         * if the user has been authenticated.
         */
        boolean isTargetUrlProtected = true;

        if ("/registrationPage".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
            return;
        }

        /*
         * If the target URL is static content or if it is the authentication servlet, then we grant access event
         * if the user has not been authenticated.
         */
        if (path.startsWith("/static/")) {
            isTargetUrlProtected = false;
        } else if ("/auth".equals(path)) {
            isTargetUrlProtected = false;
        } else if (path.startsWith("/api/")) {
            isTargetUrlProtected = false;
        } else if ("/reg".equals(path)) {
            isTargetUrlProtected = false;
        } else {
            /*
             * Let's imagine that the user has sent a request to /MVCDemo/pages/beers before logging into the
             * application. In that case, we want to route the user to the login page. If he provides valid
             * credentials, then we then want to redirect the user to /MVCDemo/pages/beers. In order to do that,
             * we need to save the target URL
             */
            request.setAttribute("targetUrl", path);

        }

        String action = httpRequest.getParameter("a");

        if (action != null && action.equals("logout")) {
            httpRequest.getSession().invalidate();
        }

        /*
         * If the user has been authenticated before, then the AuthenticationServlet has placed
         * an object (in this case a String) in the HTTP session. We can retrieve it.
         */
        Long userId = (Long) httpRequest.getSession().getAttribute("userId");
        if (userId == null && isTargetUrlProtected) {
            request.setAttribute("nbUsers", usersDAO.count());
            request.setAttribute("nbApp", applicationDAO.count());
            request.setAttribute("nbEndUserLast30Days", endUserDAO.getNumberEndUserRegisteredLast30Days());
            
            /*
             * The user has not been authenticated and tries to access a protected resource,
             * we display the login page (and interrupt the request processing pipeline).
             */
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        } else {
            /*
             * We authorize the access, so we can tell the request processing pipeline to
             * continue its work.
             */
            chain.doFilter(request, response);
            /*
             * Here, we could inspect and manipulate the response and its way back to the
             * client. In this case, we don't have anything to do.
             */
        }
    }

    @Override
    public void destroy() {
    }

}
