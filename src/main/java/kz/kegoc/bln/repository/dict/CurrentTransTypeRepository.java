package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.CurrentTransType;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface CurrentTransTypeRepository extends JpaRepository<CurrentTransType> {}
