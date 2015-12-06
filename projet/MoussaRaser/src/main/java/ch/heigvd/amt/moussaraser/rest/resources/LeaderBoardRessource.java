/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 29.11.2015
 * Fichier : LeaderBoardRessource.java
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.rest.config.response.SendApiKey;
import ch.heigvd.amt.moussaraser.rest.config.response.SendUser;
import ch.heigvd.amt.moussaraser.rest.dto.EndUserDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
import ch.heigvd.amt.moussaraser.web.utils.EncryptionManager;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe resprésentant une ressource REST LeaderBoard et l'action pour certaines
 * méthodes HTTP :
 * - GET /leaderboard
 * @author jermoret
 */
@Stateless
@Path("/leaderboard")
public class LeaderBoardRessource {

    @EJB
    ApiKeyDAOLocal apiKeyDAO;

    @EJB
    EndUserDAOLocal endUsersDAO;

    /**
     * Récupère le leaderboard actuel
     * @param apiKey clé de l'application
     * @return réponse JAX-RS
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLeaderBoard(@QueryParam("apiKey") String apiKey) {
        if (apiKey == null || apiKey.length() != EncryptionManager.getAPIKey().length()) {
            return SendApiKey.errorApiKeyNotProvided();
        }

        ApiKey key = apiKeyDAO.findByApiKeyString(apiKey);

        if (key == null) {
            return SendApiKey.errorApiKeyInvalid();
        }

        List<EndUser> endUsers = endUsersDAO.getLeaderboard(key);
        List<EndUserDTO> endUsersDTO = new ArrayList<>();

        for (EndUser endUser : endUsers) {
            endUsersDTO.add(new EndUserDTO(
                    endUser.getId(),
                    endUser.getFirstName(),
                    endUser.getLastName(),
                    endUser.getScore()));
        }

        // Renvoie une liste de end users triée par ordre décroissant
        return SendUser.send200OK(endUsersDTO);
    }
}
