package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageTransType;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.VoltageTransTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class VoltageTransTypeFilterImpl implements Filter<VoltageTransType> {
    public VoltageTransType filter(VoltageTransType entity) {
        if (entity.getId()!=null) {
            VoltageTransType curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private VoltageTransType translate(VoltageTransType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        VoltageTransTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new VoltageTransTypeTranslate());
        translate.setLang(lang);
        translate.setVoltageTransType(entity);
        translate.setName(entity.getName());
        translate.setManufacturer(entity.getManufacturer());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private VoltageTransTypeService service;

    @Inject
    private Lang defLang;
}
