package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.SubstationBusinessPartner;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.SubstationBusinessPartnerService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;


@Stateless
public class SubstationBusinessPartnerServiceImpl extends AbstractEntityService<SubstationBusinessPartner>
        implements SubstationBusinessPartnerService {

	@Inject
    public SubstationBusinessPartnerServiceImpl(Repository<SubstationBusinessPartner> repository, Validator validator, Filter<SubstationBusinessPartner> prePersistFilter, Translator<SubstationBusinessPartner> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}
