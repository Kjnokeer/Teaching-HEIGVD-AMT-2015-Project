/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 16.10.2015
 * Fichier : RewardDAO.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * DAO correspondant à l'entité (table) Application
 */
@Stateless
public class RewardDAO extends GenericDAO<Reward, Long> implements RewardDAOLocal {

    @EJB
    ApplicationDAOLocal applicationDAO;

    @Override
    public List<Reward> getRewardsByApiKey(ApiKey apiKey) {
        Application app = applicationDAO.getApplicationByApiKey(apiKey);
        return em.createNamedQuery("Reward.getAllByApplication").setParameter("app", app).getResultList();
    }

    @Override
    public Reward getRewardByIdAndByApiKey(Long id, ApiKey apiKey) {
        Application app = applicationDAO.getApplicationByApiKey(apiKey);

        try {
            return (Reward) em.createNamedQuery("Reward.getByIdAndByApplication").setParameter("id", id).setParameter("app", app).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
