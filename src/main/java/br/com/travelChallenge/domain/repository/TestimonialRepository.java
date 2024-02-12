package br.com.travelChallenge.domain.repository;

import br.com.travelChallenge.domain.model.Testimonial;

import java.util.Optional;

public interface TestimonialRepository {

    Optional<Testimonial> findById(Long id);

    Testimonial save(Testimonial testimonial);

    Testimonial update(Testimonial testimonial);

    void delete(Long id);
}
