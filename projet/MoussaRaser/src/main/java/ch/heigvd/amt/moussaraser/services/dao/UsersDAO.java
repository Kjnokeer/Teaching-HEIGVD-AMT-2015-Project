/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author thibaud
 */
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
   public User getFromId(Long id) {
      User u = (User) em.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
      return createAndReturnManagedEntity(u);
   }

}
