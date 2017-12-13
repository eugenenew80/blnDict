package kz.kegoc.bln.service.common;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.exception.RepositoryNotFoundException;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.repository.common.RepositoryOrg;
import kz.kegoc.bln.translator.Translator;
import kz.kegoc.bln.webapi.filters.SessionContext;

import javax.validation.Validator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class AbstractEntityServiceOrg <T extends HasId> extends AbstractEntityService<T> implements EntityServiceOrg<T> {
    public AbstractEntityServiceOrg(Repository<T> repository, Validator validator, Filter<T> prePersistFilter, Translator<T> translator) {
        super(repository, validator, prePersistFilter, translator);
    }

    public List<T> findByOrg(SessionContext context) {
        if (repository==null)
            throw new RepositoryNotFoundException();

        Lang lang = context!=null && context.getLang()!=null ? context.getLang() : Lang.RU;

        List<Long> orgList = new ArrayList<>();
        if (context.getUser().getOrgId()==1)
            orgList = Arrays.asList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l, 11l, 12l, 13l, 14l);
        else
            orgList.add(context.getUser().getOrgId());



        List<T> list = ((RepositoryOrg<T>)repository).selectByOrg(orgList);
        if (translator!=null && lang!=null) {
            return list.stream()
                .map(t -> translator.translate(t, lang))
                .collect(Collectors.toList());
        }
        return list;
    }}
