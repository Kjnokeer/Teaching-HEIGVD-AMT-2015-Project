/**
 * Auteurs : J�r�me Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 29.11.2015
 * Fichier : UsersRessource.java
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendApiKey;
import ch.heigvd.amt.moussaraser.rest.config.response.SendBadge;
import ch.heigvd.amt.moussaraser.rest.config.response.SendReward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendUser;
import ch.heigvd.amt.moussaraser.rest.config.response.message.InfoObject;
import ch.heigvd.amt.moussaraser.rest.dto.BadgeDTO;
import ch.heigvd.amt.moussaraser.rest.dto.EndUserDTO;
import ch.heigvd.amt.moussaraser.rest.dto.RewardDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.BadgeDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.RewardDAOLocal;
import ch.heigvd.amt.moussaraser.web.utils.EncryptionManager;
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
 * Classe respr�sentant une ressource REST User (EndUser) et l'action pour certaines
 * m�thodes HTTP :
 * - GET /users
 * - POST /users
 *
 * - GET /users/{id}
 * - PUT /users/{id}
 * - DELETE /users/{id}
 *
 * - GET /users/{id}/badges
 * - POST /users/{id}/badges
 * - DELETE /users/{id}/badges/{id}
 *
 * - GET /users/{id}/rewards
 * - POST /users/{id}/rewards
 * - DELETE /users/{id}/rewards/{id}
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
     * R�cup�re la liste de tous les utilisateurs finaux de l'application
     *
     * @param apiKey cl� de l'application
     * @return r�ponse JAX-RS
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
            endUsersDTO.add(new EndUserDTO(endUser.getId(), endUser.getFirstName(), endUser.getLastName(), endUser.getScore()));
        }

        return SendUser.send200OK(endUsersDTO);
    }

    /**
     * Cr�er un utilisateur final
     *
     * @param b Payload JSON de l'utilisateur
     * @param apiKey cl� de l'application
     * @return R�ponse JAX-RS
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(EndUser endUser, @QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        endUser.setApplication(applicationDAO.getApplicationByApiKey(key));

        endUsersDAO.create(endUser);

        return SendUser.send201Created(new EndUserDTO(
                endUser.getId(),
                endUser.getFirstName(),
                endUser.getLastName(),
                endUser.getScore()
        ));
    }

    /**
     * R�cup�re un certain utilisateur final selon un id
     *
     * @param id id badge
     * @param apiKey cl� de l'application
     * @return r�ponse Jax-RS
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEndUser(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(id, key);

        if (endUser == null) {
            return SendUser.errorUserInvalid();
        }

        return SendUser.send200OK(new EndUserDTO(
                endUser.getId(),
                endUser.getFirstName(),
                endUser.getLastName(),
                endUser.getScore()
        ));
    }

    /**
     * Modifie un certain utilisateur final
     *
     * @param b Payload JSON de l'utilisateur
     * @param id id du badge
     * @param apiKey cl� de l'application
     * @return r�ponse JAX-RS
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(EndUser endUser, @PathParam("id") long id, @QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser tmp = endUsersDAO.getEndUserByIdAndByApiKey(id, key);

        if (tmp == null) {
            return SendUser.errorUserInvalid();
        }

        EndUser endUserUpdate = endUsersDAO.createAndReturnManagedEntity(tmp);

        endUserUpdate.setFirstName(endUser.getFirstName());
        endUserUpdate.setLastName(endUser.getLastName());
        endUserUpdate.setApplication(tmp.getApplication());
        endUserUpdate.setBadges(tmp.getBadges());
        endUserUpdate.setRewards(tmp.getRewards());

        return SendUser.send200OK(new EndUserDTO(
                endUser.getId(),
                endUserUpdate.getFirstName(),
                endUserUpdate.getLastName(),
                endUser.getScore()
        ));
    }

    /**
     * Supprime un certain badge
     *
     * @param id id du badge
     * @param apiKey cl� de l'application
     * @return r�ponse JAX-RS
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(id, key);

        if (endUser == null) {
            return SendUser.errorUserInvalid();
        }

        endUsersDAO.delete(endUser);

        return SendUser.send200OK(new InfoObject(
                "User successfully deleted"
        ));
    }

    /**
     * R�cup�re tous les badges d'un certain utilisateur final
     * @param id id de l'utilisateur
     * @param apiKey cl� de l'application
     * @return r�spone JAX-RS
     */
    @GET
    @Path("/{id}/badges")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBadgesOfUser(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {
        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(id, key);

        if (endUser == null) {
            return SendUser.errorUserInvalid();
        }

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

        return SendUser.send200OK(badgesDTO);
    }

    /**
     * Cr�er un badge pour un certain utilisateur final
     * @param badge badge � cr�er
     * @param id id de l'utilisateur final
     * @param apiKey cl� de l'application
     * @return r�ponse JAX-RS
     */
    @POST
    @Path("/{id}/badges")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBadgeOfUser(Badge badge, @PathParam("id") long id, @QueryParam("apiKey") String apiKey) {
        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(id, key);

        if (endUser == null) {
            return SendUser.errorUserInvalid();
        }

        Badge managedBadge = badgeDAO.createAndReturnManagedEntity(badge);
        managedBadge.setApplication(applicationDAO.getApplicationByApiKey(key));
        endUser.getBadges().add(managedBadge);
        endUsersDAO.update(endUser);

        return SendUser.send201Created(new InfoObject("New badge successfully added"));
    }

    /**
     * Supprime un certain badge d'un certain utilisateur final
     * @param idUser id de l'utilisateur final
     * @param idBadge id du badge
     * @param apiKey cl� de l'application
     * @return r�ponse JAX-RS
     */
    @DELETE
    @Path("/{idUser}/badges/{idBadge}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBadgeOfUser(
            @PathParam("idUser") long idUser,
            @PathParam("idBadge") long idBadge,
            @QueryParam("apiKey") String apiKey) {
        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(idUser, key);

        if (endUser == null) {
            return SendUser.errorUserInvalid();
        }

        Badge badge = badgeDAO.getBadgeByIdAndByApiKey(idBadge, key);

        if (badge == null) {
            return SendBadge.errorBadgeInvalid();
        }

        String infoMsg = "Badge successfully deleted";
        if (!endUser.getBadges().remove(badge)) {
            infoMsg = "Nothing deleted, the user doesn't have this badge.";
        }

        endUsersDAO.update(endUser);

        return SendUser.send200OK(new InfoObject(infoMsg));
    }

    /**
     * R�cup�re tous les prix d'un certain utilisateur final
     * @param id id de l'utilisateur
     * @param apiKey cl� de l'application
     * @return r�spone JAX-RS
     */
    @GET
    @Path("/{id}/rewards")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRewardsOfUser(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {
        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(id, key);

        if (endUser == null) {
            return SendUser.errorUserInvalid();
        }

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

        return SendUser.send200OK(rewardsDTO);
    }

    /**
     * Cr�er un prix pour un certain utilisateur final
     * @param reward prix � cr�er
     * @param id id de l'utilisateur final
     * @param apiKey cl� de l'application
     * @return r�ponse JAX-RS
     */
    @POST
    @Path("/{id}/rewards")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRewardOfUser(Reward reward, @PathParam("id") long id, @QueryParam("apiKey") String apiKey) {
        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(id, key);

        if (endUser == null) {
            return SendUser.errorUserInvalid();
        }

        Reward managedReward = rewardDAO.createAndReturnManagedEntity(reward);
        managedReward.setApplication(applicationDAO.getApplicationByApiKey(key));
        endUser.getRewards().add(managedReward);
        endUsersDAO.update(endUser);

        return SendUser.send201Created(new InfoObject("New reward successfully added"));
    }

    /**
     * Supprime un certain prix d'un certain utilisateur final
     * @param idUser id de l'utilisateur final
     * @param idReward id du prix
     * @param apiKey cl� de l'application
     * @return r�ponse JAX-RS
     */
    @DELETE
    @Path("/{idUser}/rewards/{idReward}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRewardOfUser(
            @PathParam("idUser") long idUser,
            @PathParam("idReward") long idReward,
            @QueryParam("apiKey") String apiKey)
    {
        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        EndUser endUser = endUsersDAO.getEndUserByIdAndByApiKey(idUser, key);

        if (endUser == null) {
            return SendUser.errorUserInvalid();
        }

        Reward reward = rewardDAO.getRewardByIdAndByApiKey(idReward, key);

        if (reward == null) {
            return SendReward.errorRewardInvalid();
        }

        String infoMsg = "Reward successfully deleted";
        if (!endUser.getRewards().remove(reward)) {
            infoMsg = "Nothing deleted, the user doesn't have this reward.";
        }

        endUsersDAO.update(endUser);

        return SendUser.send200OK(new InfoObject(infoMsg));
    }

}
