package org.sventetsky.logging.feign.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.model.Post;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JsonPlaceHolderServiceTest {

    @Autowired
    private JsonPlaceHolderService service;

    @Test
    void fetchPosts_shouldReturnCorrectNumberOfPosts() {
        List<Post> posts = service.fetchPosts();
        assertEquals(100, posts.size());
    }

    @Test
    void createPost_shouldReturnCreatedPost() {
        Post post = service.createPost();
        assertEquals(101, post.getId());
    }
}