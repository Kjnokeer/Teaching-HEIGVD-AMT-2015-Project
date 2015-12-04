/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date
 * : 16.10.2015 Fichier : ApplicationDAOLocal.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface pour le DAO ApplicationDAO
 */
@Local
public interface EndUserDAOLocal extends IGenericDAO<EndUser, Long> {

    Long getNumberOfEndUsersInApp(Application app);

    List<EndUser> getEndUsersInApp(Application app);

    List<EndUser> getEndUsersByApiKey(ApiKey apiKey);

    EndUser getEndUserByIdAndByApiKey(Long id, ApiKey apiKey);

    Long getNumberEndUserRegisteredLast30Days();

    List<EndUser> getLeaderboard(ApiKey apiKey);
}
