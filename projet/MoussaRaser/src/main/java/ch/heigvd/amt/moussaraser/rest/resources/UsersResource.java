/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.rest.config.response.SendApiKey;
import ch.heigvd.amt.moussaraser.rest.config.response.SendUser;
import ch.heigvd.amt.moussaraser.rest.dto.EndUserDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
import ch.heigvd.amt.moussaraser.web.utils.EncryptionManager;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
@Path("/users")
public class UsersResource {

   @EJB
   EndUserDAOLocal endUsersDAO;

   @EJB
   ApplicationDAOLocal applicationDAO;

   @EJB
   ApiKeyDAOLocal apiKeyDAO;

   @GET
   @Produces("application/json")
   public Response getEndUsers(@QueryParam("apiKey") String apiKey) {

      if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
         return SendApiKey.errorApiKeyNotProvided();
      }

      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      if (key == null) {
         return SendApiKey.errorApiKeyInvalid();
      }

      List<EndUser> endUsers = endUsersDAO.getEndUsersByApiKey(key);
      List<EndUserDTO> endUsersDTO = new ArrayList<>();
      
      for (EndUser endUser : endUsers) {
         endUsersDTO.add(new EndUserDTO(endUser.getFirstName(), endUser.getLastName()));
      }
      
      return SendUser.send200OK(endUsersDTO);
   }

   @GET
   @Path("/{id}")
   @Produces("application/json")
   public Response getEndUser(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {
      
      if(apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
         return SendApiKey.errorApiKeyNotProvided();
      }
      
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);
      
      if(key == null) {
         return SendApiKey.errorApiKeyInvalid();
      }
      
      EndUser endUser = endUsersDAO.findById(id);
      
      if(endUser == null) {
         return SendUser.errorUserInvalid();
      }
      
      return SendUser.send200OK(new EndUserDTO(endUser.getFirstName(), endUser.getLastName()));
   }

}
