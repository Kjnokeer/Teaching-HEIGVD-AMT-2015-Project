/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.rest.config;

/**
 *
 * @author Mathias
 */
public class ErrorApiKey {
 
   private String error;

   public ErrorApiKey(String error) {
      this.error = error;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }
   
}
