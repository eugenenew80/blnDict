package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.EnergySource;
import kz.kegoc.bln.entity.dict.dto.EnergySourceDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.EnergySourceService;
import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictEnergySource")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EnergySourceResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<EnergySourceDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, EnergySourceDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<EnergySourceDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);
		service.setLang(userLang);

		EnergySource entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, EnergySourceDto.class))
			.build();		
	}
	

	@POST
	public Response create(EnergySourceDto entityDto) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		EnergySource newEntity = service.create(mapper.map(entityDto, EnergySource.class));
		return Response.ok()
			.entity(mapper.map(newEntity, EnergySourceDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, EnergySourceDto entityDto ) {
		final Lang userLang = (entityDto.getLang()!=null ? entityDto.getLang() : defLang);
		service.setLang(userLang);

		EnergySource newEntity = service.update(mapper.map(entityDto, EnergySource.class));
		return Response.ok()
			.entity(mapper.map(newEntity, EnergySourceDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Path("/{energySourceId : \\d+}/dictEnergySourceCompany")
	public EnergySourceBusinessPartnerResourceImpl getModules(@PathParam("energySourceId") Long id) {
		return businessPartnerResource;
	}


	@Path("/{energySourceId : \\d+}/dictEnergySourceMeteringPoint")
	public EnergySourceMeteringPointResourceImpl getMeteringPoints(@PathParam("energySourceId") Long id) {
		return energySourceMeteringPointResource;
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}


	@Inject
	private EnergySourceService service;

	@Inject
	private EnergySourceBusinessPartnerResourceImpl businessPartnerResource;

	@Inject
	private EnergySourceMeteringPointResourceImpl energySourceMeteringPointResource;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
