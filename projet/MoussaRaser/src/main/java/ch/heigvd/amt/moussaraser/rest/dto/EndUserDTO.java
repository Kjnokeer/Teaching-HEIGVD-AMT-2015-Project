/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.dto;

/**
 *
 * @author thibaud
 */
public class EndUserDTO {

   private String firstname;
   private String lastname;

   public EndUserDTO(String firstname, String lastname) {
      this.firstname = firstname;
      this.lastname = lastname;
   }

   /**
    * @return the first public String getFirsname
    */
   public String getFirstname() {
      return firstname;
   }

   /**
    * @param firstname the firstname to set
    */
   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   /**
    * @return the lastname
    */
   public String getLastname() {
      return lastname;
   }

   /**
    * @param lastname the lastname to set
    */
   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

}
