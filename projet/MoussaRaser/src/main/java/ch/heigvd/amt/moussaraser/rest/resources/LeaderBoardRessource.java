package ch.heigvd.amt.moussaraser.rest.resources;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Stateless
@Path("/leaderboard")
public class LeaderBoardRessource {

    @GET
    @Produces("application/json")
    public Response getLeaderBoard() {
        return null;
    }
}
