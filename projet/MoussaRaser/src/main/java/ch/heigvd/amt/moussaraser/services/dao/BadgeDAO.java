/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 16.10.2015
 * Fichier : BadgeDAO.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * DAO correspondant à l'entité (table) Application
 */
@Stateless
public class BadgeDAO extends GenericDAO<Badge, Long> implements BadgeDAOLocal {

    @EJB
    ApplicationDAOLocal applicationDAO;

    @Override
    public List<Badge> getBadgesByApiKey(ApiKey apiKey) {
        Application app = applicationDAO.getApplicationByApiKey(apiKey);

        return em.createNamedQuery("Badge.getAllByApplication").setParameter("app", app).getResultList();
    }

    @Override
    public Badge getBadgeByIdAndByApiKey(Long id, ApiKey apiKey) {
        Application app = applicationDAO.getApplicationByApiKey(apiKey);

        try {
            return (Badge) em.createNamedQuery("Badge.getByIdAndByApplication").setParameter("id", id).setParameter("app", app).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
