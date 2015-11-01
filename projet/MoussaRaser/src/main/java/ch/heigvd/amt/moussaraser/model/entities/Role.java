/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 09.10.2015
 * Fichier : Role.java
 */

package ch.heigvd.amt.moussaraser.model.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({ 
   //@NamedQuery(name = "User.findByNameAndPassword", query = "SELECT u FROM User u WHERE u.firstName = :fname AND u.password = :pass"),
})

/**
 * Cette classe est un JPA, elle représente l'entité (table) Role.
 */
public class Role extends AbstractDomainModelEntity<Long> {

   private String role;

   public Role() {
   }

   /**
    * @return the role
    */
   public String getRole() {
      return role;
   }

   /**
    * @param role the role to set
    */
   public void setRole(String role) {
      this.role = role;
   }

}
