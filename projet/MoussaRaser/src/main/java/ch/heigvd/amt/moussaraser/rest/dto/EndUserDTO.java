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

   private Long id;
   private String firstname;
   private String lastname;
   private Long score;

   public EndUserDTO(Long id, String firstname, String lastname, Long score) {
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
      this.score = score;
   }

   /**
    * @return the first public String getId
    */
   public Long getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id) {
      this.id = id;
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

    /**
     * @return the score
     */
    public Long getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Long score) {
        this.score = score;
    }

}
