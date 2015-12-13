/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.config.response;

import ch.heigvd.amt.moussaraser.rest.config.response.message.ErrorObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mathias
 */
public class SendResponse {

    public static Response send200OK(Object toSend) {
        return Response.status(Response.Status.OK)
                .entity(toSend)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response send201Created(Object toSend) {
        return Response.status(Response.Status.CREATED)
                .entity(toSend)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response missingDataInPayload() {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorObject("Missing payload or an invalid payload was provided"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
