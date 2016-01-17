/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : RuleDTO.java
 */
package ch.heigvd.amt.moussaraser.rest.dto;

/**
 * Classe représentant un DTO de l'entité Rule
 *
 * @author jermoret
 */
public class RuleDTO {

   private Long id;
   private String name;
   private String eventType;
   private Long pointsToAdd;
   private Long badgeIdToAdd;
   private Long rewardIdToAdd;

   /** Constructs a new RuleDTO. */
   public RuleDTO() {
   }

   public RuleDTO(Long id, String name, String eventType, Long pointsToAdd, Long badgeIdToAdd, Long rewardIdToAdd) {
      this.id = id;
      this.name = name;
      this.eventType = eventType;
      this.pointsToAdd = pointsToAdd;
      this.badgeIdToAdd = badgeIdToAdd;
      this.rewardIdToAdd = rewardIdToAdd;
   }

   /**
    * Getter for property 'id'.
    *
    * @return Value for property 'id'.
    */
   public Long getId() {
      return id;
   }

   /**
    * Setter for property 'id'.
    *
    * @param id Value to set for property 'id'.
    */
   public void setId(Long id) {
      this.id = id;
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
    * Getter for property 'badgeIdToAdd'.
    *
    * @return Value for property 'badgeIdToAdd'.
    */
   public Long getBadgeIdToAdd() {
      return badgeIdToAdd;
   }

   /**
    * Setter for property 'badgeIdToAdd'.
    *
    * @param badgeIdToAdd Value to set for property 'badgeIdToAdd'.
    */
   public void setBadgeIdToAdd(Long badgeIdToAdd) {
      this.badgeIdToAdd = badgeIdToAdd;
   }

   /**
    * Getter for property 'rewardIdToAdd'.
    *
    * @return Value for property 'rewardIdToAdd'.
    */
   public Long getRewardIdToAdd() {
      return rewardIdToAdd;
   }

   /**
    * Setter for property 'rewardIdToAdd'.
    *
    * @param rewardIdToAdd Value to set for property 'rewardIdToAdd'.
    */
   public void setRewardIdToAdd(Long rewardIdToAdd) {
      this.rewardIdToAdd = rewardIdToAdd;
   }

}
