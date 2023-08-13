package com.blogepost.controller;

import com.blogepost.payload.PostDto;
import com.blogepost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService ;
// http://localhost:8080/api/posts

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid  @RequestBody PostDto postDto  , BindingResult result){
       if (result.hasErrors()){
           return new ResponseEntity<>(result.getFieldError().getDefaultMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
       }

        PostDto post = postService.createPost(postDto);
        return  new ResponseEntity<>(post , HttpStatus.CREATED);
    }

    // http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=asc

    @GetMapping
    public List<PostDto> getPost(
            @RequestParam(value = "pageNo" , defaultValue ="0" , required = false) int pageNo ,
            @RequestParam(value = "pageSize" , defaultValue ="3" , required = false) int pageSize ,
            @RequestParam(value = "sortBy" , defaultValue ="0" , required = false)  String sortBy ,
            @RequestParam(value = "sortDir" , defaultValue ="0" , required = false) String sortDir
    ){
        List<PostDto> post = postService.getPost(pageNo , pageSize , sortBy , sortDir);
        return  post;
    }


    // http://localhost:8080/api/posts
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id ){
        PostDto  dto = postService.getPostById(id);
        return  new ResponseEntity<>(dto , HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id ){
         postService.deletePostById(id);
        return  new ResponseEntity<>( "post is deleted" , HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") long id  , @RequestBody PostDto postDto){
        PostDto dto = postService.updatePostById(id, postDto);
        return  new ResponseEntity<>( dto, HttpStatus.OK);
    }

}
