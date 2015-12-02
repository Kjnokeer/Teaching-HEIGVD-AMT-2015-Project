/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date
 * : 16.10.2015 Fichier : ApplicationDAO.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * DAO correspondant à l'entité (table) Application
 */
@Stateless
public class ApiKeyDAO extends GenericDAO<ApiKey, Long> implements ApiKeyDAOLocal {

    @Override
    public ApiKey findByApiKeyString(String apiKeyStr) {
        try {
            return (ApiKey) em.createNamedQuery("ApiKey.findByApiKeyStr").setParameter("apiKey", apiKeyStr).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
