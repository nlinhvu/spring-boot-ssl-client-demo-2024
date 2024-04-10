package vn.cloud.sslhttpsclientdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private final RestClient restClient;
    private final RestTemplate restTemplate;

    HelloController(RestClient restClient, RestTemplate restTemplate) {
        this.restClient = restClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    String hello() {
        return this.restClient.get().uri("/hello").retrieve().body(String.class);
    }

    @GetMapping("/hi")
    String hi() {
        return this.restTemplate.getForObject("/hello", String.class);
    }
}
