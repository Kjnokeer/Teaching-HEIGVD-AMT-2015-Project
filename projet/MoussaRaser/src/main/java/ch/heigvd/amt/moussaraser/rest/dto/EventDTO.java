/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : EventDTO.java
 */
package ch.heigvd.amt.moussaraser.rest.dto;

/**
 * Classe représentant un DTO de l'entité Event
 *
 * @author jermoret
 */
public class EventDTO {
   
   private Long toUserId;
   private String eventType;

   /** Constructs a new EventDTO. */
   public EventDTO() {
   }

   public EventDTO(Long id, String type) {
      this.toUserId = id;
      this.eventType = type;
   }

   /**
    * Getter for property 'toUserId'.
    *
    * @return Value for property 'toUserId'.
    */
   public Long getToUserId() {
      return toUserId;
   }

   /**
    * Setter for property 'toUserId'.
    *
    * @param id Value to set for property 'toUserId'.
    */
   public void setToUserId(Long id) {
      this.toUserId = id;
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
}
