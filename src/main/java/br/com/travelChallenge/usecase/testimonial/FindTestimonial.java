package br.com.travelChallenge.usecase.testimonial;

import br.com.travelChallenge.domain.exception.ObjectNotFoundException;
import br.com.travelChallenge.domain.model.Testimonial;
import br.com.travelChallenge.domain.repository.TestimonialRepository;

import java.util.List;

public class FindTestimonial {
    private final TestimonialRepository repository;

    public FindTestimonial(TestimonialRepository repository) {
        this.repository = repository;
    }

    public Testimonial byId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Testimonial not found by the id: " + id));
    }

    public List<Testimonial> getThree(){
        return repository.findRandomThree();
    }
}
