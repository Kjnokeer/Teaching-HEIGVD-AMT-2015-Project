/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : SendApiKey.java
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.message.ErrorObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mathias
 */
public class SendApiKey extends SendResponse {

    public static Response errorApiKeyNotProvided() {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorObject("No API key was provided with the API request"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response errorApiKeyInvalid() {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorObject("An invalid API key was provided with the API request"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
