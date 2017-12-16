package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.MeteringType;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface MeteringTypeRepository extends JpaRepository<MeteringType> {}
