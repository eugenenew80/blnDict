package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BankAccountTranslatorImpl implements Translator<BankAccount> {
    public BankAccount translate(BankAccount entity, Lang lang) {
        entity.setLang(lang);
        return entity;
    }

    @Inject
    private Lang defLang;
}
