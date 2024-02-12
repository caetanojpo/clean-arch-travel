package br.com.travelChallenge.infrastructure.http.dto.request;

public record TestimonialRequest(
        String description,
        String photo,
        String author
) {
}
