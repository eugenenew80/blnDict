package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.entity.dict.translate.AccountingTypeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.AccountingTypeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class AccountingTypeFilterImpl extends AbstractFilter<AccountingType> implements Filter<AccountingType> {
    public AccountingType filter(AccountingType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private AccountingType prepare(AccountingType entity, SessionContext context) {
        if (entity.getId()!=null) {
            AccountingType curEntity = service.findById(entity.getId(), context);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations() == null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private AccountingType translate(AccountingType entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        AccountingTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new AccountingTypeTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setAccountingType(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private AccountingTypeService service;

    @Inject
    private Lang defLang;
}
