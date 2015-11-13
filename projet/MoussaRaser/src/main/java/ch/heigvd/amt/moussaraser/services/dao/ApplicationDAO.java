/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 16.10.2015
 * Fichier : ApplicationDAO.java
 */

package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 * DAO correspondant à l'entité (table) Application
 */
@Stateless
public class ApplicationDAO extends GenericDAO<Application, Long> implements ApplicationDAOLocal {

   @Override
   public List<Application> getAllApplicationsForUser(User u) {
      return em.createNamedQuery("Application.findAllByUser").setParameter("user", u).getResultList();
   }

   @Override
   public Application getManagedApplicationByApiKey(ApiKey apiKey) {
      return createAndReturnManagedEntity((Application) em.createNamedQuery("Application.findByApiKey").setParameter("apiKey", apiKey).getSingleResult());
   }
}
