/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 16.10.2015
 * Fichier : ApplicationDAOLocal.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.User;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface pour le DAO ApplicationDAO
 */
@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application, Long> {

    List<Application> getAllApplicationsForUser(User u);

    Application getApplicationByApiKey(ApiKey apiKey);

    Application getManagedApplicationByApiKey(ApiKey apiKey);
}
