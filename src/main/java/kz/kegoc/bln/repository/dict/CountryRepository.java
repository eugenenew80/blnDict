package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Country;
import kz.kegoc.bln.repository.common.Repository;

import javax.ejb.Local;

@Local
public interface CountryRepository extends Repository<Country> {}
