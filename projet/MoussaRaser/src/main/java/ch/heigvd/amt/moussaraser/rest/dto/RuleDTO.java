/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date :
 * 29.11.2015 Fichier : RewardDTO.java
 */
package ch.heigvd.amt.moussaraser.rest.dto;

import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import ch.heigvd.amt.moussaraser.rest.config.EventsEnumeration;
import java.util.List;

/**
 * Classe représentant un DTO de l'entité Reward
 *
 * @author jermoret
 */
public class RuleDTO {

   private Long id;
   private String name;
   private EventsEnumeration eventType;
   private Long pointsToAdd;
   private Long badgeIdToAdd;
   private Long rewardIdToAdd;

   public RuleDTO() {
   }

   public RuleDTO(Long id, String name, EventsEnumeration eventType, Long pointsToAdd, Long badgeIdToAdd, Long rewardIdToAdd) {
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

   public EventsEnumeration getEventType() {
      return eventType;
   }

   public void setEventType(EventsEnumeration eventType) {
      this.eventType = eventType;
   }

   public Long getAddPoint() {
      return pointsToAdd;
   }

   public void setAddPoint(Long pointsToAdd) {
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
