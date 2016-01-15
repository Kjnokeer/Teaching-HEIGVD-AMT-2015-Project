/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : SendRule.java
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.message.ErrorObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SendRule extends SendResponse {

   public static Response errorRuleInvalid() {
      return Response.status(Response.Status.UNAUTHORIZED)
              .type(MediaType.APPLICATION_JSON)
              .entity(new ErrorObject("The rule's payload is incorrect"))
              .type(MediaType.APPLICATION_JSON)
              .build();
   }

}
