package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.entity.dict.dto.MeteringPointDto;
import kz.kegoc.bln.service.dict.MeteringPointService;

@Stateless
@Path("/dict/dictMeteringPoint")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringPointResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		List<MeteringPointDto> list = service.findAll(buildSessionContext(lang))
			.stream()
			.map(it-> mapper.map(it, MeteringPointDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<MeteringPointDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		MeteringPoint entity = service.findById(id, buildSessionContext(lang));
		return Response.ok()
			.entity(mapper.map(entity, MeteringPointDto.class))
			.build();		
	}
	

	@POST
	public Response create(MeteringPointDto entityDto) {
		MeteringPoint entity = mapper.map(entityDto, MeteringPoint.class);
		MeteringPoint newEntity = service.create(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeteringPointDto entityDto ) {
		MeteringPoint entity = mapper.map(entityDto, MeteringPoint.class);
		MeteringPoint newEntity = service.update(entity, buildSessionContext(entityDto.getLang()));

		return Response.ok()
			.entity(mapper.map(newEntity, MeteringPointDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id, buildSessionContext(null));
		return Response.noContent()
			.build();
	}
	

	@Path("/{meteringPointId : \\d+}/dictMeteringPointMeter")
	public MeteringPointMeterResourceImpl getMeterResource() {
		return meterResource;
	}

	@Path("/{meteringPointId : \\d+}/dictMeteringPointCurrentTrans")
	public MeteringPointCurrentTransResourceImpl getCurrentTransResource() {
		return currentTransResource;
	}

	@Path("/{meteringPointId : \\d+}/dictMeteringPointVoltageTrans")
	public MeteringPointVoltageTransResourceImpl getVoltageTransResource() {
		return voltageTransResource;
	}

	@Path("/{meteringPointId : \\d+}/dictMeteringPointCharacteristic")
	public MeteringPointCharacteristicResourceImpl getCharacteristicResource() {
		return characteristicResource;
	}


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}


	@Inject
	private MeteringPointService service;

	@Inject
	private MeteringPointMeterResourceImpl meterResource;

	@Inject
	private MeteringPointCurrentTransResourceImpl currentTransResource;

	@Inject
	private MeteringPointVoltageTransResourceImpl voltageTransResource;

	@Inject
	private MeteringPointCharacteristicResourceImpl characteristicResource;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Lang defLang;

	@Context
	private SecurityContext securityContext;
}
