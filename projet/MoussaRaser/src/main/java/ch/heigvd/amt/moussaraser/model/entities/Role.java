package ch.heigvd.amt.moussaraser.model.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({ 
   //@NamedQuery(name = "User.findByNameAndPassword", query = "SELECT u FROM User u WHERE u.firstName = :fname AND u.password = :pass"),
})

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
