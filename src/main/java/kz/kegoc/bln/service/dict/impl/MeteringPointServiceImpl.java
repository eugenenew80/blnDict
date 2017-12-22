package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Validator;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasOrg;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeteringPointRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointService;
import kz.kegoc.bln.translator.Translator;
import kz.kegoc.bln.webapi.filters.SessionContext;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Stateless
public class MeteringPointServiceImpl extends AbstractEntityService<MeteringPoint> implements MeteringPointService {
	@Inject
    public MeteringPointServiceImpl(MeteringPointRepository repository, Validator validator, Filter<MeteringPoint> prePersistFilter, Translator<MeteringPoint> translator) {
        super(repository, validator, prePersistFilter, translator);
    }


    @Override
    public List<MeteringPoint> findEveryWhere(String value, SessionContext context) {
        CriteriaBuilder cb =  repository.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MeteringPoint> query = cb.createQuery(repository.getClazz());
        Root<MeteringPoint> root = query.from(repository.getClazz());

        Predicate criteria = cb.conjunction();
        if (HasOrg.class.isAssignableFrom(repository.getClazz()))
            criteria = cb.isTrue(root.get("org").get("id").in(buildOrgList(context)));

        if (StringUtils.isNotEmpty(value)) {
            Predicate criteriaEveryWhere = cb.like(cb.upper(root.get("name")), "%" + value.toUpperCase() + "%");
            criteriaEveryWhere = cb.or(criteriaEveryWhere, cb.like(root.get("shortName"), "%" + value.toUpperCase() + "%"));
            criteriaEveryWhere = cb.or(criteriaEveryWhere, cb.like(root.get("code"), value + "%"));
            criteria = cb.and(criteria, criteriaEveryWhere);
        }

        if (HasId.class.isAssignableFrom(repository.getClazz()))
            query.orderBy(cb.asc(root.get("id")));

        return find(query.where(criteria), context);    }
}
