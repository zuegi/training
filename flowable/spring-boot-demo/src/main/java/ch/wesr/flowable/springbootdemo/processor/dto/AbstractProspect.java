package ch.wesr.flowable.springbootdemo.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractProspect implements Serializable {


    private static final long serialVersionUID = 4433188986504128663L;
    String vorname;
    String nachname;
    String strasse;
    String plz;
    String ort;


    public boolean isRecorded() {
        int x = 0;
        return !StringUtils.isEmpty(this.vorname) && !StringUtils.isEmpty(this.nachname) && !StringUtils.isEmpty(this.strasse)
                && !StringUtils.isEmpty(this.ort) && !StringUtils.isEmpty(this.plz);
    }
}
