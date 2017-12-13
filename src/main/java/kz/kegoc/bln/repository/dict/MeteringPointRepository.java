package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.repository.common.RepositoryOrg;

import javax.ejb.Local;

@Local
public interface MeteringPointRepository extends RepositoryOrg<MeteringPoint> {}
