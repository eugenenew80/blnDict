package kz.kegoc.bln.entity.common;

import kz.kegoc.bln.entity.adm.User;

public interface HasUser {
	User getCreateBy();
	User getLastUpdateBy();
	
	void setCreateBy(User createBy);
	void setLastUpdateBy(User lastUpdateBy) ;
}
