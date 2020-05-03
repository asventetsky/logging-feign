package org.sventetsky.logging.feign.example.client.jsonplaceholder;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.configuration.JsonPlaceHolderClientConfiguration;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.model.Post;
import org.sventetsky.logging.feign.example.service.JsonPlaceHolderService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(
        name = "jsonplaceholder",
        url = "https://jsonplaceholder.typicode.com",
        configuration = JsonPlaceHolderClientConfiguration.class
)
public interface JsonPlaceHolderClient {

    @RequestMapping(method = GET, value = "/posts")
    List<Post> fetchPosts();

    @RequestMapping(method = POST, value = "/posts")
    Post createPost(@RequestBody Post requestPost);
}
