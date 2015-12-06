/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 29.11.2015
 * Fichier : BadgeDTO.java
 */
package ch.heigvd.amt.moussaraser.rest.dto;

/**
 * Classe représentant un DTO de l'entité Badge
 * @author jermoret
 */
public class BadgeDTO {

    private Long id;
    private String name;
    private String category;
    private String description;
    private String image;

    /**
     * Constructs a new BadgeDTO
     * @param id
     * @param name
     * @param category
     * @param description
     * @param image
     */
    public BadgeDTO(Long id, String name, String category, String description, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    /** Constructs a new BadgeDTO. */
    public BadgeDTO() {
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'category'.
     *
     * @return Value for property 'category'.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for property 'category'.
     *
     * @param category Value to set for property 'category'.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter for property 'description'.
     *
     * @return Value for property 'description'.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for property 'description'.
     *
     * @param description Value to set for property 'description'.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for property 'image'.
     *
     * @return Value for property 'image'.
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter for property 'image'.
     *
     * @param image Value to set for property 'image'.
     */
    public void setImage(String image) {
        this.image = image;
    }
}
