package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface DataSourceRepository extends JpaRepository<DataSource> {}
