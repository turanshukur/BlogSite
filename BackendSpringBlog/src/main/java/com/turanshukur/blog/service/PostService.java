package com.turanshukur.blog.service;

import com.turanshukur.blog.dto.PostDto;
import com.turanshukur.blog.exception.PostNotFoundException;
import com.turanshukur.blog.model.Post;
import com.turanshukur.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;

    @Autowired
    private PostRepository postRepository;

    public void createPost(PostDto postDto){
         Post post = new Post();
         post.setTitle(postDto.getTitle());
         post.setContent(postDto.getContent());
         User username = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("No User logged in "));
         System.err.println("--------username: " + username.getUsername());
         post.setUsername(username.getUsername());
         post.setCreateOn(Instant.now());

         postRepository.save(post);
    }

    public List<PostDto> showAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("For id" + id));
        return mapFromPostToDto(post);
        }

}
