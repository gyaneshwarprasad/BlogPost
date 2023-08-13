package com.blogepost.service;

import com.blogepost.payload.PostDto;

import java.util.List;

public interface PostService {
    
    PostDto createPost(PostDto postDto);

    List<PostDto> getPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

     void deletePostById(long id);

     PostDto updatePostById(long id, PostDto postDto);
}
