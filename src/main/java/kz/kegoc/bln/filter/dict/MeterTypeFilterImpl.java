package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.entity.dict.translate.MeterTypeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeterTypeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeterTypeFilterImpl extends AbstractFilter<MeterType> implements Filter<MeterType> {
    public MeterType filter(MeterType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private MeterType prepare(MeterType entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeterType curEntity = service.findById(entity.getId(), context);

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

    private MeterType translate(MeterType entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeterTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new MeterTypeTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setMeterType(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeterTypeService service;

    @Inject
    private Lang defLang;
}
