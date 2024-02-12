package br.com.travelChallenge.infrastructure.database.persistence.repository;

import br.com.travelChallenge.domain.model.Testimonial;
import br.com.travelChallenge.domain.repository.TestimonialRepository;
import br.com.travelChallenge.infrastructure.database.persistence.springdata.TestimonialJpaRepository;
import br.com.travelChallenge.infrastructure.database.schema.TestimonialSchema;
import br.com.travelChallenge.infrastructure.mapper.TestimonialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestimonialPostgreRepository implements TestimonialRepository {

    private final TestimonialJpaRepository jpaRepository;
    private static final TestimonialMapper mapper = TestimonialMapper.INSTANCE;

    @Override
    public Optional<Testimonial> findById(Long id) {
        return jpaRepository.findById(id)
                .map(TestimonialMapper.INSTANCE::toTestimonial);
    }

    @Override
    public List<Testimonial> findRandomThree() {
        return jpaRepository.findRandomThree()
                .stream().map(TestimonialMapper.INSTANCE::toTestimonial).toList();
    }

    @Override
    public Testimonial save(Testimonial testimonial) {
        return mapper.toTestimonial(
                jpaRepository.save(mapper.toTestimonialSchema(testimonial)));
    }

    @Override
    public Testimonial update(Testimonial testimonial) {
        return mapper.toTestimonial(jpaRepository.save(
                mapper.toTestimonialSchema(testimonial)));
    }


    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
