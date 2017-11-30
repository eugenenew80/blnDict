package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.entity.dict.translate.ReactorTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.ReactorService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class ReactorFilterImpl implements Filter<Reactor> {
    public Reactor filter(Reactor entity) {
        if (entity.getId()!=null) {
            Reactor curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private Reactor translate(Reactor entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        ReactorTranslate translate = entity.getTranslations().getOrDefault(lang, new ReactorTranslate());
        translate.setLang(lang);
        translate.setReactor(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private ReactorService service;

    @Inject
    private Lang defLang;
}
