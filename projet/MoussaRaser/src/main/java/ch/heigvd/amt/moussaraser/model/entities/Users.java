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
  @NamedQuery(name = "Users.findByNameAndPassword", query = "SELECT u FROM Users u WHERE u.firstName = :fname AND u.password = :pass"),
})

public class Users extends AbstractDomainModelEntity<Long> {

  private String firstName;
  private String lastName;
  private String email;
  private String password;

  public Users() {
  }
  
  
}
