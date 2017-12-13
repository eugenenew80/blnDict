package kz.kegoc.bln.entity.ecm;

import kz.kegoc.bln.entity.common.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.time.LocalDateTime;
import kz.kegoc.bln.entity.adm.User;

@Data
@EqualsAndHashCode(of= {"id"})
public class Content implements HasId, HasLang, HasDates, HasUser {
	private Long id;

	@NotNull @Size(max=128)
	private String sourceTable;

	@NotNull
	private Long sourceId;

	@NotNull @Size(max=255)
	private String fileName;

	@Size(max=10)
	private String fileExt;

	@NotNull
	private Long fileSize;

	@NotNull
	private ContentType contentType;

	@NotNull
	@Lob
	private Blob content;

	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private User createBy;
	private User lastUpdateBy;
	private Lang lang;
}
