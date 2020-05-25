package ch.wesr.flowable.springbootdemo.service;

import ch.wesr.flowable.springbootdemo.web.ui.GegenparteiUI;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GegenparteiService {


    @Autowired
    private ResourceLoader resourceLoader;

    List<GegenparteiUI> gegenparteiUIList = new ArrayList<>();

    @PostConstruct
    private void init() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:assets/gegenparteien.csv");
        gegenparteiUIList = this.loadObjectList(GegenparteiUI.class, resource.getFile());
    }

    public List<GegenparteiUI> findBySearchKey(String searchKey) {
        return gegenparteiUIList.stream()
                .filter(gegenparteiUI -> gegenparteiUI.getReferenzId().contains(searchKey)
                        | gegenparteiUI.getBezeichnung().contains(searchKey)
                        | gegenparteiUI.getCode().contains(searchKey)
                        | gegenparteiUI.getStrasse().contains(searchKey)
                        | gegenparteiUI.getOrt().contains(searchKey)
                        | gegenparteiUI.getLand().contains(searchKey)
                        | gegenparteiUI.getSwiftKey().contains(searchKey))
                .collect(Collectors.toList());
    }

    public GegenparteiUI createOrGegenpartei(GegenparteiUI gegenparteiUI) {
        if (StringUtils.isEmpty(gegenparteiUI.getBezeichnung())) {
            log.info("gegenpartei.getBezeichnung ist empty is empty");
            return null;
        }

        GegenparteiUI foundGegenpartei = this.gegenparteiUIList.stream()
                .filter(gegenparteiUI1 -> gegenparteiUI1.getReferenzId().equals(gegenparteiUI.getReferenzId()))
                .findFirst()
                .orElse(null);

        if (foundGegenpartei == null) {
            this.gegenparteiUIList.add(gegenparteiUI);
        } else {
            foundGegenpartei.setBezeichnung(gegenparteiUI.getBezeichnung());
            foundGegenpartei.setReferenzId(gegenparteiUI.getReferenzId());
            foundGegenpartei.setCode(gegenparteiUI.getCode());
            foundGegenpartei.setLand(gegenparteiUI.getLand());
            foundGegenpartei.setPlz(gegenparteiUI.getPlz());
            foundGegenpartei.setOrt(gegenparteiUI.getOrt());
            foundGegenpartei.setProspect(gegenparteiUI.isProspect());
            foundGegenpartei.setSwiftKey(gegenparteiUI.getSwiftKey());
        }
        return foundGegenpartei;
    }


    public GegenparteiUI updateGegenpartei(String lookupId, GegenparteiUI gegenparteiUI) {

        GegenparteiUI foundGegenpartei = this.gegenparteiUIList.stream().filter(gegenparteiUI1 -> gegenparteiUI.getReferenzId().equals(lookupId)).collect(Collectors.toList()).get(0);
        if (foundGegenpartei != null) {
            foundGegenpartei.setBezeichnung(gegenparteiUI.getBezeichnung());
            foundGegenpartei.setReferenzId(gegenparteiUI.getReferenzId());
            foundGegenpartei.setCode(gegenparteiUI.getCode());
            foundGegenpartei.setLand(gegenparteiUI.getLand());
            foundGegenpartei.setPlz(gegenparteiUI.getPlz());
            foundGegenpartei.setOrt(gegenparteiUI.getOrt());
            foundGegenpartei.setProspect(gegenparteiUI.isProspect());
        } else {
            log.info("Es gibt kein Objekt mit der ReferenzId {}", lookupId);
        }

        return foundGegenpartei;
    }

    public Collection<GegenparteiUI> findAll() {
        return this.gegenparteiUIList;
    }

    private <T> List<T> loadObjectList(Class<T> type, File file) {
            try {
                CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
                CsvMapper mapper = new CsvMapper();
                MappingIterator<T> readValues =
                        mapper.reader(type).with(bootstrapSchema).readValues(file);
                return readValues.readAll();
            } catch (Exception e) {
                log.error("Error occurred while loading object list from file " + file.getName(), e);
                return Collections.emptyList();
            }
    }


}
