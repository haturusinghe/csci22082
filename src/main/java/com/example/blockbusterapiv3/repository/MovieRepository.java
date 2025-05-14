package com.example.blockbusterapiv3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.blockbusterapiv3.model.Movie;
import com.example.blockbusterapiv3.model.MovieGenre;


/*
 * We follow the Spring Data JPA repository pattern to create a repository interface for the Movie entity.
 * The name should be in the format of <EntityName>Repository.
 * We extend the CrudRepository interface provided by Spring Data JPA because it provides CRUD operations for the entity and we pass the entity type and the type of its primary key to the interface.
 *  We need to use the annotation @Repository to indicate that this interface is a repository.
 *  We can also define custom query methods in this interface to perform specific queries on the Movie entity.
 * 
 */
@Repository // Spring Data JPA annotation to indicate that this interface is a repository
// The repository will be used to perform CRUD operations on the Movie entity
public interface MovieRepository extends CrudRepository<Movie, Long> {
    // Custom query method to find movies released between two years
    List<Movie> findByReleaseYearBetween(int startYear, int endYear);

    // Custom query method to find movies with matching to a given title (Search by title)
    List<Movie> findByTitleContainingIgnoreCase(String title);
}