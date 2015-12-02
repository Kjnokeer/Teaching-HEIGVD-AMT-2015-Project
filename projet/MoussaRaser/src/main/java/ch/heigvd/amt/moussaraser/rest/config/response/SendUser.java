/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.message.ErrorObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mathias
 */
public class SendUser extends SendResponse {

    public static Response errorUserInvalid() {
        return Response.status(401)
                .entity(new ErrorObject("The user ID you requested was invalid"))
                .type("application/json")
                .build();
    }
}
