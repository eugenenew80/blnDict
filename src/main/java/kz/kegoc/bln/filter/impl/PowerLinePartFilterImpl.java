package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerLinePart;
import kz.kegoc.bln.entity.dict.translate.PowerLinePartTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerLinePartService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PowerLinePartFilterImpl implements Filter<PowerLinePart> {
    public PowerLinePart filter(PowerLinePart entity) {
        return translate(prepare(entity));
    }

    private PowerLinePart prepare(PowerLinePart entity) {
        if (entity.getId()!=null) {
            PowerLinePart curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private PowerLinePart translate(PowerLinePart entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerLinePartTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerLinePartTranslate());
        translate.setLang(lang);
        translate.setPowerLinePart(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerLinePartService service;

    @Inject
    private Lang defLang;
}
