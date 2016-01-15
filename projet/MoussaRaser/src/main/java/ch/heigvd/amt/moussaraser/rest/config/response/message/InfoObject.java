/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : InfoObject.java
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
