package br.com.travelChallenge.infrastructure.configuration;

import br.com.travelChallenge.domain.repository.TestimonialRepository;
import br.com.travelChallenge.usecase.testimonial.FindTestimonial;
import br.com.travelChallenge.usecase.testimonial.RemoveTestimonial;
import br.com.travelChallenge.usecase.testimonial.SaveTestimonial;
import br.com.travelChallenge.usecase.testimonial.UpdateTestimonial;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public FindTestimonial findTestimonial(TestimonialRepository testimonialRepository) {
        return new FindTestimonial(testimonialRepository);
    }

    @Bean
    public SaveTestimonial saveTestimonial(TestimonialRepository testimonialRepository){
        return new SaveTestimonial(testimonialRepository);
    }

    @Bean
    public UpdateTestimonial updateTestimonial(TestimonialRepository testimonialRepository, FindTestimonial findTestimonial){
        return new UpdateTestimonial(testimonialRepository, findTestimonial);
    }

    @Bean
    public RemoveTestimonial removeTestimonial(TestimonialRepository testimonialRepository, FindTestimonial findTestimonial){
        return new RemoveTestimonial(testimonialRepository, findTestimonial);
    }
}
