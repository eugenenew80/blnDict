package kz.kegoc.bln.webapi.dict;

import java.util.*;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.translator.Translator;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.entity.dict.dto.MeteringPointDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.dict.MeteringPointService;

import static org.apache.commons.lang3.StringUtils.*;

@Stateless
@Path("/dict/dictMeteringPoint")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MeteringPointResourceImpl {

	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		Query query = QueryImpl.builder()
			.setParameter("code", isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)
			.setParameter("name", isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)
			.setOrderBy("t.id")
			.build();		
		
		List<MeteringPointDto> list = service.find(query)
			.stream()
			.map(it -> translator.translate(it, userLang))
			.map( it-> mapper.map(it, MeteringPointDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<MeteringPointDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		MeteringPoint entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), MeteringPointDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		MeteringPoint entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), MeteringPointDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name, @QueryParam("lang") Lang lang) {
		final Lang userLang = (lang!=null ? lang : defLang);

		MeteringPoint entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(translator.translate(entity, userLang), MeteringPointDto.class))
			.build();
	}

	
	@POST
	public Response create(MeteringPointDto entityDto) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		MeteringPoint newEntity = service.create(mapper.map(entityDto, MeteringPoint.class));
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), MeteringPointDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MeteringPointDto entityDto ) {
		if (entityDto.getLang()==null)
			entityDto.setLang(defLang);

		MeteringPoint newEntity = service.update(mapper.map(entityDto, MeteringPoint.class));
		return Response.ok()
			.entity(mapper.map(translator.translate(newEntity, entityDto.getLang()), MeteringPointDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Path("/{meteringPointId : \\d+}/dictMeteringPointMeter")
	public MeteringPointMeterResourceImpl getMeterResource() {
		return meterResourceImpl;
	}

	@Path("/{meteringPointId : \\d+}/dictMeteringPointCurrentTrans")
	public MeteringPointCurrentTransResourceImpl getCurrentTransResource() {
		return currentTransResource;
	}

	@Path("/{meteringPointId : \\d+}/dictMeteringPointVoltageTrans")
	public MeteringPointVoltageTransResourceImpl getVoltageTransResource() {
		return voltageTransResource;
	}


	@Inject
	private MeteringPointService service;

	@Inject
	private MeteringPointMeterResourceImpl meterResourceImpl;

	@Inject
	private MeteringPointCurrentTransResourceImpl currentTransResource;

	@Inject
	private MeteringPointVoltageTransResourceImpl voltageTransResource;

	@Inject
	private DozerBeanMapper mapper;

	@Inject
	private Translator<MeteringPoint> translator;

	@Inject
	private Lang defLang;
}
