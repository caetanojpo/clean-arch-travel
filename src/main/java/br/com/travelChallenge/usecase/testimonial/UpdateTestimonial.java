package br.com.travelChallenge.usecase.testimonial;

import br.com.travelChallenge.domain.model.Testimonial;
import br.com.travelChallenge.domain.repository.TestimonialRepository;

public class UpdateTestimonial {

    private final TestimonialRepository repository;
    private final FindTestimonial find;

    public UpdateTestimonial(TestimonialRepository repository, FindTestimonial find) {
        this.repository = repository;
        this.find = find;
    }

    public Testimonial execute(Long id, Testimonial testimonial) {
        var existingTestimonial = find.byId(id);
        existingTestimonial.setDescription(testimonial.getDescription());
        existingTestimonial.setAuthor(testimonial.getAuthor());
        existingTestimonial.setPhoto(testimonial.getPhoto());
        return repository.update(testimonial);
    }
}
