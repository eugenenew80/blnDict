package kz.kegoc.bln.service.dict;

import kz.kegoc.bln.entity.dict.Country;
import kz.kegoc.bln.service.common.EntityService;

import javax.ejb.Local;

@Local
public interface CountryService extends EntityService<Country> {}
