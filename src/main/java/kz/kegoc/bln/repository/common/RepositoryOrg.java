package kz.kegoc.bln.repository.common;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.repository.common.Repository;

import java.util.List;

public interface RepositoryOrg<T extends HasId> extends Repository<T> {
    List<T> selectByOrg(List<Long> orgList);
}
