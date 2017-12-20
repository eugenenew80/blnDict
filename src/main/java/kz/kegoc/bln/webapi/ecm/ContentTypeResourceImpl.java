package kz.kegoc.bln.webapi.ecm;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.ecm.ContentType;
import kz.kegoc.bln.entity.ecm.dto.ContentTypeDto;
import kz.kegoc.bln.service.ecm.ContentTypeService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/dict/ecmContentType")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ContentTypeResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @HeaderParam("lang") Lang lang) {
		List<ContentTypeDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map( it-> mapper.getMapper().map(it, ContentTypeDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<ContentTypeDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		ContentType entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, ContentTypeDto.class))
			.build();		
	}


	@POST
	public Response create(ContentTypeDto entityDto, @HeaderParam("lang") Lang lang) {
		ContentType entity = mapper.getMapper().map(entityDto, ContentType.class);
		ContentType newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, ContentTypeDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, ContentTypeDto entityDto, @HeaderParam("lang") Lang lang) {
		ContentType entity = mapper.getMapper().map(entityDto, ContentType.class);
		ContentType newEntity = service.update(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, ContentTypeDto.class))
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
	private ContentTypeService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
