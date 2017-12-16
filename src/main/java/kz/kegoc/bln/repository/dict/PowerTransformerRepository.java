package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.PowerTransformer;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface PowerTransformerRepository extends JpaRepository<PowerTransformer> {}
