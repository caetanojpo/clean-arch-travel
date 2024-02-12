package br.com.travelChallenge.infrastructure.mapper;

import br.com.travelChallenge.domain.model.Testimonial;
import br.com.travelChallenge.infrastructure.database.schema.TestimonialSchema;
import br.com.travelChallenge.infrastructure.http.dto.request.TestimonialRequest;
import br.com.travelChallenge.infrastructure.http.dto.response.TestimonialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestimonialMapper {

    TestimonialMapper INSTANCE = Mappers.getMapper(TestimonialMapper.class);

    Testimonial toTestimonial(TestimonialSchema testimonialSchema);
    Testimonial toTestimonial(TestimonialRequest testimonialRequest);
    TestimonialSchema toTestimonialSchema(Testimonial testimonial);
    TestimonialResponse toTestimonialResponse(Testimonial testimonial);
}
