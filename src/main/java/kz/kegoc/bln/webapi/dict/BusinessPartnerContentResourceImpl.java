package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.ecm.Content;
import kz.kegoc.bln.entity.ecm.dto.BusinessPartnerContentDto;
import kz.kegoc.bln.service.ecm.ContentService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BusinessPartnerContentResourceImpl {

	@GET
	public Response getAll(@PathParam("businessPartnerId") Long businessPartnerId, @HeaderParam("lang") Lang lang) {
		System.out.println(businessPartnerId);
		List<BusinessPartnerContentDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.filter(it -> StringUtils.equals(it.getSourceTable(), "DICT_BUSINESS_PARTNERS") && it.getSourceId().equals(businessPartnerId))
			.map(it-> mapper.getMapper().map(it, BusinessPartnerContentDto.class))
			.collect(Collectors.toList());

		return Response.ok()
			.entity(new GenericEntity<Collection<BusinessPartnerContentDto>>(list){})
			.build();
	}


	@GET
	@Path("/{id : \\d+}")
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		Content entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, BusinessPartnerContentDto.class))
			.build();
	}

	@GET
	@Path("/{id : \\d+}/download")
	public Response getContent(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		Content entity = service.findById(id, buildSessionContext(lang));
		BusinessPartnerContentDto entityDto = mapper.getMapper().map(entity, BusinessPartnerContentDto.class);

		try {
			int contentLength = (int) entity.getContent().length();
			byte[] contentAsBytes = entity.getContent().getBytes(1, contentLength);
			entityDto.setContentBase64(Base64.encodeBase64String(contentAsBytes));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.ok()
			.entity(entityDto)
			.build();
	}


	@POST
	public Response create(@PathParam("businessPartnerId") Long businessPartnerId, BusinessPartnerContentDto entityDto, @HeaderParam("lang") Lang lang) {
		Content entity = mapper.getMapper().map(entityDto, Content.class);
		entity.setSourceTable("DICT_BUSINESS_PARTNERS");
		entity.setSourceId(businessPartnerId);

		byte[] contentAsBytes = Base64.decodeBase64(entityDto.getContentBase64());
		try {
			Blob blob = new javax.sql.rowset.serial.SerialBlob(contentAsBytes);
			entity.setContent(blob);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		Content newEntity = service.create(entity, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, BusinessPartnerContentDto.class))
			.build();
	}


	@PUT
	@Path("{id : \\d+}")
	public Response update(@PathParam("businessPartnerId") Long businessPartnerId, @PathParam("id") Long id, BusinessPartnerContentDto entityDto, @HeaderParam("lang") Lang lang) {
		Content entity = mapper.getMapper().map(entityDto, Content.class);
		entity.setSourceTable("DICT_BUSINESS_PARTNERS");
		entity.setSourceId(businessPartnerId);

		byte[] contentAsBytes = Base64.decodeBase64(entityDto.getContentBase64());
		try {
			Blob blob = new javax.sql.rowset.serial.SerialBlob(contentAsBytes);
			entity.setContent(blob);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		Content newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, BusinessPartnerContentDto.class))
			.build();
	}


	@DELETE
	@Path("{id : \\d+}")
	public Response delete(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		service.delete(id, buildSessionContext(lang));
		return Response.noContent()
			.build();
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}

	@Inject
	private ContentService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
