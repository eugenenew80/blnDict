package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLineType;
import kz.kegoc.bln.entity.dict.translate.PowerLineTypeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerLineTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PowerLineTypeFilterImpl extends AbstractFilter<PowerLineType> implements Filter<PowerLineType> {
    public PowerLineType filter(PowerLineType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private PowerLineType prepare(PowerLineType entity, SessionContext context) {
        if (entity.getId()!=null) {
            PowerLineType curEntity = service.findById(entity.getId(), null);

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

    private PowerLineType translate(PowerLineType entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerLineTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLineTypeTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setPowerLineType(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerLineTypeService service;

    @Inject
    private Lang defLang;
}
