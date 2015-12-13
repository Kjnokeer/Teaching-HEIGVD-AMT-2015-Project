/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date :
 * 09.10.2015 Fichier : EndUser.java
 */
package ch.heigvd.amt.moussaraser.model.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
   @NamedQuery(name = "EndUser.getNumberOfEndUsersInApp", query = "SELECT COUNT(eu) FROM EndUser eu WHERE eu.application = :app"),
   @NamedQuery(name = "EndUser.getEndUsersInApp", query = "SELECT eu FROM EndUser eu WHERE eu.application = :app"),
   @NamedQuery(name = "EndUser.getEndUserByIdAndApp", query = "SELECT eu FROM EndUser eu WHERE eu.id = :id AND eu.application = :app"),
   @NamedQuery(name = "EndUser.getNumberEndUserRegisteredLast30Days", query = "SELECT COUNT(eu) FROM EndUser eu WHERE eu.registrationDate >= :date")
})

/**
 * Cette classe est un JPA, elle représente l'entité (table) EndUser.
 */
public class EndUser extends AbstractDomainModelEntity<Long> {

   @NotNull
   private String firstName;
   @NotNull
   private String lastName;

   private long score = 0;

   @ManyToOne
   private Application application;

   @ManyToMany
   private List<Badge> badges;

   @ManyToMany
   private List<Reward> rewards;

   @NotNull
   @Temporal(TemporalType.TIMESTAMP)
   private Date registrationDate = new Date();

   /**
    * Constructeur vide
    */
   public EndUser() {
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @param firstName the firstName to set
    */
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param lastName the lastName to set
    */
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   /**
    * @return the the registration date
    */
   public Date getRegistrationDate() {
      return registrationDate;
   }

   /**
    * @param registrationDate the registration date to set
    */
   public void setRegistrationDate(Date registrationDate) {
      this.registrationDate = registrationDate;
   }

   /**
    * @return the application
    */
   public Application getApplication() {
      return application;
   }

   /**
    * @param application the application to set
    */
   public void setApplication(Application application) {
      this.application = application;
   }

   /**
    * @return the list of badges
    */
   public List<Badge> getBadges() {
      return badges;
   }

   /**
    * @param badges the list of badges to set
    */
   public void setBadges(List<Badge> badges) {
      this.badges = badges;
   }

   public void addBadge(Badge badge) {
      if (!badges.contains(badge)) {
         badges.add(badge);
      }
   }

   public void removeBadge(Badge badge) {
      if (badges.contains(badge)) {
         badges.remove(badge);
      }
   }

   /**
    * @return the liste of rewards
    */
   public List<Reward> getRewards() {
      return rewards;
   }

   /**
    * @param rewards the list of rewards to set
    */
   public void setRewards(List<Reward> rewards) {
      this.rewards = rewards;
   }

   public void addReward(Reward reward) {
      if (!rewards.contains(reward)) {
         rewards.add(reward);
      }
   }

   public void removeReward(Reward reward) {
      if (rewards.contains(reward)) {
         rewards.remove(reward);
      }
   }

   /**
    * @return the score
    */
   public long getScore() {
      return score;
   }

   /**
    * @param score the score to set
    */
   public void setScore(long score) {
      this.score = score;
   }
}
