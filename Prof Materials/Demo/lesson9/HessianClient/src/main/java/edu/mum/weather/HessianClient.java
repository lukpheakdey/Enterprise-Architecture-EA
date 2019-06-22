package edu.mum.weather;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HessianClient {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("edu.mum.weather.config");

        WeatherServiceClient client = context.getBean(WeatherServiceClient.class);
       TemperatureInfo temperature = client.getTodayTemperature("Houston");
        System.out.println("Min temperature : " + temperature.getMin());
        System.out.println("Max temperature : " + temperature.getMax());
        System.out.println("Average temperature : " + temperature.getAverage());
    }
}
