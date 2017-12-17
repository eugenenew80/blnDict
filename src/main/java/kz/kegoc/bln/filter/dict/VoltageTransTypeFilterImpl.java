package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageTransType;
import kz.kegoc.bln.entity.dict.translate.VoltageTransTypeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.VoltageTransTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class VoltageTransTypeFilterImpl extends AbstractFilter<VoltageTransType> implements Filter<VoltageTransType> {
    public VoltageTransType filter(VoltageTransType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private VoltageTransType prepare(VoltageTransType entity, SessionContext context) {
        if (entity.getId()!=null) {
            VoltageTransType curEntity = service.findById(entity.getId(), context);

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

    private VoltageTransType translate(VoltageTransType entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        VoltageTransTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new VoltageTransTypeTranslate());
        translate = addUpdateInfo(translate, context);
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
