package com.blogepost.service.serviceimpl;

import com.blogepost.entity.Post;
import com.blogepost.exceptions.ResourceNotFoundException;
import com.blogepost.payload.PostDto;
import com.blogepost.repository.PostRepository;
import com.blogepost.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo ;

    @Autowired
    private ModelMapper modelMapper ;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savePost = postRepo.save(post);
        return mapToDto(savePost);
    }

    @Override
    public List<PostDto> getPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();


        Pageable pageable = PageRequest.of(pageNo, pageSize , sort);
        Page<Post>  posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        return content.stream().map(x->mapToDto(x)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );

        return mapToDto(post) ;
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );

        postRepo.deleteById(id);
    }

    @Override
    public PostDto updatePostById(long id, PostDto postDto){
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        Post  updateContent = mapToEntity(postDto);
         updateContent.setId(post.getId());
        Post  update = postRepo.save(updateContent);
        return mapToDto(update) ;
    }

    Post mapToEntity(PostDto postDto){
        return modelMapper.map(postDto , Post.class);

  }

    PostDto mapToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }
}
