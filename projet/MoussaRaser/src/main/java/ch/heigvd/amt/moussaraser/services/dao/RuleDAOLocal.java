/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : RuleDAOLocal.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Rule;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RuleDAOLocal extends IGenericDAO<Rule, Long> {

   public List<Rule> getAllRulesByApplication(Application app);

}
