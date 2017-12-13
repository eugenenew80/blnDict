package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.repository.common.RepositoryOrg;

import javax.ejb.Local;

@Local
public interface ReactorRepository extends RepositoryOrg<Reactor> { }
