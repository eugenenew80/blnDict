package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.entity.dict.translate.BankTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.BankService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class BankFilterImpl extends AbstractFilter<Bank> implements Filter<Bank> {
    public Bank filter(Bank entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Bank prepare(Bank entity, SessionContext context) {
        if (entity.getId()!=null) {
            Bank curEntity = service.findById(entity.getId(), context);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getParentBank()!=null && entity.getParentBank().getId()==null)
            entity.setParentBank(null);

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private Bank translate(Bank entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        BankTranslate translate = entity.getTranslations().getOrDefault(lang, new BankTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setBank(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private BankService service;

    @Inject
    private Lang defLang;
}
