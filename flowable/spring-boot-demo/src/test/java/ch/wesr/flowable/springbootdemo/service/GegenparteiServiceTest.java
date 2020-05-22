package ch.wesr.flowable.springbootdemo.service;

import ch.wesr.flowable.springbootdemo.SpringBootDemoTestConfiguration;
import ch.wesr.flowable.springbootdemo.web.ui.GegenparteiUI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GegenparteiServiceTest extends SpringBootDemoTestConfiguration {

    @Autowired
    GegenparteiService gegenparteiService;

    @Test
    public void updateGegenpartei() {
        GegenparteiUI gegenparteiUI = GegenparteiUI.builder()
                .referenzId("1100")
                .code("flawa")
                .bezeichnung("Flawa AG")
                .strasse("Wilerstrasse 5")
                .plz(9230).ort("Flawil").land("Schweiz").swiftCode("FLAWCHF33")
                .build();
        this.gegenparteiService.createGegenpartei(gegenparteiUI);

        assertThat(this.gegenparteiService.findBySearchKey("Botsberg"), is(empty()));
        gegenparteiUI.setStrasse("Botsberg 42");

        this.gegenparteiService.updateGegenpartei(gegenparteiUI.getReferenzId(), gegenparteiUI);

        assertThat(this.gegenparteiService.findBySearchKey(gegenparteiUI.getStrasse()), is(notNullValue()));
    }

    @Test
    public void erstelleGegenpartei() {
        GegenparteiUI gegenparteiUI = GegenparteiUI.builder()
                .referenzId("1000")
                .code("flawa")
                .bezeichnung("Flawa AG")
                .strasse("Wilerstrasse 5")
                .plz(9230).ort("Flawil").land("Schweiz").swiftCode("FLAWCHF33")
                .build();
        this.gegenparteiService.createGegenpartei(gegenparteiUI);
    }

    @Test
    public void erstelleSchonVorhandeneGegenpartei() {
        GegenparteiUI gegenparteiUI = GegenparteiUI.builder()
                .referenzId("1000")
                .code("flawa")
                .bezeichnung("Flawa AG")
                .strasse("Wilerstrasse 5")
                .plz(9230).ort("Flawil").land("Schweiz").swiftCode("FLAWCHF33")
                .build();
        assertThat(this.gegenparteiService.createGegenpartei(gegenparteiUI), is(nullValue()));
        // und dann gleich nochmals
        assertThat(gegenparteiUI, is(this.gegenparteiService.createGegenpartei(gegenparteiUI)));
    }

    @Test
    public void findByOrt() {
        String ort = "Spanien";
        findBySearchKey(ort, 2);
    }

    @Test
    public void findByXXX() {
        String searchKey = "XXX";
        findBySearchKey(searchKey, 3);
    }

    @Test
    public void findBySearchkeyMadrid() {
        String searchKey = "Madrid";
        findBySearchKey(searchKey, 2);
    }

    @Test
    public void findBySearchkeyC() {
        String searchKey = "c";
        findBySearchKey(searchKey, 2);
    }


    private void findBySearchKey(String searchKey, int expectedHits) {
        List<GegenparteiUI> gegenparteiUIList = gegenparteiService.findBySearchKey(searchKey);
        assertThat(gegenparteiUIList, hasSize(expectedHits));
    }
}
