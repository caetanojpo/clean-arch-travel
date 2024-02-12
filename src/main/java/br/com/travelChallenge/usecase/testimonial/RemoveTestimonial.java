package br.com.travelChallenge.usecase.testimonial;

import br.com.travelChallenge.domain.repository.TestimonialRepository;

public class RemoveTestimonial {

    private final TestimonialRepository repository;
    private final FindTestimonial find;

    public RemoveTestimonial(TestimonialRepository repository, FindTestimonial find) {
        this.repository = repository;
        this.find = find;
    }

    public void execute(Long id){
        find.byId(id);

        repository.delete(id);
    }
}
