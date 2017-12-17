package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.entity.dict.translate.ReactorTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ReactorService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class ReactorFilterImpl extends AbstractFilter<Reactor> implements Filter<Reactor> {
    public Reactor filter(Reactor entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Reactor prepare(Reactor entity, SessionContext context) {
        if (entity.getId()!=null) {
            Reactor curEntity = service.findById(entity.getId(), context);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getOrg()==null)
            entity.setOrg(new Organization(context.getUser().getOrgId()));

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private Reactor translate(Reactor entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        ReactorTranslate translate = entity.getTranslations().getOrDefault(lang, new ReactorTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setReactor(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private ReactorService service;

    @Inject
    private Lang defLang;
}
