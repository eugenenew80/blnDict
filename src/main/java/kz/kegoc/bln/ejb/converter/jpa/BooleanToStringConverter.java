package kz.kegoc.bln.ejb.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return (value != null && value) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return (value!=null ? "Y".equals(value) : false);
    }
}
