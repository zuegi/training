package ch.wesr.flowable.springbootdemo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/hello")
public class HelloResource {

    @GetMapping("/say")
    public String hello(Principal principal) {
        return "Hello " + principal.getName() + "!";
    }

}
