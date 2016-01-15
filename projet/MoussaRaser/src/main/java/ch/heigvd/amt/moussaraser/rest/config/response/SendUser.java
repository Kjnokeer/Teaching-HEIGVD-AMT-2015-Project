/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : SendUser.java
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.message.ErrorObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SendUser extends SendResponse {

   public static Response errorUserInvalid() {
      return Response.status(Response.Status.UNAUTHORIZED)
              .entity(new ErrorObject("The user ID you requested was invalid"))
              .type(MediaType.APPLICATION_JSON)
              .build();
   }
   
}
