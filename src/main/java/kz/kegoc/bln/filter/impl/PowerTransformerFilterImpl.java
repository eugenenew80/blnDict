package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerTransformer;
import kz.kegoc.bln.entity.dict.translate.PowerTransformerTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerTransformerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class PowerTransformerFilterImpl implements Filter<PowerTransformer> {
    public PowerTransformer filter(PowerTransformer entity, SessionContext context) {
        return translate(prepare(entity));
    }

    private PowerTransformer prepare(PowerTransformer entity) {
        if (entity.getId()!=null) {
            PowerTransformer curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private PowerTransformer translate(PowerTransformer entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerTransformerTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerTransformerTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

        translate.setLang(lang);
        translate.setPowerTransformer(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerTransformerService service;

    @Inject
    private Lang defLang;
}
