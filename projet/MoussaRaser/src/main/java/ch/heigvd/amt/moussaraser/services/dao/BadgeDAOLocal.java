/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 16.10.2015
 * Fichier : BadgeDAOLocal.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface pour le DAO ApplicationDAO
 */
@Local
public interface BadgeDAOLocal extends IGenericDAO<Badge, Long> {

   List<Badge> getBadgesByApiKey(ApiKey apiKey);

   Badge getBadgeByIdAndByApiKey(Long id, ApiKey apiKey);

}
