package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.entity.dict.translate.OrganizationTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.OrganizationService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class OrganizationFilterImpl implements Filter<Organization> {
    public Organization filter(Organization entity) {
        return translate(prepare(entity));
    }

    private Organization prepare(Organization entity) {
        if (entity.getId()!=null) {
            Organization curEntity = companyService.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private Organization translate(Organization entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        OrganizationTranslate translate = entity.getTranslations().getOrDefault(lang, new OrganizationTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

        translate.setLang(lang);
        translate.setOrg(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private OrganizationService companyService;

    @Inject
    private Lang defLang;
}
