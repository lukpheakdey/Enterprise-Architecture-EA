package edu.mum.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.dao.VehicleDao;
import edu.mum.domain.Vehicle;
import edu.mum.service.VehicleService;

 
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

 //       VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDaoImpl");
       VehicleService vehicleDao = (VehicleService) context.getBean("vehicleServiceImpl");

        
        
        Vehicle vehicle = new Vehicle("TEM0002", "Red", 4, 4);
        vehicleDao.insert(vehicle);

        vehicle = vehicleDao.findByVehicleNo("TEM0002");
        System.out.println();
        System.out.println("        *********  VEHICLE **********");
       System.out.println("Vehicle No: " + vehicle.getVehicleNo());
        System.out.println("Color: " + vehicle.getColor());
        System.out.println("Wheel: " + vehicle.getWheel());
        System.out.println("Seat: " + vehicle.getSeat());
    }

}
