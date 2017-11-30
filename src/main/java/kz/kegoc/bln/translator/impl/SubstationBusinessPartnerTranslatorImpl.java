package kz.kegoc.bln.translator.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.SubstationBusinessPartner;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SubstationBusinessPartnerTranslatorImpl implements Translator<SubstationBusinessPartner> {
    public SubstationBusinessPartner translate(SubstationBusinessPartner entity, Lang lang) {
        entity.setLang(lang);
        return entity;
    }

    @Inject
    private Lang defLang;
}
