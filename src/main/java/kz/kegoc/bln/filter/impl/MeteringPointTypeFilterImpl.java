package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointType;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeteringPointTypeFilterImpl implements Filter<MeteringPointType> {
    public MeteringPointType filter(MeteringPointType entity) {
        return translate(entity);
    }

    private MeteringPointType prepare(MeteringPointType entity) {
        if (entity.getId()!=null) {
            MeteringPointType curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private MeteringPointType translate(MeteringPointType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeteringPointTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new MeteringPointTypeTranslate());
        translate.setLang(lang);
        translate.setMeteringPointType(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeteringPointTypeService service;

    @Inject
    private Lang defLang;
}
