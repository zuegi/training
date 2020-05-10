package ch.wesr.flowable.springbootdemo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prospect")
public class ProspectResource {

    @GetMapping("/find")
    public ResponseEntity<String> findByName(@PathVariable String name) {

        if(name == "Paul Allen") {
            return ResponseEntity.ok(name);
        }
        return ResponseEntity.ok(null);


    }
}
