package com.blogepost.controller;

import com.blogepost.payload.CommentDto;
import com.blogepost.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService ;

    // http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto>  createComment(@PathVariable(value =  "postId") long postId ,  @RequestBody CommentDto  commentDto ){
        CommentDto comment = commentService.createComment(postId, commentDto);
        return  new ResponseEntity<>( comment , HttpStatus.CREATED);
    }


    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findCommentByPostId(
            @PathVariable(value = "postId")long postId
    ){
        List<CommentDto> commentByPostId = commentService.findCommentByPostId(postId);
        return commentByPostId;
    }

    //http://localhost:8080/api/posts/1/comments/2
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto>  findCommentGetById(
            @PathVariable(value = "postId")long postId ,
            @PathVariable(value = "id")long id
    ){
        CommentDto comment = commentService.findCommentGetById(postId , id);
        return  new ResponseEntity<>( comment , HttpStatus.OK);
    }



    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>  deleteCommentById(
            @PathVariable(value = "postId")long postId ,
            @PathVariable(value = "id")long id
    ){
       commentService.deleteCommentById(postId , id);
        return  new ResponseEntity<>( "comment is deleted" , HttpStatus.OK);
    }


    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto>  updateCommentById(
            @PathVariable(value = "postId")long postId ,
            @PathVariable(value = "id")long id ,
            @RequestBody CommentDto commentDto
    ){
      CommentDto dto=  commentService.updateCommentById(postId , id , commentDto);
        return  new ResponseEntity<>( dto, HttpStatus.OK);
    }

}
