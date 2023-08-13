package com.blogepost.repository;

import com.blogepost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post , Long>{
}
