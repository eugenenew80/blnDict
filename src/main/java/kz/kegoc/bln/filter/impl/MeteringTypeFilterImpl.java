package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringType;
import kz.kegoc.bln.entity.dict.translate.MeteringTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeteringTypeFilterImpl implements Filter<MeteringType> {
    public MeteringType filter(MeteringType entity) {
        return translate(prepare(entity));
    }

    private MeteringType prepare(MeteringType entity) {
        if (entity.getId()!=null) {
            MeteringType curEntity = service.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private MeteringType translate(MeteringType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeteringTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new MeteringTypeTranslate());
        translate.setLang(lang);
        translate.setMeteringType(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeteringTypeService service;

    @Inject
    private Lang defLang;
}
