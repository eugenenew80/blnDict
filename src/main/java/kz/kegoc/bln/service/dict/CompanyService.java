package kz.kegoc.bln.service.dict;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.service.common.EntityService;

import javax.ejb.Local;

@Local
public interface CompanyService extends EntityService<Organization> {}
