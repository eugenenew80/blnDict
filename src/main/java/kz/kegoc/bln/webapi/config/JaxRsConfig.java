package kz.kegoc.bln.webapi.config;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.ejb.jackson.ObjectMapperContextResolver;
import kz.kegoc.bln.webapi.dict.*;
import kz.kegoc.bln.webapi.exception.mapper.*;
import kz.kegoc.bln.webapi.filters.BasicAuthFilter;

@ApplicationPath("/webapi")
public class JaxRsConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<Class<?>>();

		resources.add(UnitResourceImpl.class);
		resources.add(DataSourceResourceImpl.class);
		resources.add(MeterTypeResourceImpl.class);
		resources.add(CurrentTransTypeResourceImpl.class);
		resources.add(VoltageTransTypeResourceImpl.class);
		resources.add(EnergyZoneResourceImpl.class);
		resources.add(RegionResourceImpl.class);
		resources.add(EnergyNodeResourceImpl.class);
		resources.add(OrganizationResourceImpl.class);
		resources.add(MeterResourceImpl.class);
		resources.add(EnergySourceTypeResourceImpl.class);
		resources.add(SubstationResourceImpl.class);
		resources.add(EnergySourceResourceImpl.class);
		resources.add(MeteringTypeResourceImpl.class);
		resources.add(SubstationTypeResourceImpl.class);
		resources.add(AccountingTypeResourceImpl.class);
		resources.add(MeteringPointTypeResourceImpl.class);	
		resources.add(MeteringPointResourceImpl.class);
		resources.add(MeteringPointMeterResourceImpl.class);
		resources.add(CountryResourceImpl.class);
		resources.add(BusinessPartnerResourceImpl.class);
		resources.add(PowerLineTypeResourceImpl.class);
		resources.add(PowerLineResourceImpl.class);
		resources.add(PowerTransformerResourceImpl.class);
		resources.add(ReactorResourceImpl.class);
		resources.add(VoltageClassResourceImpl.class);
		resources.add(BankResourceImpl.class);

		resources.add(BasicAuthFilter.class);
		resources.add(RepositryNotFoundExceptionMapperImpl.class);
		resources.add(EntityNotFoundExceptionMapperImpl.class);
		resources.add(DuplicateEntityExceptionMapperImpl.class);
		resources.add(ValidationExceptionMapperImpl.class);
		resources.add(InvalidArgumentExceptionMapperImpl.class);
		resources.add(EjbExceptionMapperImpl.class);
		resources.add(WebApplicationExceptionMapperImpl.class);
        resources.add(DefaultExceptionMapperImpl.class);

        resources.add(ObjectMapperContextResolver.class);

		return resources;
	}	
}
