package kz.kegoc.bln.service.common;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import javax.validation.*;
import kz.kegoc.bln.entity.common.HasDates;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.exception.EntityNotFoundException;
import kz.kegoc.bln.exception.InvalidArgumentException;
import kz.kegoc.bln.exception.RepositoryNotFoundException;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.repository.common.query.Query;
import kz.kegoc.bln.translator.Translator;


public abstract class AbstractEntityService<T extends HasId> implements EntityService<T> {
    public AbstractEntityService() {}

    public AbstractEntityService(Repository<T> repository) {
        this.repository = repository;
    }
    
    public AbstractEntityService(Repository<T> repository, Validator validator) {
        this(repository);
        this.validator = validator;
    }

	public AbstractEntityService(Repository<T> repository, Validator validator, Filter<T> prePersistFilter) {
		this(repository);
		this.validator = validator;
		this.prePersistFilter = prePersistFilter;
	}

	public AbstractEntityService(Repository<T> repository, Validator validator, Filter<T> prePersistFilter, Translator<T> translator) {
		this(repository);
		this.validator = validator;
		this.prePersistFilter = prePersistFilter;
		this.translator = translator;
	}

    
	public List<T> findAll() {
		if (repository==null)
			throw new RepositoryNotFoundException();

		List<T> list = repository.selectAll();
		if (translator!=null && lang!=null) {
			return list.stream()
					.map(t -> translator.translate(t, lang))
					.collect(Collectors.toList());
		}
		return list;
	}


	public List<T> find(Query query) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		List<T> list = repository.select(query);
		if (translator!=null && lang!=null) {
			return list.stream()
					.map(t -> translator.translate(t, lang))
					.collect(Collectors.toList());
		}
		return list;
	}


	public T findById(Object entityId) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entityId==null)
			throw new InvalidArgumentException();
		
		T entity = repository.selectById(entityId);
		if (entity==null)
			throw new EntityNotFoundException(entityId);

		if (translator!=null && lang!=null)
			entity = translator.translate(entity, lang);

		return entity;
	}


	public T create(T entity) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entity==null) 
			throw new InvalidArgumentException();
		
		if (entity.getId()!=null)
			throw new InvalidArgumentException(entity);

		if (prePersistFilter !=null)
			entity = prePersistFilter.filter(entity);

		if (entity instanceof HasDates)
			((HasDates) entity).setCreateDate(LocalDateTime.now());

		if (validator!=null)
			validate(entity);

		return repository.insert(entity);
	}

	
	public T update(T entity) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entity==null) 
			throw new InvalidArgumentException();
		
		if (entity.getId()==null) 
			throw new InvalidArgumentException(entity);

		if (prePersistFilter !=null)
			entity = prePersistFilter.filter(entity);

		T currentEntity = findById(entity.getId());
		if (entity instanceof HasDates) {
			((HasDates) entity).setCreateDate( ((HasDates)currentEntity).getCreateDate() );
			((HasDates) entity).setLastUpdateDate(LocalDateTime.now());
		}

		if (validator!=null)
			validate(entity);

		return repository.update(entity);
	}

	
	public boolean delete(Long entityId) {
		if (repository==null)
			throw new RepositoryNotFoundException();

		if (entityId==null) 
			throw new InvalidArgumentException();		
		
		findById(entityId);
		
		return repository.delete(entityId); 
	}


	public void setLang(Lang lang) {
    	this.lang = lang;
	}


	private void validate(T entity) {
		Set<ConstraintViolation<T>> violations =  validator.validate(entity);
		if (violations.size()>0) {
			ConstraintViolation<T> violation = violations.iterator().next();
			throw new ValidationException(violation.getPropertyPath() + ": " + violation.getMessage());
		}
	}


	private Repository<T> repository;
	private Validator validator;
	private Filter<T> prePersistFilter;
	private Translator<T> translator;
	private Lang lang;
}
