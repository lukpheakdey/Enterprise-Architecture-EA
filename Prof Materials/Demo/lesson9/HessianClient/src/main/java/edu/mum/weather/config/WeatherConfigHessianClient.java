package edu.mum.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import edu.mum.weather.WeatherService;
import edu.mum.weather.WeatherServiceClient;

@Configuration
public class WeatherConfigHessianClient {
    
    @Bean
    public HessianProxyFactoryBean weatherService() {
	HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
	factory.setServiceUrl("http://localhost:8080/HessianServer/weather");
	factory.setServiceInterface(WeatherService.class);
	return factory;
    }

    @Bean
    public WeatherServiceClient weatherClient() { 
	WeatherServiceClient wServiceClient = new WeatherServiceClient();
	return wServiceClient;
    }
    
}
