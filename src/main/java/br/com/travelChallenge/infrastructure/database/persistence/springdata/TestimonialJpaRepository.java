package br.com.travelChallenge.infrastructure.database.persistence.springdata;

import br.com.travelChallenge.infrastructure.database.schema.TestimonialSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialJpaRepository extends JpaRepository<TestimonialSchema, Long> {

    @Query(value = "SELECT * FROM testimonials ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<TestimonialSchema> findRandomThree();
}
