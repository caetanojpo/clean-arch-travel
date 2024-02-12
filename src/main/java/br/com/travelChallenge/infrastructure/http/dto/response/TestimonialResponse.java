package br.com.travelChallenge.infrastructure.http.dto.response;

public record TestimonialResponse(
        Long id,
        String description,
        String photo,
        String author
) {
}
