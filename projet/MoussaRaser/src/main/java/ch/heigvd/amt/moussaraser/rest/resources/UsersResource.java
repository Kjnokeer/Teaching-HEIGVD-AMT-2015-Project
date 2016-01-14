/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date :
 * 29.11.2015 Fichier : UsersRessource.java
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendBadge;
import ch.heigvd.amt.moussaraser.rest.config.response.SendReward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendUser;
import ch.heigvd.amt.moussaraser.rest.config.response.message.InfoObject;
import ch.heigvd.amt.moussaraser.rest.dto.ApplicationDTO;
import ch.heigvd.amt.moussaraser.rest.dto.BadgeDTO;
import ch.heigvd.amt.moussaraser.rest.dto.EndUserDTO;
import ch.heigvd.amt.moussaraser.rest.dto.RewardDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.BadgeDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
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
 * Classe resprésentant une ressource REST User (EndUser) et l'action pour certaines
 * méthodes HTTP : - GET /users - POST /users
 *
 * - GET /users/{id} - PUT /users/{id} - DELETE /users/{id}
 *
 * - GET /users/{id}/badges - POST /users/{id}/badges - DELETE
 * /users/{id}/badges/{id}
 *
 * - GET /users/{id}/rewards - POST /users/{id}/rewards - DELETE
 * /users/{id}/rewards/{id}
 *
 * @author jermoret
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

   @EJB
   BadgeDAOLocal badgeDAO;

   @EJB
   RewardDAOLocal rewardDAO;

   /**
    * Récupère la liste de tous les utilisateurs finaux de l'application
    *
    * @param apiKey Clé de l'application
    * @return Réponse JAX-RS
    */
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getEndUsers(@QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      List<EndUser> endUsers = endUsersDAO.getEndUsersByApiKey(key);
      List<EndUserDTO> endUsersDTO = new ArrayList<>();

      for (EndUser endUser : endUsers) {
         endUsersDTO.add(userDetails(endUser));
      }

      return SendUser.send200OK(endUsersDTO);
   }

   /**
    * Créer un utilisateur final
    *
    * @param apiKey Clé de l'application
    * @param endUserDTO Payload JSON de l'utilisateur
    * @return Réponse JAX-RS
    */
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response createNewUser(@QueryParam("apiKey") String apiKey, EndUserDTO endUserDTO) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      if (endUserDTO == null || endUserDTO.getFirstName() == null || endUserDTO.getLastName() == null) {
         return SendUser.missingDataInPayload();
      }

      EndUser newEndUser = new EndUser();
      newEndUser.setFirstName(endUserDTO.getFirstName());
      newEndUser.setLastName(endUserDTO.getLastName());
      newEndUser.setScore(endUserDTO.getScore() == null ? 0 : endUserDTO.getScore());
      newEndUser.setApplication(applicationDAO.getApplicationByApiKey(key));

      endUsersDAO.create(newEndUser);

      return SendUser.send200OK(userDetails(newEndUser));
   }

   /**
    * Récupère un certain utilisateur final selon un id
    *
    * @param userId ID de l'utilisateur final
    * @param apiKey Clé de l'application
    * @return Réponse JAX-RS
    */
   @GET
   @Path("/{userId}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getDetailsUser(@PathParam("userId") long userId, @QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUser == null) {
         return SendUser.errorUserInvalid();
      }

      return SendUser.send200OK(userDetails(endUser));
   }

   /**
    * Modifie un utilisateur final
    *
    * @param userId ID de l'utilisateur final
    * @param apiKey Clé de l'application
    * @param endUserDTO Payload JSON de l'utilisateur
    * @return Réponse JAX-RS
    */
   @PUT
   @Path("/{userId}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateUser(@PathParam("userId") long userId, @QueryParam("apiKey") String apiKey, EndUserDTO endUserDTO) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      if (endUserDTO == null || endUserDTO.getFirstName() == null || endUserDTO.getLastName() == null) {
         return SendUser.missingDataInPayload();
      }

      EndUser endUserToUpdate = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUserToUpdate == null) {
         return SendUser.errorUserInvalid();
      }

      endUserToUpdate = endUsersDAO.createAndReturnManagedEntity(endUserToUpdate);

      endUserToUpdate.setFirstName(endUserDTO.getFirstName());
      endUserToUpdate.setLastName(endUserDTO.getLastName());

      if (endUserDTO.getScore() != null) {
         endUserToUpdate.setScore(endUserDTO.getScore());
      }

      return SendUser.send200OK(userDetails(endUserToUpdate));
   }

   /**
    * Supprime un utilisateur final
    *
    * @param userId ID de l'utilisateur final
    * @param apiKey Clé de l'application
    * @return Réponse JAX-RS
    */
   @DELETE
   @Path("/{userId}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteUser(@PathParam("userId") long userId, @QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUser == null) {
         return SendUser.errorUserInvalid();
      }

      endUsersDAO.delete(endUser);

      return SendUser.send200OK(new InfoObject("User successfully deleted"));
   }

   /**
    * Récupére tous les badges d'un utilisateur final
    *
    * @param userId ID de l'utilisateur final
    * @param apiKey Clé de l'application
    * @return Réponse JAX-RS
    */
   @GET
   @Path("/{userId}/badges")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getBadgesForUser(@PathParam("userId") long userId, @QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUser == null) {
         return SendUser.errorUserInvalid();
      }

      return SendUser.send200OK(getBadgesDTO(endUser));
   }

   /**
    * Assigne un badge pour un utilisateur final
    *
    * @param userId ID de l'utilisateur final
    * @param apiKey Clé de l'application
    * @param badgeId ID du badge à ajouter à l'utilisateur final
    * @return Réponse JAX-RS
    */
   @POST
   @Path("/{userId}/badges")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response assignBadgeToUser(@PathParam("userId") long userId, @QueryParam("apiKey") String apiKey, long badgeId) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUser == null) {
         return SendUser.errorUserInvalid();
      }

      Badge badge = badgeDAO.getBadgeByIdAndByApiKey(badgeId, key);

      if (badge == null) {
         return SendBadge.errorBadgeInvalid();
      }

      endUser.addBadge(badge);

      return SendUser.send200OK(userDetails(endUser));
   }

   /**
    * Retire un badge d'un utilisateur final
    *
    * @param userId ID de l'utilisateur final
    * @param badgeId ID du badge à retirer à l'utilisateur final
    * @param apiKey Clé de l'application
    * @return Réponse JAX-RS
    */
   @DELETE
   @Path("/{userId}/badges/{badgeId}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response removeBadgeForUser(@PathParam("userId") long userId, @PathParam("badgeId") long badgeId, @QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUser == null) {
         return SendUser.errorUserInvalid();
      }

      Badge badge = badgeDAO.getBadgeByIdAndByApiKey(badgeId, key);

      if (badge == null) {
         return SendBadge.errorBadgeInvalid();
      }

      endUser.removeBadge(badge);

      return SendUser.send200OK(userDetails(endUser));
   }

   /**
    * Récupére tous les prix d'un utilisateur final
    *
    * @param userId ID de l'utilisateur final
    * @param apiKey Clé de l'application
    * @return Réponse JAX-RS
    */
   @GET
   @Path("/{userId}/rewards")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getRewardsOfUser(@PathParam("userId") long userId, @QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUser == null) {
         return SendUser.errorUserInvalid();
      }

      return SendUser.send200OK(getRewardsDTO(endUser));
   }

   /**
    * Créer un prix pour un certain utilisateur final
    *
    * @param userId ID de l'utilisateur final
    * @param apiKey Clé de l'application
    * @param rewardId ID du prix à ajouter à l'utilisateur final
    * @return Réponse JAX-RS
    */
   @POST
   @Path("/{userId}/rewards")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response assignRewardToUser(@PathParam("userId") long userId, @QueryParam("apiKey") String apiKey, long rewardId) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUser == null) {
         return SendUser.errorUserInvalid();
      }

      Reward reward = rewardDAO.getRewardByIdAndByApiKey(rewardId, key);

      if (reward == null) {
         return SendReward.errorRewardInvalid();
      }

      endUser.addReward(reward);

      return SendUser.send200OK(userDetails(endUser));
   }

   /**
    * Supprime un certain prix d'un certain utilisateur final
    *
    * @param userId ID de l'utilisateur final
    * @param rewardId ID du prix à ajouter à l'utilisateur final
    * @param apiKey Clé de l'application
    * @return Réponse JAX-RS
    */
   @DELETE
   @Path("/{userId}/rewards/{rewardId}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteRewardOfUser(@PathParam("userId") long userId, @PathParam("rewardId") long rewardId, @QueryParam("apiKey") String apiKey) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(userId, key);

      if (endUser == null) {
         return SendUser.errorUserInvalid();
      }

      Reward reward = rewardDAO.getRewardByIdAndByApiKey(rewardId, key);

      if (reward == null) {
         return SendReward.errorRewardInvalid();
      }

      endUser.removeReward(reward);

      return SendUser.send200OK(userDetails(endUser));
   }

   /**
    * Récupère une liste des badges de l'utilisateur final
    *
    * @param endUser L'utilisateur final
    * @return Une liste des badges de l'utilisateur final
    */
   private List<BadgeDTO> getBadgesDTO(EndUser endUser) {
      List<Badge> badges = endUser.getBadges();
      ArrayList<BadgeDTO> badgesDTO = new ArrayList<>();

      for (Badge badge : badges) {
         badgesDTO.add(new BadgeDTO(
                 badge.getId(),
                 badge.getName(),
                 badge.getCategory(),
                 badge.getDescription(),
                 badge.getImage()
         ));
      }

      return badgesDTO;
   }

   /**
    * Récupère une liste des prix de l'utilisateur final
    *
    * @param endUser L'utilisateur final
    * @return Une liste des prix de l'utilisateur final
    */
   private List<RewardDTO> getRewardsDTO(EndUser endUser) {
      List<Reward> rewards = endUser.getRewards();
      ArrayList<RewardDTO> rewardsDTO = new ArrayList<>();

      for (Reward reward : rewards) {
         rewardsDTO.add(new RewardDTO(
                 reward.getId(),
                 reward.getName(),
                 reward.getCategory(),
                 reward.getDescription(),
                 reward.getImage()
         ));
      }

      return rewardsDTO;
   }

   /**
    * Récupère les détails d'un utilisateur
    *
    * @param endUser L'utilisateur final
    * @return Réponse JAX-RS
    */
   private EndUserDTO userDetails(EndUser endUser) {
      List<BadgeDTO> badgesDTO = getBadgesDTO(endUser);
      List<RewardDTO> rewardsDTO = getRewardsDTO(endUser);

      return new EndUserDTO(
              endUser.getId(),
              endUser.getFirstName(),
              endUser.getLastName(),
              endUser.getScore(),
              new ApplicationDTO(endUser.getApplication().getName(), endUser.getApplication().getDescription(), endUser.getApplication().isEnabled()),
              badgesDTO,
              rewardsDTO,
              endUser.getRegistrationDate()
      );
   }

}
