package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.entity.dict.translate.UnitTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.UnitService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class UnitFilterImpl extends AbstractFilter<Unit> implements Filter<Unit> {
    public Unit filter(Unit entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Unit prepare(Unit entity, SessionContext context) {
        if (entity.getId()!=null) {
            Unit curEntity = service.findById(entity.getId(), null);

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

    private Unit translate(Unit entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        UnitTranslate translate = entity.getTranslations().getOrDefault(lang, new UnitTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setUnit(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private UnitService service;

    @Inject
    private Lang defLang;
}
