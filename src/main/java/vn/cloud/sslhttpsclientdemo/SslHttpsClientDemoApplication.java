package vn.cloud.sslhttpsclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.client.RestClientSsl;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.time.Duration;

@SpringBootApplication
public class SslHttpsClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslHttpsClientDemoApplication.class, args);
	}

	@Bean
	RestClient restClient(RestClient.Builder builder, RestClientSsl ssl) {
		return builder
				.baseUrl("https://localhost:8443")
				.apply(ssl.fromBundle("client"))
				.build();
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder, SslBundles sslBundles) {
		return builder
				.rootUri("https://localhost:8443")
				.setSslBundle(sslBundles.getBundle("client"))
				.build();
	}
}
