package ch.wesr.flowable.springbootdemo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prospect")
public class ProspectResource {

    @GetMapping("/{name}")
    public ResponseEntity<String> findByName(@PathVariable String name) {

        if(name.equals("Paul")) {
            return ResponseEntity.ok("Hallo " +name +", willkommen an Board");
        }
        return ResponseEntity.ok("Ooooops, da ging wohl was schief");


    }
}
