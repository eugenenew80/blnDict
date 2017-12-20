package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageClass;
import kz.kegoc.bln.entity.dict.dto.VoltageClassDto;
import kz.kegoc.bln.service.dict.VoltageClassService;
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
@Path("/dict/dictVoltageClass")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class VoltageClassResourceImpl {

	@GET 
	public Response getAll(
		@QueryParam("name") String name,
		@QueryParam("lang") Lang lang
	) {
		List<VoltageClassDto> list = service.find(null, null, name, buildSessionContext(lang))
			.stream()
			.map( it-> mapper.getMapper().map(it, VoltageClassDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<VoltageClassDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		VoltageClass entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, VoltageClassDto.class))
			.build();		
	}
	

	@POST
	public Response create(VoltageClassDto entityDto) {
		VoltageClass entity = mapper.getMapper().map(entityDto, VoltageClass.class);
		VoltageClass newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, VoltageClassDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, VoltageClassDto entityDto ) {
		VoltageClass entity = mapper.getMapper().map(entityDto, VoltageClass.class);
		VoltageClass newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, VoltageClassDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id, buildSessionContext(null));
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
	private VoltageClassService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
