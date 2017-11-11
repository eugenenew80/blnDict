package kz.kegoc.bln.ejb.cdi;

import org.dozer.DozerBeanMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Arrays;

@ApplicationScoped
public class Producer {
	DozerBeanMapper mapper = null;
	
    @Produces
    public DozerBeanMapper dozerBeanMapper() {
    	if (mapper!=null)
    		return mapper;
    	
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(
                "dozer/MappingConfig.xml",
                "dozer/dict/def/AccountingTypeDto.xml",
                "dozer/dict/def/CompanyDto.xml",
                "dozer/dict/def/CurrentTransDto.xml",
                "dozer/dict/def/CurrentTransTypeDto.xml",
                "dozer/dict/def/DataSourceDto.xml",
                "dozer/dict/def/EnergyNodeDto.xml",
                "dozer/dict/def/EnergySourceCompanyDto.xml",
                "dozer/dict/def/EnergySourceMeteringPointDto.xml",
                "dozer/dict/def/EnergySourceDto.xml",
                "dozer/dict/def/EnergySourceTypeDto.xml",
                "dozer/dict/def/EnergyZoneDto.xml",
                "dozer/dict/def/MeteringPointDto.xml",
                "dozer/dict/def/MeteringPointMeterDto.xml",
                "dozer/dict/def/MeteringPointTypeDto.xml",
                "dozer/dict/def/MeteringTypeDto.xml",
                "dozer/dict/def/MeterDto.xml",
                "dozer/dict/def/MeterTypeDto.xml",
                "dozer/dict/def/RegionDto.xml",
                "dozer/dict/def/SubstationCompanyDto.xml",
                "dozer/dict/def/SubstationMeteringPointDto.xml",
                "dozer/dict/def/SubstationDto.xml",
                "dozer/dict/def/SubstationTypeDto.xml",
                "dozer/dict/def/UnitDto.xml",
                "dozer/dict/def/VoltageTransDto.xml",
                "dozer/dict/def/VoltageTransTypeDto.xml",
                "dozer/dict/def/CountryDto.xml",

                "dozer/dict/list/MeterDto.xml"
        ));
        return mapper;
    }
}
