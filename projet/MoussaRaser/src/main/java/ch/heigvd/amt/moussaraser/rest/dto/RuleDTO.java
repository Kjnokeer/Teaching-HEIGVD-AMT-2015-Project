/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : RuleDTO.java
 */
package ch.heigvd.amt.moussaraser.rest.dto;

/**
 * Classe représentant un DTO de l'entité Reward
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

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public Long getBadgeIdToAdd() {
      return badgeIdToAdd;
   }

   public void setBadgeIdToAdd(Long badgeIdToAdd) {
      this.badgeIdToAdd = badgeIdToAdd;
   }

   public Long getRewardIdToAdd() {
      return rewardIdToAdd;
   }

   public void setRewardIdToAdd(Long rewardIdToAdd) {
      this.rewardIdToAdd = rewardIdToAdd;
   }

}
