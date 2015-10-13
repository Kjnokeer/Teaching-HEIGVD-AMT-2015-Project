/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
  //@NamedQuery(name = "User.findByNameAndPassword", query = "SELECT u FROM User u WHERE u.firstName = :fname AND u.password = :pass"),
})
/**
 *
 * @author jermoret
 */
public class Application extends AbstractDomainModelEntity<Long> {
   private String name;
   private String description;
   private String apiKey;
   private boolean enabled;
   
   @ManyToOne
   private User creator;


   public Application() {
   }

   /**
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return the description
    */
   public String getDescription() {
      return description;
   }

   /**
    * @param description the description to set
    */
   public void setDescription(String description) {
      this.description = description;
   }

   /**
    * @return the enabled
    */
   public boolean isEnabled() {
      return enabled;
   }

   /**
    * @param enabled the enabled to set
    */
   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }   
}