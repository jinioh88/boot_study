package com.bootstudy.study.common.validation.config;

import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class BeanApplicationConfig {
    @Bean
    public LocalValidatorFactoryBean beanValidator(MessageSource messageSource) {
        val bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    public ModelMapper modelMapper() {
        val modelMapper = new ModelMapper();
        val configuration = modelMapper.getConfiguration();

        configuration.setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
