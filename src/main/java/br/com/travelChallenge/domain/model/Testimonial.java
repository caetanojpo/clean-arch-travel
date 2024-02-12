package br.com.travelChallenge.domain.model;

public class Testimonial {

    private Long id;
    private String description;
    private String photo;
    private String author;

    public Testimonial(Long id, String description, String photo, String author) {
        this.id = id;
        this.description = description;
        this.photo = photo;
        this.author = author;
    }

    public Testimonial(String description, String photo, String author) {
        this.description = description;
        this.photo = photo;
        this.author = author;
    }

    public Testimonial() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
