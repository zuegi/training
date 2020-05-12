package ch.wesr.flowable.springbootdemo.processor.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProspectDtoTest {


    @Test
    public void validKunde() {
        ProspectDto prospectDto = ProspectDto.VALID_KUNDE;
        prospectDto.setSwiftKey("This is a SwiftKey");
        assertTrue(prospectDto.isRecorded());

    }

    @Test
    public void invalidKunde() {
        ProspectDto prospectDto = new ProspectDto();
        prospectDto.setVorname("Rene");
        assertFalse(prospectDto.isRecorded());
    }


}
