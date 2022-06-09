package io.github.ammar257ammar.fair.nsdra;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.openjson.JSONException;
import com.github.openjson.JSONObject;

@SpringBootApplication
public class App {

  @Bean(name="miJsonDescription")
  public JSONObject getMiJsonDescription()
      throws JSONException, MalformedURLException, IOException {
    JSONObject json = new JSONObject(IOUtils.toString(new URL(
        "https://raw.githubusercontent.com/NSDRA/nsdra-maturity-indicators/main/lists.json"),
        Charset.forName("UTF-8")));
    return json;
  }
  
  @Bean(name="miAppJsonDescription")
  public JSONObject getMiAppJsonDescription()
      throws JSONException, MalformedURLException, IOException {
    JSONObject json = new JSONObject(IOUtils.toString(new URL(
        "https://raw.githubusercontent.com/NSDRA/nsdra-maturity-indicators/main/mapping.json"),
        Charset.forName("UTF-8")));
    return json;
  }

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(App.class);
    application.run(args);
  }

}