package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import ch.heigvd.amt.moussaraser.model.entities.Rule;
import ch.heigvd.amt.moussaraser.rest.config.response.SendBadge;
import ch.heigvd.amt.moussaraser.rest.config.response.SendReward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendRule;
import ch.heigvd.amt.moussaraser.rest.dto.BadgeDTO;
import ch.heigvd.amt.moussaraser.rest.dto.EndUserDTO;
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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

   @GET
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
                 rule.getBadgeToAdd(),
                 rule.getRewardToAdd()
         ));
      }

      return SendRule.send200OK(rulesDTO);
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createNewRule(@QueryParam("apiKey") String apiKey, RuleDTO ruleDTO) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      if (ruleDTO == null || ruleDTO.getName() == null || ruleDTO.getEventType() == null) {
         return SendRule.errorRuleInvalid();
      }

      if (ruleDTO.getBadgeIdToAdd() != null && badgeDAO.getBadgeByIdAndByApiKey(ruleDTO.getBadgeIdToAdd(), key) == null) {
         return SendBadge.errorBadgeInvalid();
      }

      if (ruleDTO.getRewardIdToAdd() != null && rewardDAO.getRewardByIdAndByApiKey(ruleDTO.getRewardIdToAdd(), key) == null) {
         return SendReward.errorRewardInvalid();
      }

      Rule newRule = new Rule(
              ruleDTO.getName(),
              ruleDTO.getEventType(),
              ruleDTO.getPointsToAdd(),
              ruleDTO.getBadgeIdToAdd(),
              ruleDTO.getRewardIdToAdd(),
              applicationDAO.getApplicationByApiKey(key)
      );

      
      ruleDTO.setId(ruleDAO.create(newRule));
      
      return SendRule.send201Created(ruleDTO);
   }

}
