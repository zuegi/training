package ch.wesr.flowable.springbootdemo.processor.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcsMsKundeTest {


    @Test
    public void validKunde() {
        Prospect prospect = erstelleTestKunde(true);

        assertTrue(prospect.isKunde());
    }

    @Test
    public void invalidKunde() {
        Prospect prospect = erstelleTestKunde(false);

        assertFalse(prospect.isKunde());
    }


    private Prospect erstelleTestKunde(boolean validKunde) {
        Prospect prospect = new Prospect();
        if (validKunde) {
            prospect.setVorname("Paul");
            prospect.setNachname("Allen");
            prospect.setOrt("Mountain View");
            prospect.setPlz("80008");
            prospect.setStrasse("Palo Alto Strasse");
        }
        return prospect;
    }

}
