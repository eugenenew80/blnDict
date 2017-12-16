package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.MeteringPointType;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface MeteringPointTypeRepository extends JpaRepository<MeteringPointType> {}
