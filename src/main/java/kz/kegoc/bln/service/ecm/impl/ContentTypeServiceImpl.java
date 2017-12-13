package kz.kegoc.bln.service.ecm.impl;

import kz.kegoc.bln.entity.ecm.ContentType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.ecm.ContentTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.ecm.ContentTypeService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class ContentTypeServiceImpl extends AbstractEntityService<ContentType> implements ContentTypeService {
	@Inject
    public ContentTypeServiceImpl(ContentTypeRepository repository, Validator validator, Filter<ContentType> prePersistFilter, Translator<ContentType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
