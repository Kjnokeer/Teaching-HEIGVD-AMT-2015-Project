/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : Rule.java
 */
package ch.heigvd.amt.moussaraser.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
   @NamedQuery(name = "Rule.getAllByApplication", query = "SELECT r FROM Rule r WHERE r.application = :app"),
   @NamedQuery(name = "Rule.getRuleByIdAndApplication", query = "SELECT r FROM Rule r WHERE r.id = :id AND r.application = :app")
})

/**
 * Cette classe est un JPA, elle représente l'entité (table) Rule.
 */
public class Rule extends AbstractDomainModelEntity<Long> {

   @NotNull
   private String name;

   @NotNull
   private String eventType;

   private Long pointsToAdd;
   
   @ManyToOne
   private Badge badgeToAdd;
   
   @ManyToOne
   private Reward rewardToAdd;

   @ManyToOne
   private Application application;

   public Rule() {
   }

   public Rule(String name, String eventType, Long pointsToAdd, Badge badgeToAdd, Reward rewardToAdd, Application application) {
      this.name = name;
      this.eventType = eventType;
      this.pointsToAdd = pointsToAdd;
      this.badgeToAdd = badgeToAdd;
      this.rewardToAdd = rewardToAdd;
      this.application = application;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEventType() {
      return eventType;
   }

   public void setEventType(String eventType) {
      this.eventType = eventType;
   }

   public Long getPointsToAdd() {
      return pointsToAdd;
   }

   public void setPointsToAdd(Long pointsToAdd) {
      this.pointsToAdd = pointsToAdd;
   }

   public Badge getBadgeToAdd() {
      return badgeToAdd;
   }

   public void setBadgeToAdd(Badge badgeToAdd) {
      this.badgeToAdd = badgeToAdd;
   }

   public Reward getRewardToAdd() {
      return rewardToAdd;
   }

   public void setRewardToAdd(Reward rewardToAdd) {
      this.rewardToAdd = rewardToAdd;
   }

   public Application getApplication() {
      return application;
   }

   public void setApplication(Application application) {
      this.application = application;
   }

}
