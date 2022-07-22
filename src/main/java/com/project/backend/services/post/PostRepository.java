package com.project.backend.services.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	Optional<Post> findByTitle(String title);
	
	@Query(value = "SELECT * FROM post WHERE com_id = ?1 ", nativeQuery = true)
	List<Post> findBycom_id(Long com_id);
	
	List<Post> findByCategories_id(Long id);
}
