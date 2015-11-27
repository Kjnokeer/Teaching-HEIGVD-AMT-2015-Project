/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 16.10.2015
 * Fichier : ApplicationDAO.java
 */

package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * DAO correspondant à l'entité (table) Application
 */
@Stateless
public class BadgeDAO extends GenericDAO<Badge, Long> implements BadgeDAOLocal {
   
   @EJB
   ApplicationDAOLocal applicationDAO;
   
   @EJB
   ApiKeyDAOLocal apiKeyDAO;

   @Override
   public List<Badge> getBadgesByApiKey(String apiKey) {
      Application app = applicationDAO.getApplicationByApiKey(apiKeyDAO.findByApiKeyString(apiKey));
      
      return em.createNamedQuery("Badge.getAllByApplication").setParameter("app", app).getResultList();
   }

}
