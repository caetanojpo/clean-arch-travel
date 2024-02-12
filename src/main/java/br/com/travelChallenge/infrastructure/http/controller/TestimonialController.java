package br.com.travelChallenge.infrastructure.http.controller;

import br.com.travelChallenge.domain.model.Testimonial;
import br.com.travelChallenge.infrastructure.http.dto.request.TestimonialRequest;
import br.com.travelChallenge.infrastructure.http.dto.response.TestimonialResponse;
import br.com.travelChallenge.infrastructure.mapper.TestimonialMapper;
import br.com.travelChallenge.usecase.testimonial.FindTestimonial;
import br.com.travelChallenge.usecase.testimonial.RemoveTestimonial;
import br.com.travelChallenge.usecase.testimonial.SaveTestimonial;
import br.com.travelChallenge.usecase.testimonial.UpdateTestimonial;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/testimonials")
public class TestimonialController {

    private final FindTestimonial find;
    private final SaveTestimonial save;
    private final UpdateTestimonial update;
    private final RemoveTestimonial remove;

    @PostMapping
    public ResponseEntity<TestimonialResponse> save(@RequestBody TestimonialRequest testimonialRequest, UriComponentsBuilder uriComponentsBuilder) {
        var testimonial = TestimonialMapper.INSTANCE.toTestimonial(testimonialRequest);
        var testimonialResponse = TestimonialMapper.INSTANCE.toTestimonialResponse(save.execute(testimonial));

        URI uri = uriComponentsBuilder.path("/testimonials/{id}").buildAndExpand(testimonialResponse.id()).toUri();

        return ResponseEntity.created(uri).body(testimonialResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestimonialResponse> findById(@PathVariable Long id) {
        var testimonialResponse = TestimonialMapper.INSTANCE.toTestimonialResponse(find.byId(id));

        return ResponseEntity.ok(testimonialResponse);
    }

    @GetMapping("/random")
    public ResponseEntity<List<TestimonialResponse>> randomThree() {
        List<Testimonial> testimonials = find.getThree();

        List<TestimonialResponse> response = testimonials.stream()
                .map(TestimonialMapper.INSTANCE::toTestimonialResponse).toList();
        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TestimonialResponse> update(@PathVariable Long id, TestimonialRequest testimonialRequest) {
        var testimonial = TestimonialMapper.INSTANCE.toTestimonial(testimonialRequest);
        var testimonialResponse = TestimonialMapper.INSTANCE.toTestimonialResponse(update.execute(id, testimonial));

        return ResponseEntity.ok(testimonialResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        remove.execute(id);

        return ResponseEntity.noContent().build();
    }
}
