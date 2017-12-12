package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergySourceType;
import kz.kegoc.bln.entity.dict.translate.EnergySourceTypeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergySourceTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class EnergySourceTypeFilterImpl extends AbstractFilter<EnergySourceType> implements Filter<EnergySourceType> {
    public EnergySourceType filter(EnergySourceType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private EnergySourceType prepare(EnergySourceType entity, SessionContext context) {
        if (entity.getId()!=null) {
            EnergySourceType curEntity = service.findById(entity.getId(), null);

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

    private EnergySourceType translate(EnergySourceType entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        EnergySourceTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergySourceTypeTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setEnergySourceType(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private EnergySourceTypeService service;

    @Inject
    private Lang defLang;
}
