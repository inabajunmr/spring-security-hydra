package work.inabajun.springsecurityhydra

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfiguration() {

    @Bean
    fun restTemplate(): RestTemplate {
        var mapper = ObjectMapper()
        mapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        return RestTemplateBuilder()
                .messageConverters(MappingJackson2HttpMessageConverter(mapper)).build()
    }

}
