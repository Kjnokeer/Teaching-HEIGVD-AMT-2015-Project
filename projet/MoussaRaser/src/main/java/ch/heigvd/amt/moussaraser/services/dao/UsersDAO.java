package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.User;
import javax.ejb.Stateless;

@Stateless
public class UsersDAO extends GenericDAO<User, Long> implements UsersDAOLocal {

   @Override
   public User login(String email, String password) {
      try {
         User u = (User) em.createNamedQuery("User.findByEmailAndPassword").setParameter("email", email).setParameter("pass", password).getSingleResult();
         return u;
      } catch (Exception e) {
         return null;
      }
   }

   @Override
   public User getUserFromId(Long id) {
      return (User) em.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
   }

   @Override
   public User getManagedUserFromId(Long id) {
      User u = (User) em.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
      return createAndReturnManagedEntity(u);
   }

}
