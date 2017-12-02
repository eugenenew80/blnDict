package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.entity.dict.translate.AccountingTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.AccountingTypeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class AccountingTypeFilterImpl implements Filter<AccountingType> {
    public AccountingType filter(AccountingType entity) {
        return translate(prepare(entity));
    }

    private AccountingType prepare(AccountingType entity) {
        if (entity.getId()!=null) {
            AccountingType curEntity = accountingTypeService.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations() == null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private AccountingType translate(AccountingType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        AccountingTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new AccountingTypeTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

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
