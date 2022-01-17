package nl.novi.autogarage.converter;

import nl.novi.autogarage.model.AppointmentStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, AppointmentStatus> {

    @Override
    public AppointmentStatus convert(String source) {

            return AppointmentStatus.valueOf(source.toUpperCase());

    }
}