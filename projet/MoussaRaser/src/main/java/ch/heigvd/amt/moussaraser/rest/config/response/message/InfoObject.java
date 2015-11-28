/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.config.response.message;

/**
 *
 * @author Mathias
 */
public class InfoObject {
   
   private String information;

   public InfoObject(String information) {
      this.information = information;
   }

   public String getInformation() {
      return information;
   }

   public void setInformation(String information) {
      this.information = information;
   }
}
