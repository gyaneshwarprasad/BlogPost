package com.blogepost.service;

import com.blogepost.payload.CommentDto;

import java.util.List;

public interface CommentService {


     CommentDto createComment(long postId, CommentDto commentDto);

       List<CommentDto> findCommentByPostId(long postId);

    CommentDto findCommentGetById(long postId, long id);

    void deleteCommentById(long postId, long id);

    CommentDto updateCommentById(long postId, long id, CommentDto commentDto);
}
