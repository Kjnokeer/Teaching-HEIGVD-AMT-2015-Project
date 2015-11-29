package ch.heigvd.amt.moussaraser.rest.dto;

/**
 *
 * @author Jérôme */
public class RewardDTO {
   
   private Long id;
   private String name;
   private String category;
   private String description;
   private String image;

   public RewardDTO(Long id, String name, String category, String description, String image) {
      this.name = name;
      this.category = category;
      this.description = description;
      this.image = image;
      this.id = id;
   }

   public RewardDTO() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   
}
