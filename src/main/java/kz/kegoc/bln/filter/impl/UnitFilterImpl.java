package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.entity.dict.translate.UnitTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.UnitService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class UnitFilterImpl implements Filter<Unit> {
    public Unit filter(Unit entity) {
        return translate(prepare(entity));
    }

    private Unit prepare(Unit entity) {
        if (entity.getId()!=null) {
            Unit curEntity = service.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private Unit translate(Unit entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        UnitTranslate translate = entity.getTranslations().getOrDefault(lang, new UnitTranslate());
        translate.setLang(lang);
        translate.setUnit(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private UnitService service;

    @Inject
    private Lang defLang;
}
