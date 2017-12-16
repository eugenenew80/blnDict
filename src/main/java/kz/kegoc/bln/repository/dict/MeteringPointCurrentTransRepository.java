package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface MeteringPointCurrentTransRepository extends JpaRepository<MeteringPointCurrentTrans> {}
