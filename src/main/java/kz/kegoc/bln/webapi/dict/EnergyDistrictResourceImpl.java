package kz.kegoc.bln.webapi.dict;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyDistrict;
import kz.kegoc.bln.entity.dict.dto.EnergyDistrictDto;
import kz.kegoc.bln.service.dict.EnergyDistrictService;
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
@Path("/dict/dictEnergyDistrict")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergyDistrictResourceImpl {

	@GET 
	public Response getAll(
			@QueryParam("shortName") String shortName,
			@QueryParam("name") String name,
			@QueryParam("energyNodeId") Long energyNodeId,
			@HeaderParam("lang") Lang lang
	) {
		List<EnergyDistrictDto> list = service.find(null, shortName, name, buildSessionContext(lang))
			.stream()
			.filter(it -> energyNodeId == null || energyNodeId.equals(it.getEnergyNode().getId()))
			.map(it-> mapper.getMapper().map(it, EnergyDistrictDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergyDistrictDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @HeaderParam("lang") Lang lang) {
		EnergyDistrict entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.getMapper().map(entity, EnergyDistrictDto.class))
			.build();		
	}
	

	@POST
	public Response create(EnergyDistrictDto entityDto, @HeaderParam("lang") Lang lang) {
		EnergyDistrict entity = mapper.getMapper().map(entityDto, EnergyDistrict.class);
		EnergyDistrict newEntity = service.create(entity, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, EnergyDistrictDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, EnergyDistrictDto entityDto, @HeaderParam("lang") Lang lang ) {
		EnergyDistrict map = mapper.getMapper().map(entityDto, EnergyDistrict.class);
		EnergyDistrict newEntity = service.update(map, buildSessionContext(lang));

		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, EnergyDistrictDto.class))
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
	private EnergyDistrictService service;

	@Inject
	private BeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
