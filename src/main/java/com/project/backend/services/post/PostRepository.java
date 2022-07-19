package com.project.backend.services.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	Optional<Post> findByTitle(String title);
	
	List<Post> findByCompanyId(Long com_id);
	
	List<Post> findByCategories_id(Long id);
}
