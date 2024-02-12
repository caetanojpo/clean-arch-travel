package br.com.travelChallenge.usecase.testimonial;

import br.com.travelChallenge.domain.model.Testimonial;
import br.com.travelChallenge.domain.repository.TestimonialRepository;

public class SaveTestimonial {
    private final TestimonialRepository repository;

    public SaveTestimonial(TestimonialRepository repository) {
        this.repository = repository;
    }

    public Testimonial execute(Testimonial testimonial) {
        return repository.save(testimonial);

    }
}
