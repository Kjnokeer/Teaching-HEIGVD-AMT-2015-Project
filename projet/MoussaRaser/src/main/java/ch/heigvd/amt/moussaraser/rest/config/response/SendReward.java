/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : SendReward.java
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.message.ErrorObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jérôme
 */
public class SendReward extends SendResponse {

    public static Response errorRewardInvalid() {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorObject("The reward ID you requested was invalid"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
