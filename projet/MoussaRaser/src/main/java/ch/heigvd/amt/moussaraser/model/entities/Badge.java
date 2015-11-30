/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 09.10.2015
 * Fichier : User.java
 */

package ch.heigvd.amt.moussaraser.model.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
   @NamedQuery(name = "Badge.getAllByApplication", query = "SELECT b FROM Badge b WHERE b.application = :app"),
   @NamedQuery(name = "Badge.getByIdAndByApplication", query = "SELECT b FROM Badge b WHERE b.id = :id AND b.application = :app"),
})

/**
 * Cette classe est un JPA, elle représente l'entité (table) User.
 */
public class Badge extends AbstractDomainModelEntity<Long> {

   @NotNull private String name;
   @NotNull private String category;
   private String description;
   private String image;
   
   @ManyToOne
   private Application application;

   public Badge() {
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

   public Application getApplication() {
      return application;
   }

   public void setApplication(Application application) {
      this.application = application;
   }
   
}
