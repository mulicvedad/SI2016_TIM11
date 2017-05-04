package com.timxyz;

import com.github.jmnarloch.spring.boot.modelmapper.ModelMapperConfigurer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper implements ModelMapperConfigurer {
    public void configure(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);
    }

}