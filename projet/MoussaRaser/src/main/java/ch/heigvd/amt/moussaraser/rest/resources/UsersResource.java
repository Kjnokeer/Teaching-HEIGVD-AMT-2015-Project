/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import ch.heigvd.amt.moussaraser.services.dao.EndUserDAOLocal;
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
  public List<EndUser> getUsers() {
    return endUsersDAO.findAll();
  }

  @Path("/{id}")
  public EndUser getUser(@PathParam("id") long id) {
    return endUsersDAO.findById(id);
  }

  public class EndUserResource {

    private long id;

    public EndUserResource(long id) {
      this.id = id;
    }

    @GET
    @Produces("application/json")
    public EndUser getBeer() {
      return new EndUser();
    }

  }
}

