package ch.wesr.flowable.springbootdemo.processor.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcsMsKundeTest {


    @Test
    public void validKunde() {
        ProspectDto prospectDto = erstelleTestKunde(true);

        assertTrue(prospectDto.isRecorded());
    }

    @Test
    public void invalidKunde() {
        ProspectDto prospectDto = erstelleTestKunde(false);

        assertFalse(prospectDto.isRecorded());
    }


    private ProspectDto erstelleTestKunde(boolean validKunde) {
        ProspectDto prospectDto = new ProspectDto();
        if (validKunde) {
            prospectDto.setVorname("Paul");
            prospectDto.setNachname("Allen");
            prospectDto.setOrt("Mountain View");
            prospectDto.setPlz("80008");
            prospectDto.setStrasse("Palo Alto Strasse");
        }
        return prospectDto;
    }

}
