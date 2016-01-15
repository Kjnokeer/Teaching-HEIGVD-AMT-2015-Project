/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 16.10.2015
 * Fichier : RuleDAO.java
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Rule;
import java.util.List;
import javax.ejb.Stateless;

/**
 * DAO correspondant à l'entité (table) Application
 */
@Stateless
public class RuleDAO extends GenericDAO<Rule, Long> implements RuleDAOLocal {

   @Override
   public List<Rule> getAllRulesByApplication(Application app) {
      return em.createNamedQuery("Rule.getAllByApplication").setParameter("app", app).getResultList();
   }

   @Override
   public Rule getRuleByIdAndApiKey(Long ruleId, Application app) {
      return (Rule) em.createNamedQuery("Rule.getRuleByIdAndApplication").setParameter("id", ruleId).setParameter("app", app).getSingleResult();
   }

}
