package tk.anotherm4.webpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableWebMvc如果不适用@RestController + @RequestMapping则无需使用，否则css和js将会出现404问题
public class WebPressApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebPressApplication.class, args);
    }

}

