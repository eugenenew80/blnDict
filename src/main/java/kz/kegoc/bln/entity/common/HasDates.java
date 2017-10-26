package kz.kegoc.bln.entity.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface HasDates  {
	LocalDateTime getCreateDate();
	LocalDateTime getUpdateDate();
	
	void setCreateDate(LocalDateTime createDate);
	void setUpdateDate(LocalDateTime updateDate) ;
}
