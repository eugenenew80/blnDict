package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.repository.common.RepositoryOrg;
import javax.ejb.Local;

@Local
public interface EnergySourceRepository extends RepositoryOrg<EnergySource> {}
