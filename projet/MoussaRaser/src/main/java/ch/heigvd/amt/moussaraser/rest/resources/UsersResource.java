/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.rest.dto.EndUserDTO;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
@Path("/users")
public class UsersResource {

    @EJB
    EndUserDAOLocal endUsersDAO;

    @GET
    @Produces("application/json")
    public List<EndUserDTO> getUsers() {
        List<EndUser> endUsers = endUsersDAO.findAll();
        List<EndUserDTO> endUsersDTO = new ArrayList<>();

        for (EndUser endUser : endUsers) {
            endUsersDTO.add(new EndUserDTO(endUser.getFirstName(), endUser.getLastName()));
        }

        return endUsersDTO;
    }

    @Path("/{id}")
    public EndUser getUser(@PathParam("id") long id) {
        return endUsersDAO.findById(id);
    }
}
