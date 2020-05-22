package ch.wesr.flowable.springbootdemo.web;

import ch.wesr.flowable.springbootdemo.service.GegenparteiService;
import ch.wesr.flowable.springbootdemo.web.ui.GegenparteiUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/gegenpartei")
public class GegenparteiResource {

    @Autowired
    private GegenparteiService gegenparteiService;

    @GetMapping("/{searchkey}")
    public ResponseEntity<List<GegenparteiUI>> findListOfGegenparteiBySearchKey(@PathVariable String searchkey) {
        log.info("SearchKey: {}", searchkey);
        return ResponseEntity.ok(gegenparteiService.findBySearchKey(searchkey));
    }


    @GetMapping
    public ResponseEntity<Collection<GegenparteiUI>> all() {
        Collection<GegenparteiUI> all = gegenparteiService.findAll();
        log.info("find all gegenparteien: {}", all);
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<GegenparteiUI> createGegenpartei(@RequestBody GegenparteiUI gegenparteiUI) {
        log.info("Create Gegenpartei: {}", gegenparteiUI.toString());
        return ResponseEntity.ok(gegenparteiService.createGegenpartei(gegenparteiUI));
    }

    @PutMapping("/{lookupId}")
    public ResponseEntity<GegenparteiUI> createGegenpartei(@PathVariable String lookupId, @RequestBody GegenparteiUI gegenparteiUI) {
        return ResponseEntity.ok(gegenparteiService.updateGegenpartei(lookupId, gegenparteiUI));
    }

}
