/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : EventsRessource.java
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.model.entities.Rule;
import ch.heigvd.amt.moussaraser.rest.config.response.SendEvent;
import ch.heigvd.amt.moussaraser.rest.dto.EventDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.BadgeDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.RewardDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.RuleDAOLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/events")
public class EventsRessource {

   @EJB
   EndUserDAOLocal endUsersDAO;
   
   @EJB
   RuleDAOLocal ruleDAO;

   @EJB
   ApplicationDAOLocal applicationDAO;

   @EJB
   ApiKeyDAOLocal apiKeyDAO;
   
   @EJB
   BadgeDAOLocal badgeDAO;
   
   @EJB
   RewardDAOLocal rewardDAO;
   
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   public Response notifyEvent(@QueryParam("apiKey") String apiKey, EventDTO eventDTO) {
      ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

      if (eventDTO == null || eventDTO.getToUserId()== null || eventDTO.getEventType()== null) {
         return SendEvent.errorEventInvalid();
      }
      
      Application application = applicationDAO.getApplicationByApiKey(key);
      List<Rule> rulesList = ruleDAO.getAllRulesByApplication(application);
      EndUser endUser = endUsersDAO.findById(eventDTO.getToUserId());
      
      for(Rule rule : rulesList) {
         if(rule.getEventType().equals(eventDTO.getEventType())) {
            if(rule.getPointsToAdd() != null) {
               endUser.setScore(endUser.getScore() + rule.getPointsToAdd());
            }
            if(rule.getBadgeToAdd() != null) {
               endUser.addBadge(badgeDAO.findById(rule.getBadgeToAdd().getId()));
            }
            if(rule.getRewardToAdd() != null) {
               endUser.addReward(rewardDAO.findById(rule.getRewardToAdd().getId()));
            }
         }
      }

      return SendEvent.send200OK(null);
   }
   
}
