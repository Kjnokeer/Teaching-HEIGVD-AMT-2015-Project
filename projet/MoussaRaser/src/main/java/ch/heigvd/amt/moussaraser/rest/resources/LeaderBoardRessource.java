package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import ch.heigvd.amt.moussaraser.rest.config.response.SendApiKey;
import ch.heigvd.amt.moussaraser.rest.config.response.SendBadge;
import ch.heigvd.amt.moussaraser.rest.config.response.SendReward;
import ch.heigvd.amt.moussaraser.rest.config.response.message.InfoObject;
import ch.heigvd.amt.moussaraser.rest.dto.BadgeDTO;
import ch.heigvd.amt.moussaraser.rest.dto.RewardDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.BadgeDAOLocal;
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
@Path("/leaderboard")
public class LeaderBoardRessource {

   @GET
   @Produces("application/json")
   public Response getLeaderBoard() {
      return null;
   }  
}
