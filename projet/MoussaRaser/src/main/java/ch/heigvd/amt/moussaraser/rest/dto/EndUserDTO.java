/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira Date :
 * 29.11.2015 Fichier : EndUserDTO.java
 */
package ch.heigvd.amt.moussaraser.rest.dto;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.Badge;
import ch.heigvd.amt.moussaraser.model.entities.Reward;
import java.util.Date;
import java.util.List;

/**
 * Classe représentant un DTO de l'entité EndUser
 *
 * @author thibaud
 */
public class EndUserDTO {

   private Long id;
   private String firstName;
   private String lastName;
   private Long score;
   private ApplicationDTO applicationDTO;
   private List<BadgeDTO> badgesDTO;
   private List<RewardDTO> rewardsDTO;
   private Date registrationDate;

   public EndUserDTO() {
   }

   public EndUserDTO(Long id, String firstname, String lastname, Long score) {
      this.id = id;
      this.firstName = firstname;
      this.lastName = lastname;
      this.score = score;
   }
   
   public EndUserDTO(Long id, String firstname, String lastname, Long score, ApplicationDTO applicationDTO, List<BadgeDTO> badgesDTO, List<RewardDTO> rewardsDTO, Date registrationDate) {
      this.id = id;
      this.firstName = firstname;
      this.lastName = lastname;
      this.score = score;
      this.applicationDTO = applicationDTO;
      this.badgesDTO = badgesDTO;
      this.rewardsDTO = rewardsDTO;
      this.registrationDate = registrationDate;
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
    * @return the first public String getFirsName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @param firstName the firstName to set
    */
   public void setFirstname(String firstName) {
      this.firstName = firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param lastName the lastName to set
    */
   public void setLastname(String lastName) {
      this.lastName = lastName;
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

   public ApplicationDTO getApplication() {
      return applicationDTO;
   }

   public void setApplication(ApplicationDTO applicationDTO) {
      this.applicationDTO = applicationDTO;
   }

   public List<BadgeDTO> getBadges() {
      return badgesDTO;
   }

   public void setBadges(List<BadgeDTO> badgesDTO) {
      this.badgesDTO = badgesDTO;
   }

   public List<RewardDTO> getRewards() {
      return rewardsDTO;
   }

   public void setRewards(List<RewardDTO> rewardsDTO) {
      this.rewardsDTO = rewardsDTO;
   }

   public Date getRegistrationDate() {
      return registrationDate;
   }

   public void setRegistrationDate(Date registrationDate) {
      this.registrationDate = registrationDate;
   }

}
