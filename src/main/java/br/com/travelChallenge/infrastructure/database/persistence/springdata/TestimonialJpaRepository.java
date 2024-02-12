package br.com.travelChallenge.infrastructure.database.persistence.springdata;

import br.com.travelChallenge.infrastructure.database.schema.TestimonialSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialJpaRepository extends JpaRepository<TestimonialSchema, Long> {

}
