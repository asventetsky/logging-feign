# Feign Logger

Implementation of `feign.Logger` with one-log-message request/response. Request and response messages would be look
like this:
```text
2020-05-03 13:04:28.726  INFO 29259 --- [           main] o.s.l.f.e.c.j.JsonPlaceHolderClient      : [RQ:2][POST]https://jsonplaceholder.typicode.com/posts
||||HEADERS:{Content-Length=[110], Content-Type=[application/json]}||||
||||BODY:{"id":101,"title":"Simple title","body":"Lorem ipsum dolor sit amet, consectetur adipiscing elit.","userId":1}||||
```
```text
2020-05-03 13:04:29.519  INFO 29259 --- [           main] o.s.l.f.e.c.j.JsonPlaceHolderClient      : [RS:2][POST][201]https://jsonplaceholder.typicode.com/posts in 791 ms
||||HEADERS:{access-control-allow-credentials=[true], access-control-expose-headers=[Location], cache-control=[no-cache], cf-cache-status=[DYNAMIC], cf-ray=[58d928790d6684f8-LED], cf-request-id=[027b979fa7000084f84a801200000001], connection=[keep-alive], content-length=[127], content-type=[application/json; charset=utf-8], date=[Sun, 03 May 2020 10:04:29 GMT], etag=[W/"7f-LmvoVQg5OBpqk38/1iku7eTFM3w"], expect-ct=[max-age=604800, report-uri="https://report-uri.cloudflare.com/cdn-cgi/beacon/expect-ct"], expires=[-1], location=[http://jsonplaceholder.typicode.com/posts/101], pragma=[no-cache], server=[cloudflare], set-cookie=[__cfduid=d7671e7446a756910f748197c587db76f1588500268; expires=Tue, 02-Jun-20 10:04:28 GMT; path=/; domain=.typicode.com; HttpOnly; SameSite=Lax], vary=[Origin, X-HTTP-Method-Override, Accept-Encoding], via=[1.1 vegur], x-content-type-options=[nosniff], x-powered-by=[Express]}||||
||||BODY:{
  "id": 101,
  "title": "Simple title",
  "body": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  "userId": 1
}||||
```

## Getting Started

### Prerequisites

You must have `spring-cloud-starter-openfeign` dependency:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### Installing

Add the following dependency:

```xml
<dependency>
    <groupId>org.sventetsky</groupId>
    <artifactId>logging-feign</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Create class which implements `LoggingConfig` interface:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sventetsky.logging.feign.LoggingConfig;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.JsonPlaceHolderClient;

public class JsonPlaceHolderLoggingConfig implements LoggingConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(JsonPlaceHolderClient.class);

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public boolean isLoggingEnabled() {
        return true;
    }

    @Override
    public boolean isLogPayload() {
        return true;
    }

    @Override
    public boolean isLogHeaders() {
        return true;
    }
}
```

Create custom configuration for your feign client. Create @Bean of type `FeignLoggerFactory` which will override
default factory. Choose `PrettyFeignLoggerFactory` which expects in constructor previously created logging config 
(`JsonPlaceHolderLoggingConfig`). Next, it is necessary to override default `Logger.Level (NONE)`.
```java
import feign.Logger;
import feign.Request;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sventetsky.logging.feign.PrettyFeignLoggerFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class JsonPlaceHolderClientConfiguration {

    @Bean
    public FeignLoggerFactory jsonPlaceHolderFeignLoggerFactory() {
        return new PrettyFeignLoggerFactory(new JsonPlaceHolderLoggingConfig());
    }

    @Bean
    public Logger.Level jsonPlaceHolderFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options jsonPlaceHolderFeignRequestOptions() {
        return new Request.Options(
                10, SECONDS,
                10, SECONDS,
                true
        );
    }
}
```

Add your custom configuration to feign client: 

```java
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.configuration.JsonPlaceHolderClientConfiguration;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.model.Post;

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
```

## Running the tests

To see feign logger in action you could try integration tests: `FeignClientsServiceTest`,
`JsonPlaceHolderServiceTest` and `TheReportOfTheWeekServiceTest`.