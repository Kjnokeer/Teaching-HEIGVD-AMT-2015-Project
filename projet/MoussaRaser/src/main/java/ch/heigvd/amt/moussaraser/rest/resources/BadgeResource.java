package ch.heigvd.amt.moussaraser.rest.resources;

import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.services.dao.BadgeDAOLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("/badges")
public class BadgeResource {

   @EJB
   BadgeDAOLocal badgeDAO;

   @GET
   @Produces("application/json")
   public List<Badge> getBadges() {
      return badgeDAO.findAll();
   }
   
   @GET
   @Path("/{id}")
   @Produces("application/json")
   public Badge getBadge(@PathParam("id") long id) {
      return badgeDAO.findById(id);
   }
}
