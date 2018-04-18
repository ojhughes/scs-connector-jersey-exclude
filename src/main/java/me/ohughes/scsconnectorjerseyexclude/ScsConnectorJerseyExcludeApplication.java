package me.ohughes.scsconnectorjerseyexclude;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RibbonClient(name = "jersey-exclude-test-client")
@RestController
public class ScsConnectorJerseyExcludeApplication {

	private RestTemplateBuilder restTemplateBuilder;
	private DiscoveryClient discoveryClient;


	@Autowired
	public ScsConnectorJerseyExcludeApplication(RestTemplateBuilder builder, DiscoveryClient discoveryClient) {
		restTemplateBuilder = builder;
		this.discoveryClient = discoveryClient;
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}

	@RequestMapping("/peer")
	public Map<String, String> peer(RestTemplate restTemplate) {

		for(ServiceInstance instance : discoveryClient.getInstances("peer1")) {
			ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(instance.getUri() + "/self",
					HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {});
			return responseEntity.getBody();
		}
		return new HashMap<>();
	}

	public static void main(String[] args) {
		SpringApplication.run(ScsConnectorJerseyExcludeApplication.class, args);
	}
}
