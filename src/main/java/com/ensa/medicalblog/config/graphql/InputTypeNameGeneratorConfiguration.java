package com.ensa.medicalblog.config.graphql;

import io.leangen.graphql.metadata.messages.MessageBundle;
import io.leangen.graphql.metadata.strategy.type.DefaultTypeInfoGenerator;
import io.leangen.graphql.metadata.strategy.type.TypeInfoGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.AnnotatedType;

@Configuration
public class InputTypeNameGeneratorConfiguration {

    @Bean
    TypeInfoGenerator infoGenerator(){
        return new DefaultTypeInfoGenerator(){
            @Override
            public String generateInputTypeName(AnnotatedType type, MessageBundle messageBundle){
                return this.generateTypeName(type, messageBundle);
            }
        };
    }
}
