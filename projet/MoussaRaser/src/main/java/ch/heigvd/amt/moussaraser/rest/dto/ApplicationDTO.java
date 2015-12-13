/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.dto;

/**
 *
 * @author Mathias
 */
public class ApplicationDTO {

   private String name;
   private String description;
   private boolean enabled;

   public ApplicationDTO() {
   }

   public ApplicationDTO(String name, String description, boolean enabled) {
      this.name = name;
      this.description = description;
      this.enabled = enabled;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

}
