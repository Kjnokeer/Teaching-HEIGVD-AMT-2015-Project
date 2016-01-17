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

   /** Constructs a new Rule. */
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

   /**
    * Getter for property 'name'.
    *
    * @return Value for property 'name'.
    */
   public String getName() {
      return name;
   }

   /**
    * Setter for property 'name'.
    *
    * @param name Value to set for property 'name'.
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Getter for property 'eventType'.
    *
    * @return Value for property 'eventType'.
    */
   public String getEventType() {
      return eventType;
   }

   /**
    * Setter for property 'eventType'.
    *
    * @param eventType Value to set for property 'eventType'.
    */
   public void setEventType(String eventType) {
      this.eventType = eventType;
   }

   /**
    * Getter for property 'pointsToAdd'.
    *
    * @return Value for property 'pointsToAdd'.
    */
   public Long getPointsToAdd() {
      return pointsToAdd;
   }

   /**
    * Setter for property 'pointsToAdd'.
    *
    * @param pointsToAdd Value to set for property 'pointsToAdd'.
    */
   public void setPointsToAdd(Long pointsToAdd) {
      this.pointsToAdd = pointsToAdd;
   }

   /**
    * Getter for property 'badgeToAdd'.
    *
    * @return Value for property 'badgeToAdd'.
    */
   public Badge getBadgeToAdd() {
      return badgeToAdd;
   }

   /**
    * Setter for property 'badgeToAdd'.
    *
    * @param badgeToAdd Value to set for property 'badgeToAdd'.
    */
   public void setBadgeToAdd(Badge badgeToAdd) {
      this.badgeToAdd = badgeToAdd;
   }

   /**
    * Getter for property 'rewardToAdd'.
    *
    * @return Value for property 'rewardToAdd'.
    */
   public Reward getRewardToAdd() {
      return rewardToAdd;
   }

   /**
    * Setter for property 'rewardToAdd'.
    *
    * @param rewardToAdd Value to set for property 'rewardToAdd'.
    */
   public void setRewardToAdd(Reward rewardToAdd) {
      this.rewardToAdd = rewardToAdd;
   }

   /**
    * Getter for property 'application'.
    *
    * @return Value for property 'application'.
    */
   public Application getApplication() {
      return application;
   }

   /**
    * Setter for property 'application'.
    *
    * @param application Value to set for property 'application'.
    */
   public void setApplication(Application application) {
      this.application = application;
   }

}
