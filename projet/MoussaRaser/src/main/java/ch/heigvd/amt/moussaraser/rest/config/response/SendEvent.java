/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : SendEvent.java
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.message.ErrorObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SendEvent extends SendResponse {
   
   public static Response errorEventInvalid() {
      return Response.status(Response.Status.UNAUTHORIZED)
               .type(MediaType.APPLICATION_JSON)
             .entity(new ErrorObject("The user ID or the type you requested was invalid"))
              .type(MediaType.APPLICATION_JSON)
              .build();
   }
   
   public static Response errorEventTypeInvalid() {
      return Response.status(Response.Status.UNAUTHORIZED)
              .entity(new ErrorObject("The type you requested was invalid"))
              .type(MediaType.APPLICATION_JSON)
              .build();
   }
   
   public static Response errorUserIdInvalid() {
      return Response.status(Response.Status.UNAUTHORIZED)
              .entity(new ErrorObject("The user ID you requested was invalid"))
              .type(MediaType.APPLICATION_JSON)
              .build();
   }
   
}
