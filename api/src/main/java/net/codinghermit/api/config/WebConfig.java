package net.codinghermit.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
					.addMapping("/api/**").allowedOrigins("http://127.0.0.1:3000");
                    // .addMapping("/api/**")
                    // .allowedOrigins(
                    //     "*"
                    // )
                    // .allowedHeaders("*")
                    // .exposedHeaders("*")
                    // .allowedMethods("*")
                    // .allowCredentials(true);
			}
		};
	}
}
