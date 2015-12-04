/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date :
 * 16.10.2015 Fichier : ApplicationDAO.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * DAO correspondant à l'entité (table) Application
 */


@Stateless
public class EndUserDAO extends GenericDAO<EndUser, Long> implements EndUserDAOLocal {

   @EJB
   ApplicationDAOLocal applicationDAO;

   @Override
   public Long getNumberOfEndUsersInApp(Application app) {
      return (Long) em.createNamedQuery("EndUser.getNumberOfEndUsersInApp").setParameter("app", app).getSingleResult();
   }

   @Override
   public List<EndUser> getEndUsersInApp(Application app) {
      return em.createNamedQuery("EndUser.getEndUsersInApp").setParameter("app", app).getResultList();
   }

   @Override
   public List<EndUser> getEndUsersByApiKey(ApiKey apiKey) {
      Application application = applicationDAO.getManagedApplicationByApiKey(apiKey);

      return getEndUsersInApp(application);
   }

   @Override
   public EndUser getEndUserByIdAndByApiKey(Long id, ApiKey apiKey) {
      Application app = applicationDAO.getApplicationByApiKey(apiKey);

      try {
         return (EndUser) em.createNamedQuery("EndUser.getEndUserByIdAndApp").setParameter("id", id).setParameter("app", app).getSingleResult();
      } catch (NoResultException e) {
         return null;
      }
   }

   @Override
   public Long getNumberEndUserRegisteredLast30Days() {
      return (Long) em.createNamedQuery("EndUser.getNumberEndUserRegisteredLast30Days").setParameter("date", new Date(System.currentTimeMillis() - 30L * 24L * 60L * 60L * 1000L)).getSingleResult();
   }

   @Override
   public List<EndUser> getLeaderboard(ApiKey apiKey) {
      Application application = applicationDAO.getManagedApplicationByApiKey(apiKey);

      List<EndUser> endUsers = getEndUsersInApp(application);

      Collections.sort(endUsers, new Comparator<EndUser>() {
         public int compare(EndUser o1, EndUser o2) {
            if (o1.getScore() == o2.getScore()) {
               return 0;
            }
            return o1.getScore() < o2.getScore() ? 1 : -1;
         }
      });

      return endUsers;
   }

}
