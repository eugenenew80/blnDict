package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict. PostAddress;
import kz.kegoc.bln.entity.dict.translate. PostAddressTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict. PostAddressService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PostAddressFilterImpl extends AbstractFilter<PostAddress> implements Filter< PostAddress> {
    public  PostAddress filter(PostAddress entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private PostAddress prepare(PostAddress entity, SessionContext context) {
        if (entity.getId()!=null) {
            PostAddress curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private PostAddress translate(PostAddress entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PostAddressTranslate translate = entity.getTranslations().getOrDefault(lang, new  PostAddressTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setPostAddress(entity);
        translate.setName(entity.getName());
        translate.setLocality(entity.getLocality());
        translate.setStreet(entity.getStreet());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private  PostAddressService service;

    @Inject
    private Lang defLang;
}
