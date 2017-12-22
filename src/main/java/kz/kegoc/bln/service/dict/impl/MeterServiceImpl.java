package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Validator;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasOrg;
import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeterRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeterService;
import kz.kegoc.bln.translator.Translator;
import kz.kegoc.bln.webapi.filters.SessionContext;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Stateless
public class MeterServiceImpl extends AbstractEntityService<Meter>
        implements MeterService {
    
	@Inject
    public MeterServiceImpl(MeterRepository repository, Validator validator, Filter<Meter> prePersistFilter, Translator<Meter> translator) {
        super(repository, validator, prePersistFilter, translator);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Meter> find(String code, String shortName, String name, String serialNumber, SessionContext context) {
        CriteriaBuilder cb =  repository.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Meter> query = cb.createQuery(repository.getClazz());
        Root<Meter> root = query.from(repository.getClazz());

        Predicate criteria = cb.conjunction();
        if (HasOrg.class.isAssignableFrom(repository.getClazz()))
            criteria = cb.isTrue(root.get("org").get("id").in(buildOrgList(context)));

        if (StringUtils.isNotEmpty(shortName))
            criteria = cb.and(criteria, cb.like(cb.upper(root.get("shortName")), "%" + shortName.toUpperCase() + "%"));

        if (StringUtils.isNotEmpty(name))
            criteria = cb.and(criteria, cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%"));

        if (StringUtils.isNotEmpty(code))
            criteria = cb.and(criteria, cb.like(root.get("code"), code + "%"));

        if (StringUtils.isNotEmpty(serialNumber))
            criteria = cb.and(criteria, cb.like(root.get("serialNumber"), serialNumber + "%"));

        if (HasId.class.isAssignableFrom(repository.getClazz()))
            query.orderBy(cb.asc(root.get("id")));

        return find(query.where(criteria), context);
    }


    @Override
    public List<Meter> findEveryWhere(String value, SessionContext context) {
        CriteriaBuilder cb =  repository.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Meter> query = cb.createQuery(repository.getClazz());
        Root<Meter> root = query.from(repository.getClazz());

        Predicate criteria = cb.conjunction();
        if (HasOrg.class.isAssignableFrom(repository.getClazz()))
            criteria = cb.isTrue(root.get("org").get("id").in(buildOrgList(context)));

        if (StringUtils.isNotEmpty(value)) {
            Predicate criteriaEveryWhere = cb.like(cb.upper(root.get("name")), "%" + value.toUpperCase() + "%");
            criteriaEveryWhere = cb.or(criteriaEveryWhere, cb.like(root.get("serialNumber"), value + "%"));
            criteria = cb.and(criteria, criteriaEveryWhere);
        }

        if (HasId.class.isAssignableFrom(repository.getClazz()))
            query.orderBy(cb.asc(root.get("id")));

        return find(query.where(criteria), context);
    }
}
