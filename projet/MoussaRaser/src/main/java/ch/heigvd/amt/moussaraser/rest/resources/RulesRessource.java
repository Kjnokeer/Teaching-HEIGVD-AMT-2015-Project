/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : RulesRessource.java
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import ch.heigvd.amt.moussaraser.model.entities.Rule;
import ch.heigvd.amt.moussaraser.rest.config.response.SendBadge;
import ch.heigvd.amt.moussaraser.rest.config.response.SendReward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendRule;
import ch.heigvd.amt.moussaraser.rest.config.response.message.InfoObject;
import ch.heigvd.amt.moussaraser.rest.dto.RuleDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.BadgeDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.RewardDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.RuleDAOLocal;

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
 * Classe resprésentant une ressource REST Rules et l'action pour certaines
 * méthodes HTTP :
 * - GET /rules
 * - POST /rules
 * - GET /rules/{id}
 * - PUT /rules/{id}
 * - DELETE /rules/{id}
 *
 * @author jermoret
 */
@Stateless
@Path("/rules")
public class RulesRessource {

    @EJB
    ApiKeyDAOLocal apiKeyDAO;

    @EJB
    ApplicationDAOLocal applicationDAO;

    @EJB
    RuleDAOLocal ruleDAO;

    @EJB
    BadgeDAOLocal badgeDAO;

    @EJB
    RewardDAOLocal rewardDAO;

    /**
     * Récupère toutes les règles
     *
     * @param apiKey
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRules(@QueryParam("apiKey") String apiKey) {
        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        Application application = applicationDAO.getApplicationByApiKey(key);

        List<Rule> rules = ruleDAO.getAllRulesByApplication(application);

        List<RuleDTO> rulesDTO = new ArrayList<>();

        for (Rule rule : rules) {
            rulesDTO.add(new RuleDTO(
                    rule.getId(),
                    rule.getName(),
                    rule.getEventType(),
                    rule.getPointsToAdd(),
                    rule.getBadgeToAdd() == null ? null : rule.getBadgeToAdd().getId(),
                    rule.getRewardToAdd() == null ? null : rule.getRewardToAdd().getId()
            ));
        }

        return SendRule.send200OK(rulesDTO);
    }

    /**
     * Crée une nouvelle règle
     *
     * @param apiKey
     * @param rule
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewRule(@QueryParam("apiKey") String apiKey, RuleDTO rule) {
        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (rule == null || rule.getName() == null || rule.getEventType() == null) {
            return SendRule.errorRuleInvalid();
        }

        if (rule.getBadgeIdToAdd() != null && badgeDAO.getBadgeByIdAndByApiKey(rule.getBadgeIdToAdd(), key) == null) {
            return SendBadge.errorBadgeInvalid();
        }

        if (rule.getRewardIdToAdd() != null && rewardDAO.getRewardByIdAndByApiKey(rule.getRewardIdToAdd(), key) == null) {
            return SendReward.errorRewardInvalid();
        }

        if (rule.getPointsToAdd() == null && rule.getBadgeIdToAdd() == null && rule.getRewardIdToAdd() == null) {
            return SendRule.missingDataInPayload();
        }

        Badge badgeToAdd = badgeDAO.getBadgeByIdAndByApiKey(rule.getBadgeIdToAdd(), key);
        Reward rewardToAdd = rewardDAO.getRewardByIdAndByApiKey(rule.getRewardIdToAdd(), key);

        Rule newRule = new Rule(
                rule.getName(),
                rule.getEventType(),
                rule.getPointsToAdd(),
                badgeToAdd,
                rewardToAdd,
                applicationDAO.getApplicationByApiKey(key)
        );


        rule.setId(ruleDAO.create(newRule));

        return SendRule.send201Created(rule);
    }

    /**
     * Récupère une certaine règle
     *
     * @param ruleId
     * @param apiKey
     * @return
     */
    @GET
    @Path("/{ruleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetailsRule(@PathParam("ruleId") long ruleId, @QueryParam("apiKey") String apiKey) {
        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        Rule rule = ruleDAO.getRuleByIdAndApiKey(ruleId, applicationDAO.getApplicationByApiKey(key));

        if (rule == null) {
            return SendRule.errorRuleInvalid();
        }

        return SendRule.send200OK(new RuleDTO(
                rule.getId(),
                rule.getName(),
                rule.getEventType(),
                rule.getPointsToAdd(),
                rule.getBadgeToAdd() == null ? null : rule.getBadgeToAdd().getId(),
                rule.getRewardToAdd() == null ? null : rule.getRewardToAdd().getId()
        ));
    }

    /** Modify une certaine règle
     * @param ruleId
     * @param apiKey
     * @param rule
     * @return
     */
    @PUT
    @Path("/{ruleId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBadge(@PathParam("ruleId") long ruleId, @QueryParam("apiKey") String apiKey, RuleDTO rule) {
        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        Rule tmp = ruleDAO.getRuleByIdAndApiKey(ruleId, applicationDAO.getApplicationByApiKey(key));

        if (tmp == null) {
            return SendRule.errorRuleInvalid();
        }

        Badge badgeToAdd = badgeDAO.getBadgeByIdAndByApiKey(rule.getBadgeIdToAdd(), key);
        Reward rewardToAdd = rewardDAO.getRewardByIdAndByApiKey(rule.getRewardIdToAdd(), key);

        Rule updateRule = ruleDAO.createAndReturnManagedEntity(tmp);
        updateRule.setName(rule.getName());
        updateRule.setEventType(rule.getEventType());
        updateRule.setPointsToAdd(rule.getPointsToAdd());
        updateRule.setBadgeToAdd(badgeToAdd);
        updateRule.setRewardToAdd(rewardToAdd);

        return SendRule.send200OK(new RuleDTO(
                updateRule.getId(),
                updateRule.getName(),
                updateRule.getEventType(),
                updateRule.getPointsToAdd(),
                updateRule.getBadgeToAdd() == null ? null : updateRule.getBadgeToAdd().getId(),
                updateRule.getRewardToAdd() == null ? null : updateRule.getRewardToAdd().getId()
        ));
    }

    /**
     * Supprime une certaine règle
     * @param ruleId
     * @param apiKey
     * @return
     */
    @DELETE
    @Path("/{ruleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBadge(@PathParam("ruleId") long ruleId, @QueryParam("apiKey") String apiKey) {
        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        Rule rule = ruleDAO.getRuleByIdAndApiKey(ruleId, applicationDAO.getApplicationByApiKey(key));

        if (rule == null) {
            return SendRule.errorRuleInvalid();
        }

        ruleDAO.delete(rule);

        return SendReward.send200OK(new InfoObject("Rule successfully deleted"));
    }

}
