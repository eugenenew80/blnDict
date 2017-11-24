package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Company;
import kz.kegoc.bln.entity.dict.translate.CompanyTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.CompanyService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class CompanyFilterImpl implements Filter<Company> {
    public Company filter(Company entity) {
        if (entity.getId()!=null) {
            Company curEntity = companyService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private Company translate(Company entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        CompanyTranslate translate = entity.getTranslations().getOrDefault(lang, new CompanyTranslate());
        translate.setLang(lang);
        translate.setCompany(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private CompanyService companyService;

    @Inject
    private Lang defLang;
}
