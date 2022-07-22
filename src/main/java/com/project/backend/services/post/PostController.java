package com.project.backend.services.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.actors.organization.Organization;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/post")
public class PostController {
	
	private final PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<Post> getAllPosts(){
		return postService.getAllPosts();
	}
	
	@GetMapping("/published")
	public List<Post> getAllPublished(){
		return postService.getAllPublished();
	}

	@GetMapping("/published/-{id}")
	public Post getPublishedById(@PathVariable("id") Long id) {
		return postService.getPublishedById(id);
	}

	@GetMapping("/publisher-{ID}")
	public List<Post> getByPublisher(@PathVariable("ID") Long id){
		return postService.getByPublisher(id);
	}
	
	@GetMapping("/category-{ID}")
	public List<Post> getByCategory_id(@PathVariable("ID") Long id){
		return postService.getByCategory_id(id);
	}
	
	@GetMapping("-{PostID}")
	public Post getPostById(@PathVariable("PostID") Long id) {
		return postService.getPostById(id);
	}
	
	@PostMapping("/add")
	public void addNewPost(@RequestBody Post post) {
		postService.addPost(post);
	}
	
	@DeleteMapping("-{PostID}")
	public void deletePost(@PathVariable("PostID") Long id) {
		postService.deletePost(id);
	}
	
	@PutMapping
	public void update(@RequestBody Post p) {
		postService.update(p);
	}
	
	@PutMapping("/publish/-{id}")
	public void publishPsot(@PathVariable("ud") Long id) {
		postService.publish(id);
	}
}

