package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.entity.dict.translate.BankTranslate;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BankTranslatorImpl implements Translator<Bank> {
    public Bank translate(Bank entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        BankTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null)
            entity.setName(translate.getName());

        return entity;
    }

    @Inject
    private Lang defLang;
}
