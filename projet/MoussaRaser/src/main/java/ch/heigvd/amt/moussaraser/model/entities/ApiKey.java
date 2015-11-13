/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 09.10.2015
 * Fichier : Application.java
 */

package ch.heigvd.amt.moussaraser.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
   @NamedQuery(name = "ApiKey.findByApiKeyStr", query = "SELECT k FROM ApiKey k WHERE k.apiKey = :apiKey"),
})

/**
 * Cette classe est un JPA, elle représente l'entité (table) ApiKey.
 */
public class ApiKey extends AbstractDomainModelEntity<Long> {

   private String apiKey;

   public ApiKey() {
   }

   public ApiKey(String apiKey) {
      this.apiKey = apiKey;
   }

   /**
    * @return the apiKey
    */
   public String getApiKey() {
      return apiKey;
   }

   /**
    * @param apiKey the apiKey to set
    */
   public void setApiKey(String apiKey) {
      this.apiKey = apiKey;
   }
}
