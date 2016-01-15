/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : SendBadge.java
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.message.ErrorObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mathias
 */
public class SendBadge extends SendResponse {

    public static Response errorBadgeInvalid() {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorObject("The badge ID is invalid"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
