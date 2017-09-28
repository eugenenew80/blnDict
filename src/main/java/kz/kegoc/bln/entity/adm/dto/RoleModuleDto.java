package kz.kegoc.bln.entity.adm.dto;

import java.util.Date;

import lombok.*;

@Data
public class RoleModuleDto {
	private Long roleId;
	private Long moduleId;
	private String moduleName;
	private Date startDate;
	private Date endDate;
}
