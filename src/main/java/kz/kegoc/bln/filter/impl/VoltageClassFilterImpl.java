package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageClass;
import kz.kegoc.bln.entity.dict.translate.VoltageClassTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.VoltageClassService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class VoltageClassFilterImpl implements Filter<VoltageClass> {
    public VoltageClass filter(VoltageClass entity) {
        if (entity.getId()!=null) {
            VoltageClass curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private VoltageClass translate(VoltageClass entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        VoltageClassTranslate translate = entity.getTranslations().getOrDefault(lang, new VoltageClassTranslate());
        translate.setLang(lang);
        translate.setVoltageClass(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private VoltageClassService service;

    @Inject
    private Lang defLang;
}
