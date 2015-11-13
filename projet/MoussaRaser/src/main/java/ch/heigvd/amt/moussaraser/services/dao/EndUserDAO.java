/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 16.10.2015
 * Fichier : ApplicationDAO.java
 */

package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.EndUser;
import java.util.List;
import javax.ejb.Stateless;

/**
 * DAO correspondant à l'entité (table) Application
 */
@Stateless
public class EndUserDAO extends GenericDAO<EndUser, Long> implements EndUserDAOLocal {

    @Override
    public Long getNumberOfEndUsersInApp(Application app) {
        return (Long)em.createNamedQuery("EndUser.getNumberOfEndUsersInApp").setParameter("app", app).getSingleResult();
    }

    @Override
    public List<EndUser> getEndUsersInApp(Application app) {
        return em.createNamedQuery("EndUser.getEndUsersInApp").setParameter("app", app).getResultList();
    }
    
}
