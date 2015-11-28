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
public class ErrorObject {
 
   private String error;

   public ErrorObject(String error) {
      this.error = error;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }
   
}
