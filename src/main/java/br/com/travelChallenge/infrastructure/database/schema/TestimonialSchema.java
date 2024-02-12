package br.com.travelChallenge.infrastructure.database.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "testimonials")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TestimonialSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String description;

    private String photo;

    @Column(nullable = false)
    private String author;


}
