package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Substation;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface SubstationRepository extends JpaRepository<Substation> {}
