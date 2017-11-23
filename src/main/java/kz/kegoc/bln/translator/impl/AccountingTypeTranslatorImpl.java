package kz.kegoc.bln.translator.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.entity.dict.translate.AccountingTypeTranslate;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountingTypeTranslatorImpl implements Translator<AccountingType> {
    public AccountingType translate(AccountingType entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        AccountingTypeTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null)
            entity.setName(translate.getName());

        return entity;
    }

    @Inject
    private Lang defLang;
}
