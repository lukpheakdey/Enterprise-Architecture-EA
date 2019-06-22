package edu.mum.dao;

import edu.mum.domain.Member;
import edu.mum.domain.Vehicle;


public interface VehicleDao extends GenericDao<Vehicle> {
 
    public Vehicle findByVehicleNo(String vehicleNo);


}
