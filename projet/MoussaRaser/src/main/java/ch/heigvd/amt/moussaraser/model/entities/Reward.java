/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 09.10.2015
 * Fichier : Reward.java
 */
package ch.heigvd.amt.moussaraser.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = "Reward.getAllByApplication", query = "SELECT r FROM Reward r WHERE r.application = :app"),
        @NamedQuery(name = "Reward.getByIdAndByApplication", query = "SELECT r FROM Reward r WHERE r.id = :id AND r.application = :app")
})

/**
 * Cette classe est un JPA, elle représente l'entité (table) Reward.
 */
public class Reward extends AbstractDomainModelEntity<Long> {

    @NotNull
    private String name;
    @NotNull
    private String category;
    private String description;
    private String image;

    @ManyToOne
    private Application application;

    /**
     * Constructeur vide
     */
    public Reward() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @param application the application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * @return the application
     */
    public Application getApplication() {
        return application;
    }
}
