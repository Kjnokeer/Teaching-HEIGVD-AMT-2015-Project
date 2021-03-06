/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 29.11.2015
 * Fichier : BadgesRessource.java
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.rest.config.response.SendBadge;
import ch.heigvd.amt.moussaraser.rest.config.response.message.InfoObject;
import ch.heigvd.amt.moussaraser.rest.dto.BadgeDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.BadgeDAOLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe resprésentant une ressource REST Badge et l'action pour certaines méthodes
 * HTTP : - GET /badges - POST /badges - GET /badges/{id} - PUT /badges/{id} - DELETE
 * /badges/{id}
 *
 * @author jermoret
 */
@Stateless
@Path("/badges")
public class BadgesResource {

   @EJB
   BadgeDAOLocal badgeDAO;

   @EJB
   ApplicationDAOLocal applicationDAO;

   @EJB
   ApiKeyDAOLocal apiKeyDAO;

   /**
    * Récupère la liste de tous les badges
    *
    * @param apiKey clé de l'application
    * @return réponse JAX-RS
    */
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getBadges(@QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      List<Badge> badges = badgeDAO.getBadgesByApiKey(key);
      List<BadgeDTO> badgesDTO = new ArrayList<>();

      // Transfert entité JPA vers DTO
      for (Badge badge : badges) {
         badgesDTO.add(new BadgeDTO(badge.getId(), badge.getName(), badge.getCategory(), badge.getDescription(), badge.getImage()));
      }

      // Réponse
      return SendBadge.send200OK(badgesDTO);
   }

   /**
    * Créer un badge
    *
    * @param apiKey clé de l'application
    * @param badge Payload JSON du badge
    * @return Réponse JAX-RS
    */
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response createBadge(@QueryParam("apiKey") String apiKey, BadgeDTO badge) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);
      
      if(badge == null || badge.getName() == null || badge.getCategory() == null) {
         SendBadge.errorBadgeInvalid();
      }
      
      Badge newBadge = new Badge();
      newBadge.setName(badge.getName());
      newBadge.setCategory(badge.getCategory());
      newBadge.setDescription(badge.getDescription());
      newBadge.setImage(badge.getImage());
      newBadge.setApplication(applicationDAO.getApplicationByApiKey(key));

      newBadge.setId(badgeDAO.create(newBadge)); // Insert

      return SendBadge.send201Created(new BadgeDTO(
              newBadge.getId(),
              newBadge.getName(),
              newBadge.getCategory(),
              newBadge.getDescription(),
              newBadge.getImage()
      ));
   }

   /**
    * Récupère un certain badge selon un id
    *
    * @param id id badge
    * @param apiKey clé de l'application
    * @return réponse Jax-RS
    */
   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getBadge(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      Badge b = badgeDAO.getBadgeByIdAndByApiKey(id, key);

      if (b == null) {
         return SendBadge.errorBadgeInvalid();
      }

      return SendBadge.send200OK(new BadgeDTO(
              b.getId(),
              b.getName(),
              b.getCategory(),
              b.getDescription(),
              b.getImage()
      ));
   }

   /**
    * Modifie un certain badge
    *
    * @param id id du badge
    * @param apiKey clé de l'application
    * @param badge Payload JSON du badge
    * @return réponse JAX-RS
    */
   @PUT
   @Path("/{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateBadge(@PathParam("id") long id, @QueryParam("apiKey") String apiKey,BadgeDTO badge) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      Badge tmp = badgeDAO.getBadgeByIdAndByApiKey(id, key);

      if (tmp == null) {
         return SendBadge.errorBadgeInvalid();
      }

      Badge updateBadge = badgeDAO.createAndReturnManagedEntity(tmp);
      updateBadge.setName(badge.getName());
      updateBadge.setCategory(badge.getCategory());
      updateBadge.setDescription(badge.getDescription());
      updateBadge.setImage(badge.getImage());

      return SendBadge.send200OK(new BadgeDTO(
              updateBadge.getId(),
              updateBadge.getName(),
              updateBadge.getCategory(),
              updateBadge.getDescription(),
              updateBadge.getImage()
      ));
   }

   /**
    * Supprime un certain badge
    *
    * @param id id du badge
    * @param apiKey clé de l'application
    * @return réponse JAX-RS
    */
   @DELETE
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteBadge(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      Badge badge = badgeDAO.getBadgeByIdAndByApiKey(id, key);

      if (badge == null) {
         return SendBadge.errorBadgeInvalid();
      }

      badgeDAO.delete(badge);

      return SendBadge.send200OK(new InfoObject("Badge successfully deleted"));
   }

}
