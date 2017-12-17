package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyNode;
import kz.kegoc.bln.entity.dict.translate.EnergyNodeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergyNodeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class EnergyNodeFilterImpl extends AbstractFilter<EnergyNode> implements Filter<EnergyNode> {
    public EnergyNode filter(EnergyNode entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private EnergyNode prepare(EnergyNode entity, SessionContext context) {
        if (entity.getId()!=null) {
            EnergyNode curEntity = service.findById(entity.getId(), context);

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

    private EnergyNode translate(EnergyNode entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        EnergyNodeTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergyNodeTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setEnergyNode(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private EnergyNodeService service;

    @Inject
    private Lang defLang;
}
