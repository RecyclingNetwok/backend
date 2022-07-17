package com.project.backend.services.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.backend.actors.company.Company;
import com.project.backend.actors.company.CompanyRepository;

@Service
public class PostService {

	private final PostRepository postRepository;
	private CompanyRepository companyRepository;
	
	@Autowired
	public PostService(PostRepository postRepository, CompanyRepository companyRepository) {
		this.postRepository = postRepository;
		this.companyRepository = companyRepository;
	}
	
	public List<Post> getAllPosts(){
		return (List<Post>) postRepository.findAll();
	}
	
	public Post getPostById(Long id) {
		return postRepository.findById(id).get();
	}
	
	public List<Post> getByPublisher(Long id){
		return postRepository.findByCom_id(id);
	}
	
	public void addPost(Post post) {
		Optional<Post> postOptional = postRepository.findByTitle(post.getTitle());
		if(postOptional.isPresent()) {
			throw new IllegalStateException("Post With this title already Exist !");
		}else {
			System.out.println("compaany id : " +post.getCom_id());
			//Get company
			Optional<Company> optionalCom = companyRepository.findById(post.getCom_id());
			if(!optionalCom.isPresent()) {
				throw new IllegalStateException("Which Company is it for ? !");
			}else {
				Company com = optionalCom.get();
				
//				com.setId(post.getCom_id());
//				System.out.println(com);
//				
				post.setCompany(com);
				
				System.out.println(post);
				postRepository.save(post);
			}
			
		}
	}
	
	public void deletePost(Long id) {
		boolean exists = postRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Post with id '"+id+"' does not exists !");
		}
		System.out.println("Deleting Post "+id);
		postRepository.deleteById(id);
	}
	
	public void update(Post p) {
		postRepository.save(p);
	}
}
