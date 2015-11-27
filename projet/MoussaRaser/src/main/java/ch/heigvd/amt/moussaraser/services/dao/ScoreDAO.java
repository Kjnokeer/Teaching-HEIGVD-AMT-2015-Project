/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 16.10.2015
 * Fichier : ApplicationDAO.java
 */

package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.ApiKey;
import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.model.entities.LeaderBoard;
import ch.heigvd.amt.moussaraser.model.entities.Score;
import ch.heigvd.amt.moussaraser.model.entities.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 * DAO correspondant à l'entité (table) Application
 */
@Stateless
public class ScoreDAO extends GenericDAO<Score, Long> implements ScoreDAOLocal {

}
