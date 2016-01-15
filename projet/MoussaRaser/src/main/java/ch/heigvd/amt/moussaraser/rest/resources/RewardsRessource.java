/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 29.11.2015
 * Fichier : RewardsRessource.java
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendReward;
import ch.heigvd.amt.moussaraser.rest.config.response.message.InfoObject;
import ch.heigvd.amt.moussaraser.rest.dto.RewardDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.RewardDAOLocal;
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
 * Classe resprésentant une ressource REST LeaderBoard et l'action pour certaines
 * méthodes HTTP : - GET /rewards - POST /rewards - GET /rewards/{id} - PUT
 * /rewards/{id} - DELETE /rewards/{id}
 *
 * @author jermoret
 */
@Stateless
@Path("/rewards")
public class RewardsRessource {

   @EJB
   RewardDAOLocal rewardDAO;

   @EJB
   ApplicationDAOLocal applicationDAO;

   @EJB
   ApiKeyDAOLocal apiKeyDAO;

   /**
    * Récupère la liste de tous les prix
    *
    * @param apiKey clé de l'application
    * @return réponse JAX-RS
    */
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getRewards(@QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      List<Reward> rewards = rewardDAO.getRewardsByApiKey(key);
      List<RewardDTO> rewardsDTO = new ArrayList<>();

      for (Reward reward : rewards) {
         rewardsDTO.add(new RewardDTO(
                 reward.getId(),
                 reward.getName(),
                 reward.getCategory(),
                 reward.getDescription(),
                 reward.getImage()
         ));
      }

      return SendReward.send200OK(rewardsDTO);
   }

   /**
    * Créer un prix
    *
    * @param apiKey clé de l'application
    * @param reward Payload JSON du reward
    * @return Réponse JAX-RS
    */
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response createReward(@QueryParam("apiKey") String apiKey, RewardDTO reward) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);
      
      if(reward == null || reward.getName() == null || reward.getCategory() == null) {
         SendReward.errorRewardInvalid();
      }

      Reward newReward = new Reward();
      newReward.setName(reward.getName());
      newReward.setCategory(reward.getCategory());
      newReward.setDescription(reward.getDescription());
      newReward.setImage(reward.getImage());
      newReward.setApplication(applicationDAO.getApplicationByApiKey(key));

      newReward.setId(rewardDAO.create(newReward)); // Insert

      return SendReward.send201Created(new RewardDTO(
              newReward.getId(),
              newReward.getName(),
              newReward.getCategory(),
              newReward.getDescription(),
              newReward.getImage()
      ));
   }

   /**
    * Récupère un certain prix selon un id
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

      Reward reward = rewardDAO.getRewardByIdAndByApiKey(id, key);

      if (reward == null) {
         return SendReward.errorRewardInvalid();
      }

      return SendReward.send200OK(new RewardDTO(
              reward.getId(),
              reward.getName(),
              reward.getCategory(),
              reward.getDescription(),
              reward.getImage()
      ));
   }

   /**
    * Modifie un certain prix
    *
    * @param id id du badge
    * @param apiKey clé de l'application
    * @param reward Payload JSON du reward
    * @return réponse JAX-RS
    */
   @PUT
   @Path("/{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateBadge(@PathParam("id") long id, @QueryParam("apiKey") String apiKey, RewardDTO reward) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      Reward tmp = rewardDAO.getRewardByIdAndByApiKey(id, key);

      if (tmp == null) {
         return SendReward.errorRewardInvalid();
      }

      Reward updateReward = rewardDAO.createAndReturnManagedEntity(tmp);
      updateReward.setName(reward.getName());
      updateReward.setCategory(reward.getCategory());
      updateReward.setDescription(reward.getDescription());
      updateReward.setImage(reward.getImage());

      return SendReward.send200OK(new RewardDTO(
              updateReward.getId(),
              updateReward.getName(),
              updateReward.getCategory(),
              updateReward.getDescription(),
              updateReward.getImage()
      ));
   }

   /**
    * Supprime un certain prix
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

      Reward reward = rewardDAO.getRewardByIdAndByApiKey(id, key);

      if (reward == null) {
         return SendReward.errorRewardInvalid();
      }

      rewardDAO.delete(reward);

      return SendReward.send200OK(new InfoObject("Reward successfully deleted"));
   }

}
