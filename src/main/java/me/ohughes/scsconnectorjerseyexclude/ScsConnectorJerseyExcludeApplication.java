package me.ohughes.scsconnectorjerseyexclude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ScsConnectorJerseyExcludeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScsConnectorJerseyExcludeApplication.class, args);
	}
}
