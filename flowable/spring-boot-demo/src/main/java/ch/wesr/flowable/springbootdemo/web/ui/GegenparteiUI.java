package ch.wesr.flowable.springbootdemo.web.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GegenparteiUI {

    private String referenzId;
    private String bezeichnung;
    private String code;
    private String strasse;
    private int plz;
    private String ort;
    private String land;
    private boolean prospect;
    private String swiftKey;
    private String konzernStruktur;

}
