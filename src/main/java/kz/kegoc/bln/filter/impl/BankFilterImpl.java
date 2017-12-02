package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.entity.dict.translate.BankTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.BankService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class BankFilterImpl implements Filter<Bank> {
    public Bank filter(Bank entity) {
        return translate(prepare(entity));
    }

    private Bank prepare(Bank entity) {
        if (entity.getId()!=null) {
            Bank curEntity = bankService.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private Bank translate(Bank entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        BankTranslate translate = entity.getTranslations().getOrDefault(lang, new BankTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

        translate.setLang(lang);
        translate.setBank(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private BankService bankService;

    @Inject
    private Lang defLang;
}
