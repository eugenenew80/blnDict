package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.entity.dict.translate.AccountingTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.AccountingTypeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class AccountingTypeFilterImpl implements Filter<AccountingType> {
    public AccountingType filter(AccountingType entity) {
        if (entity.getId()!=null) {
            AccountingType curEntity = accountingTypeService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private AccountingType translate(AccountingType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        AccountingTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new AccountingTypeTranslate());
        translate.setLang(lang);
        translate.setAccountingType(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private AccountingTypeService accountingTypeService;

    @Inject
    private Lang defLang;
}
