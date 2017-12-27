package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasOrg;
import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.entity.dict.PowerLinePart;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.PowerLineRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.PowerLineService;
import kz.kegoc.bln.translator.Translator;
import kz.kegoc.bln.webapi.filters.SessionContext;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.criteria.*;
import javax.validation.Validator;
import java.util.List;

@Stateless
public class PowerLineServiceImpl extends AbstractEntityService<PowerLine> implements PowerLineService {

	@Inject
    public PowerLineServiceImpl(PowerLineRepository repository, Validator validator, Filter<PowerLine> prePersistFilter, Translator<PowerLine> translator) {
        super(repository, validator, prePersistFilter, translator);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<PowerLine> find(String code, String shortName, String name, SessionContext context) {
        CriteriaBuilder cb =  repository.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PowerLine> query = cb.createQuery(repository.getClazz());
        Root<PowerLine> root = query.from(repository.getClazz());

        Predicate criteria = cb.conjunction();
        if (HasOrg.class.isAssignableFrom(repository.getClazz())) {
            Join<PowerLine, PowerLinePart> join = root.join("powerLineParts");
            criteria = cb.isTrue(join.get("org").get("id").in(buildOrgList(context)));
            query.distinct(true);
        }

        if (StringUtils.isNotEmpty(shortName))
            criteria = cb.and(criteria, cb.like(cb.upper(root.get("shortName")), "%" + shortName.toUpperCase() + "%"));

        if (StringUtils.isNotEmpty(name))
            criteria = cb.and(criteria, cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%"));

        if (StringUtils.isNotEmpty(code))
            criteria = cb.and(criteria, cb.like(root.get("code"), "%" + code + "%"));

        if (HasId.class.isAssignableFrom(repository.getClazz()))
            query.orderBy(cb.asc(root.get("id")));

        query.orderBy(cb.desc(root.get("lastUpdateDate")));
        return find(query.where(criteria), context);
    }
}
