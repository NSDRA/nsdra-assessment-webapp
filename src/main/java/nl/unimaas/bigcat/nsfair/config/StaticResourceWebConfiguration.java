package nl.unimaas.bigcat.nsfair.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceWebConfiguration implements WebMvcConfigurer {
    protected static final String[] RESOURCE_LOCATIONS = new String[] {
        "classpath:/static/",
        "classpath:/static/css/",
        "classpath:/static/js/",
        "classpath:/static/images/"
    };
    protected static final String[] RESOURCE_PATHS = new String[] { "/css/*", "/js/*", "/images/*" };

    protected ResourceHandlerRegistration appendResourceHandler(ResourceHandlerRegistry registry) {
        return registry.addResourceHandler(RESOURCE_PATHS)
        				.addResourceLocations(RESOURCE_LOCATIONS);
    }

}
