package kz.kegoc.bln.entity.adm.dto;

import java.util.Date;

import lombok.*;

@Data
public class RoleDictDto {
	private Long roleId;
	private Long dictId;
	private String dictName;
	private Date startDate;
	private Date endDate;
}
