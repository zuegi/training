package ch.wesr.flowable.springbootdemo.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProspectDto extends AbstractProspect {

    public static final String PAUL = "Paul";
    public static final ProspectDto VALID_KUNDE = erstelleKunde(PAUL);

    private String swiftKey;

    public static ProspectDto erstelleKunde(String prospectName) {
        ProspectDto prospectDto = new ProspectDto();
        prospectDto.setVorname(prospectName);
        prospectDto.setNachname("Allen");
        prospectDto.setOrt("Mountain View");
        prospectDto.setPlz("80008");
        prospectDto.setStrasse("Palo Alto Strasse");
        return prospectDto;
    }
}
