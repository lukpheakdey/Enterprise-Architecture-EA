package edu.mum.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.dao.VehicleDao;
import edu.mum.domain.Vehicle;
import edu.mum.service.VehicleService;

 
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

        VehicleService vehicleService = (VehicleService) context.getBean("vehicleServiceImpl");

        
        
        Vehicle vehicle = new Vehicle("TEM0003", "Red", 4, 4);
        vehicleService.insert(vehicle);

        vehicle = vehicleService.findByVehicleNo("TEM0003");
        System.out.println();
        System.out.println("        *********  VEHICLE **********");

        System.out.println("Vehicle No: " + vehicle.getVehicleNo());
        System.out.println("Color: " + vehicle.getColor());
        System.out.println("Wheel: " + vehicle.getWheel());
        System.out.println("Seat: " + vehicle.getSeat());
    }

}
