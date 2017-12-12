package kz.kegoc.bln.service.ecm.impl;

import kz.kegoc.bln.entity.ecm.Content;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.ecm.ContentService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;


@Stateless
public class ContentServiceImpl extends AbstractEntityService<Content> implements ContentService {
	@Inject
    public ContentServiceImpl(Repository<Content> repository, Validator validator, Filter<Content> prePersistFilter, Translator<Content> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
