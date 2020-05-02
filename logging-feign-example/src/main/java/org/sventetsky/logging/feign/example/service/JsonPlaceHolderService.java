package org.sventetsky.logging.feign.example.service;

import org.springframework.stereotype.Service;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.JsonPlaceHolderClient;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.model.Post;

import java.util.List;

@Service
public class JsonPlaceHolderService {

    private JsonPlaceHolderClient client;

    public JsonPlaceHolderService(JsonPlaceHolderClient client) {
        this.client = client;
    }

    public List<Post> fetchPosts() {
        return client.fetchPosts();
    }

    public Post createPost() {
        Post requestPost = new Post(101,
                "Simple title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                1);
        return client.createPost(requestPost);
    }
}
