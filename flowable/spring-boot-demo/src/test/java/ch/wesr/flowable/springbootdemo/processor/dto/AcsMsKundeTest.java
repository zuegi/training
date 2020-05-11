package ch.wesr.flowable.springbootdemo.processor.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcsMsKundeTest {


    @Test
    public void validKunde() {
        AcsMsKunde acsMsKunde = erstelleTestKunde(true);

        assertTrue(acsMsKunde.isKunde());
    }

    @Test
    public void invalidKunde() {
        AcsMsKunde acsMsKunde = erstelleTestKunde(false);

        assertFalse(acsMsKunde.isKunde());
    }


    private AcsMsKunde erstelleTestKunde(boolean validKunde) {
        AcsMsKunde acsMsKunde = new AcsMsKunde();
        if (validKunde) {
            acsMsKunde.setVorname("Paul");
            acsMsKunde.setNachname("Allen");
            acsMsKunde.setOrt("Mountain View");
            acsMsKunde.setPlz("80008");
            acsMsKunde.setStrasse("Palo Alto Strasse");
        }
        return acsMsKunde;
    }

}
