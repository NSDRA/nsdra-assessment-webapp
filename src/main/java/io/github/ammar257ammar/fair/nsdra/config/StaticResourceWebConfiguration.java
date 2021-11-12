package io.github.ammar257ammar.fair.nsdra.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class StaticResourceWebConfiguration implements WebMvcConfigurer {
  
  protected static final String[] RESOURCE_LOCATIONS = new String[] {
      "classpath:/static/css/", 
      "classpath:/static/js/",
      "classpath:/static/images/",
      "file:///files/"
  };
  
  protected static final String[] RESOURCE_PATHS = new String[] { "/css/**", "/js/**", "/images/**", "/files/**" };

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler(RESOURCE_PATHS)
              .addResourceLocations(RESOURCE_LOCATIONS)
              .setCachePeriod(14400)
              .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
              .resourceChain(true)
              .addResolver(new PathResourceResolver());
  }

}
