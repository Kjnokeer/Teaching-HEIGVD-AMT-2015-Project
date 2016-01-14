package ch.heigvd.amt.moussaraser.rest.dto;

public class EventDTO {
   
   private String type;
   private Long userId;

   public EventDTO() {
   }

   public EventDTO(String type, Long userId) {
      this.type = type;
      this.userId = userId;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }
   
}
