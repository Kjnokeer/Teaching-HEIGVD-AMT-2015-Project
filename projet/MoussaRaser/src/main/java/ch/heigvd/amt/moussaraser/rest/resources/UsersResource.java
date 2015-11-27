/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.rest.dto.EndUserDTO;
import ch.heigvd.amt.moussaraser.services.dao.ApiKeyDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.ApplicationDAOLocal;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
@Path("/users")
public class UsersResource {

    @EJB
    EndUserDAOLocal endUsersDAO;

    @EJB
    ApplicationDAOLocal applicationDAO;

    @EJB
    ApiKeyDAOLocal apiKeyDAO;

    @GET
    @Produces("application/json")
    public List<EndUserDTO> getUsers(@QueryParam("apiKey") String apiKey) {
        List<EndUser> endUsers;
        List<EndUserDTO> endUsersDTO = new ArrayList<>();
        try {
            endUsers = endUsersDAO.getEndUsersByApiKey(apiKey);
        }
        catch(Exception e) {
            return endUsersDTO;
        }
        

        for (EndUser endUser : endUsers) {
            endUsersDTO.add(new EndUserDTO(endUser.getFirstName(), endUser.getLastName()));
        }

        return endUsersDTO;
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public EndUserDTO getUser(@PathParam("id") long id) {
        EndUser endUser = endUsersDAO.findById(id);
        return new EndUserDTO(endUser.getFirstName(), endUser.getLastName());
    }

}
