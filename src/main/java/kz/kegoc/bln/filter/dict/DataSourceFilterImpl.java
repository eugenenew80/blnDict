package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.entity.dict.translate.DataSourceTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.DataSourceService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class DataSourceFilterImpl extends AbstractFilter<DataSource> implements Filter<DataSource> {
    public DataSource filter(DataSource entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private DataSource prepare(DataSource entity, SessionContext context) {
        if (entity.getId()!=null) {
            DataSource curEntity = service.findById(entity.getId(), context);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private DataSource translate(DataSource entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        DataSourceTranslate translate = entity.getTranslations().getOrDefault(lang, new DataSourceTranslate());
        translate = addUpdateInfo(translate, context);
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
