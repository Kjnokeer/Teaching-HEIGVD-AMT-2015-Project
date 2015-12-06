/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 09.10.2015
 * Fichier : ApiKey.java
 */
package ch.heigvd.amt.moussaraser.model.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = "ApiKey.findByApiKeyStr", query = "SELECT k FROM ApiKey k WHERE k.apiKey = :apiKey")
})

/**
 * Cette classe est un JPA, elle représente l'entité (table) ApiKey.
 * @author jermoret
 */
public class ApiKey extends AbstractDomainModelEntity<Long> {

    @NotNull
    private String apiKey; // Champ obligatoire

    /**
     * Constructeur vide
     */
    public ApiKey() {
    }

    /**
     * Constructeur avec clé de l'API
     *
     * @param apiKey
     */
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
