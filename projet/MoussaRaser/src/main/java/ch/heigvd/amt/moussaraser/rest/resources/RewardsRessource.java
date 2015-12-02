package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendApiKey;
import ch.heigvd.amt.moussaraser.rest.config.response.SendReward;
import ch.heigvd.amt.moussaraser.rest.config.response.message.InfoObject;
import ch.heigvd.amt.moussaraser.rest.dto.BadgeDTO;
import ch.heigvd.amt.moussaraser.rest.dto.RewardDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.RewardDAOLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ch.heigvd.amt.moussaraser.web.utils.EncryptionManager;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

@Stateless
@Path("/rewards")
public class RewardsRessource {

    @EJB
    RewardDAOLocal rewardDAO;

    @EJB
    ApplicationDAOLocal applicationDAO;

    @EJB
    ApiKeyDAOLocal apiKeyDAO;

    @GET
    @Produces("application/json")
    public Response getRewards(@QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

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

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createReward(Reward reward, @QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        reward.setApplication(applicationDAO.getApplicationByApiKey(key));

        rewardDAO.create(reward);

        return SendReward.send200OK(new BadgeDTO(
                reward.getId(),
                reward.getName(),
                reward.getCategory(),
                reward.getDescription(),
                reward.getImage()
        ));
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getBadge(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

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

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateBadge(Reward reward, @PathParam("id") long id, @QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

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

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteBadge(@PathParam("id") long id, @QueryParam("apiKey") String apiKey) {

        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        Reward reward = rewardDAO.getRewardByIdAndByApiKey(id, key);

        if (reward == null) {
            return SendReward.errorRewardInvalid();
        }

        rewardDAO.delete(reward);

        return SendReward.send200OK(new InfoObject("Reward successfully deleted"));
    }

}
