/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.error.ErrorObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mathias
 */
public class SendApiKey extends SendResponse {
   
   public static Response errorApiKeyNotProvided() {
      return Response.status(401)
                .entity(new ErrorObject("No API key was provided with the API request"))
                .type("application/json")
                .build();
   }
   
   public static Response errorApiKeyInvalid() {
      return Response.status(401)
                .entity(new ErrorObject("An invalid API key was provided with the API request"))
                .type("application/json")
                .build();
   }

}
