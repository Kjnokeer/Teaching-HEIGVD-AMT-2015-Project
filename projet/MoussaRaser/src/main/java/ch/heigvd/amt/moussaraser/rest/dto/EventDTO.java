package ch.heigvd.amt.moussaraser.rest.dto;

public class EventDTO {
   
   private Long id;
   private String type;

   public EventDTO() {
   }

   public EventDTO(Long id, String type) {
      this.id = id;
      this.type = type;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }
   
}
