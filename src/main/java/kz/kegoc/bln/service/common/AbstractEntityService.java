package kz.kegoc.bln.service.common;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.*;

import kz.kegoc.bln.entity.common.HasOrg;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.exception.EntityNotFoundException;
import kz.kegoc.bln.exception.InvalidArgumentException;
import kz.kegoc.bln.exception.RepositoryNotFoundException;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.JpaRepository;
import kz.kegoc.bln.repository.common.query.Query;
import kz.kegoc.bln.translator.Translator;
import org.apache.commons.lang3.StringUtils;

public abstract class AbstractEntityService<T extends HasId> implements EntityService<T> {
    public AbstractEntityService() {}

    public AbstractEntityService(JpaRepository<T> repository) {
        this.repository = repository;
    }
    
    public AbstractEntityService(JpaRepository<T> repository, Validator validator) {
        this(repository);
        this.validator = validator;
    }

	public AbstractEntityService(JpaRepository<T> repository, Validator validator, Filter<T> prePersistFilter) {
		this(repository);
		this.validator = validator;
		this.prePersistFilter = prePersistFilter;
	}

	public AbstractEntityService(JpaRepository<T> repository, Validator validator, Filter<T> prePersistFilter, Translator<T> translator) {
		this(repository);
		this.validator = validator;
		this.prePersistFilter = prePersistFilter;
		this.translator = translator;
	}


	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findAll(SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		Lang lang = context!=null && context.getLang()!=null ? context.getLang() : Lang.RU;
		return translateList(repository.selectAll(), lang);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> find(Query query, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		Lang lang = context!=null && context.getLang()!=null ? context.getLang() : Lang.RU;
		return translateList(repository.select(query), lang);
	}


	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> find(CriteriaQuery<T> query, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		Lang lang = context!=null && context.getLang()!=null ? context.getLang() : Lang.RU;
		return translateList(repository.select(query), lang);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> find(String code, String shortName, String name, SessionContext context) {
    	CriteriaBuilder cb =  repository.getEntityManager().getCriteriaBuilder();
    	CriteriaQuery<T> query = cb.createQuery(repository.getClazz());
		Root<T> root = query.from(repository.getClazz());

		Predicate criteria = cb.conjunction();
		if (HasOrg.class.isAssignableFrom(repository.getClazz()))
			criteria = cb.isTrue(root.get("org").get("id").in(buildOrgList(context)));

		if (StringUtils.isNotEmpty(shortName))
			criteria = cb.and(criteria, cb.like(cb.upper(root.get("shortName")), "%" + shortName.toUpperCase() + "%"));

		if (StringUtils.isNotEmpty(name))
			criteria = cb.and(criteria, cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%"));

		if (StringUtils.isNotEmpty(code))
			criteria = cb.and(criteria, cb.like(root.get("code"), code + "%"));

		if (HasId.class.isAssignableFrom(repository.getClazz()))
			query.orderBy(cb.asc(root.get("id")));

		return find(query.where(criteria), context);
	}

	private List<T> translateList(List<T> list, Lang lang) {
		if (translator!=null && lang!=null) {
			return list.stream()
				.map(t -> translator.translate(t, lang))
				.collect(Collectors.toList());
		}
		return list;
	}


	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public T findById(Object entityId, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entityId==null)
			throw new InvalidArgumentException();

		T entity = repository.selectById(entityId);
		if (entity==null)
			throw new EntityNotFoundException(entityId);

		Lang lang = context!=null && context.getLang()!=null ? context.getLang() : Lang.RU;
		if (translator!=null && lang!=null)
			entity = translator.translate(entity, lang);

		return entity;
	}


	public T create(T entity, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entity==null)
			throw new InvalidArgumentException();

		if (entity.getId()!=null)
			throw new InvalidArgumentException(entity);

		if (prePersistFilter !=null)
			entity = prePersistFilter.filter(entity, context);

		if (validator!=null)
			validate(entity);

		return repository.insert(entity);
	}


	public T update(T entity, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entity==null)
			throw new InvalidArgumentException();

		if (entity.getId()==null)
			throw new InvalidArgumentException(entity);

		if (prePersistFilter !=null)
			entity = prePersistFilter.filter(entity, context);

		if (validator!=null)
			validate(entity);

		return repository.update(entity);
	}

	public boolean delete(Long entityId, SessionContext context) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entityId==null)
			throw new InvalidArgumentException();

		return repository.delete(entityId);
	}

	private void validate(T entity) {
		Set<ConstraintViolation<T>> violations =  validator.validate(entity);
		if (violations.size()>0) {
			ConstraintViolation<T> violation = violations.iterator().next();
			throw new ValidationException(violation.getPropertyPath() + ": " + violation.getMessage());
		}
	}

	protected List<Long> buildOrgList(SessionContext context) {
		if (context.getUser().getOrgId()==1)
			return Arrays.asList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l, 11l, 12l, 13l, 14l);
		else
			return Arrays.asList(context.getUser().getOrgId());
	}

	protected JpaRepository<T> repository;
	protected Validator validator;
	protected Filter<T> prePersistFilter;
	protected Translator<T> translator;
}
