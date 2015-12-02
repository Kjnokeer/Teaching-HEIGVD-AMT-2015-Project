/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date
 * : 16.10.2015 Fichier : UsersDAOLocal.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.User;
import javax.ejb.Local;

/**
 * Interface pour le DAO UsersDAO
 */
@Local
public interface UsersDAOLocal extends IGenericDAO<User, Long> {

    User login(String username, String password);

    User getUserFromId(Long id);

    User getManagedUserFromId(Long id);
}
