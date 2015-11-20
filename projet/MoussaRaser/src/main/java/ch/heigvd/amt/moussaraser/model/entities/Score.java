/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 09.10.2015
 * Fichier : User.java
 */

package ch.heigvd.amt.moussaraser.model.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({

})

/**
 * Cette classe est un JPA, elle représente l'entité (table) User.
 */
public class Score extends AbstractDomainModelEntity<Long> {

   private int score;
   
   @ManyToOne
   LeaderBoard leaderBoard;

   public Score() {
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }   
}
