package com.project.backend.services.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	@Query(value = "SELECT * FROM post  WHERE title = ?1", nativeQuery = true)
	Optional<Post> findByTitle(String postTitle);
	
	@Query(value = "SELECT * FROM post  WHERE com_id = ?1", nativeQuery = true)
	List<Post> findByCom_id(Long com_id);
}
