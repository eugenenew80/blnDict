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
                "mapper/MappingConfig.xml",
                "mapper/dict/def/AccountingTypeDto.xml",
                "mapper/dict/def/CompanyDto.xml",
                "mapper/dict/def/CurrentTransDto.xml",
                "mapper/dict/def/CurrentTransTypeDto.xml",
                "mapper/dict/def/DataSourceDto.xml",
                "mapper/dict/def/EnergyNodeDto.xml",
                "mapper/dict/def/EnergySourceCompanyDto.xml",
                "mapper/dict/def/EnergySourceMeteringPointDto.xml",
                "mapper/dict/def/EnergySourceDto.xml",
                "mapper/dict/def/EnergySourceTypeDto.xml",
                "mapper/dict/def/EnergyZoneDto.xml",
                "mapper/dict/def/MeteringPointDto.xml",
                "mapper/dict/def/MeteringPointMeterDto.xml",
                "mapper/dict/def/MeteringPointTypeDto.xml",
                "mapper/dict/def/MeteringTypeDto.xml",
                "mapper/dict/def/MeterDto.xml",
                "mapper/dict/def/MeterTypeDto.xml",
                "mapper/dict/def/RegionDto.xml",
                "mapper/dict/def/SubstationCompanyDto.xml",
                "mapper/dict/def/SubstationMeteringPointDto.xml",
                "mapper/dict/def/SubstationDto.xml",
                "mapper/dict/def/SubstationTypeDto.xml",
                "mapper/dict/def/UnitDto.xml",
                "mapper/dict/def/VoltageTransDto.xml",
                "mapper/dict/def/VoltageTransTypeDto.xml",
                "mapper/dict/def/CountryDto.xml",
                "mapper/dict/def/BusinessPartnerDto.xml",
                "mapper/dict/def/BankDto.xml",
                "mapper/dict/def/BankAccountDto.xml",
                "mapper/dict/def/PhoneNumberDto.xml",
                "mapper/dict/def/PostAddressDto.xml",
                "mapper/dict/def/VoltageClassDto.xml",
                "mapper/dict/list/MeterDto.xml"

        ));
        return mapper;
    }
}
