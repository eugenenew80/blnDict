package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.repository.common.RepositoryOrg;
import javax.ejb.Local;

@Local
public interface PowerLineRepository extends RepositoryOrg<PowerLine> {}
