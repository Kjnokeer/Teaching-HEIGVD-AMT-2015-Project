package ch.heigvd.amt.moussaraser.rest.dto;

public class EventDTO {
   
   private Long toUserId;
   private String eventType;

   public EventDTO() {
   }

   public EventDTO(Long id, String type) {
      this.toUserId = id;
      this.eventType = type;
   }

   public Long getToUserId() {
      return toUserId;
   }

   public void setToUserId(Long id) {
      this.toUserId = id;
   }

   public String getEventType() {
      return eventType;
   }

   public void setEventType(String eventType) {
      this.eventType = eventType;
   }
}
