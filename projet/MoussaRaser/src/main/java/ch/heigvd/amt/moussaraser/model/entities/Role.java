package ch.heigvd.amt.moussaraser.model.entities;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

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
