package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.User;
import javax.ejb.Local;

@Local
public interface UsersDAOLocal extends IGenericDAO<User, Long> {

   User login(String username, String password);

   User getUserFromId(Long id);

   User getManagedUserFromId(Long id);

}
