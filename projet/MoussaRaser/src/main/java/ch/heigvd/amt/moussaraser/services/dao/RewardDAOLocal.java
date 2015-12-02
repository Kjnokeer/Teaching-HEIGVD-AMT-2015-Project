/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date
 * : 16.10.2015 Fichier : ApplicationDAOLocal.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface pour le DAO ApplicationDAO
 */
@Local
public interface RewardDAOLocal extends IGenericDAO<Reward, Long> {

    List<Reward> getRewardsByApiKey(ApiKey apiKey);

    Reward getRewardByIdAndByApiKey(Long id, ApiKey apiKey);
}
