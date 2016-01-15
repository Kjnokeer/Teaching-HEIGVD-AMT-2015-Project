/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : AuthorizationRequestFilter.java
 */
package ch.heigvd.amt.moussaraser.rest.config;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.rest.config.response.SendApiKey;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Mathias
 */
@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

   @EJB
   ApiKeyDAOLocal apiKeyDAO;

   @Override
   public void filter(ContainerRequestContext crc) throws IOException {
      if (crc.getRequest().getMethod().equals("OPTIONS")) {
         crc.abortWith(Response.status(Response.Status.OK).build());
         return;
      }

      String apiKey;

      try {
         apiKey = crc.getUriInfo().getQueryParameters().get("apiKey").get(0);
      } catch (NullPointerException e) {
         crc.abortWith(SendApiKey.errorApiKeyNotProvided());
         return;
      }

      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      if (key == null) {
         crc.abortWith(SendApiKey.errorApiKeyInvalid());
      }
   }

}
