/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : ErrorObject.java
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
