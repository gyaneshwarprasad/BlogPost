package com.blogepost.service.serviceimpl;

import com.blogepost.entity.Comment;
import com.blogepost.entity.Post;
import com.blogepost.exceptions.ResourceNotFoundException;
import com.blogepost.payload.CommentDto;
import com.blogepost.repository.CommentRepository;
import com.blogepost.repository.PostRepository;
import com.blogepost.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private ModelMapper modelMapper ;
    @Autowired
    private CommentRepository commentRepo ;

    @Autowired
     private PostRepository postRepo ;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto){

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        Comment comment = mapToCommentEntity(commentDto);
        comment.setPost(post);
        Comment save = commentRepo.save(comment);
        return mapToCommentDto(save);
    }

    @Override
    public List<CommentDto> findCommentByPostId(long postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        List<Comment>  comment = commentRepo.findByPostId(postId);
        return comment.stream().map(x->mapToCommentDto(x)).collect(Collectors.toList());
    }

    @Override
    public CommentDto findCommentGetById(long postId, long id) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );

        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );

        return mapToCommentDto(comment);
    }

    @Override
    public void deleteCommentById(long postId, long id) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );

        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
         commentRepo.deleteById(comment.getId());
    }

    @Override
    public CommentDto updateCommentById(long postId, long id, CommentDto commentDto) {

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );

        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );


        Comment updateContent = mapToCommentEntity(commentDto);
                updateContent.setId(comment.getId());
                updateContent.setPost(post);

        Comment save = commentRepo.save(updateContent);

        return mapToCommentDto(save);
    }

    Comment mapToCommentEntity(CommentDto commentDto){
      return   modelMapper.map(commentDto , Comment.class);
    }

    CommentDto mapToCommentDto(Comment comment){
        return   modelMapper.map(comment , CommentDto.class);
    }
}
