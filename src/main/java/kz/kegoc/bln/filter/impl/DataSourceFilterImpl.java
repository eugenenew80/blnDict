package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.entity.dict.translate.DataSourceTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.DataSourceService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class DataSourceFilterImpl implements Filter<DataSource> {
    public DataSource filter(DataSource entity) {
        if (entity.getId()!=null) {
            DataSource curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private DataSource translate(DataSource entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        DataSourceTranslate translate = entity.getTranslations().getOrDefault(lang, new DataSourceTranslate());
        translate.setLang(lang);
        translate.setDataSource(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private DataSourceService service;

    @Inject
    private Lang defLang;
}
