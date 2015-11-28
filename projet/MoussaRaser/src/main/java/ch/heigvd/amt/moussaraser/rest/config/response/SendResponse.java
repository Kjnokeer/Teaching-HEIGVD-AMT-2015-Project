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
public class SendResponse {
   
   public static Response send200OK(Object toSend) {
      return Response.status(200)
              .entity(toSend)
              .type("application/json")
              .build();
   }
   
}
