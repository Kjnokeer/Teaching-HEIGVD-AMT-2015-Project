/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 09.10.2015
 * Fichier : Application.java
 */

package ch.heigvd.amt.moussaraser.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
   @NamedQuery(name = "Application.findAllByUser", query = "SELECT a FROM Application a WHERE a.creator = :user"),
   @NamedQuery(name = "Application.findByApiKey", query = "SELECT a FROM Application a WHERE a.apiKey = :apiKey"),
})

/**
 * Cette classe est un JPA, elle représente l'entité (table) Application.
 */
public class Application extends AbstractDomainModelEntity<Long> {

   private String name;
   private String description;
   private boolean enabled;
   
   @OneToOne
   private ApiKey apiKey;

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

   /**
    * @return the creator
    */
   public User getCreator() {
      return creator;
   }

   /**
    * @param creator the creator to set
    */
   public void setCreator(User creator) {
      this.creator = creator;
   }

   /**
    * @return the apiKey
    */
   public ApiKey getApiKey() {
      return apiKey;
   }

   /**
    * @param apiKey the apiKey to set
    */
   public void setApiKey(ApiKey apiKey) {
      this.apiKey = apiKey;
   }
   
}
