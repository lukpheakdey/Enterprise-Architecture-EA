package edu.mum.service;

import edu.mum.domain.Vehicle;
 
public interface VehicleService {

    public void insert(Vehicle vehicle);

    public void update(Vehicle vehicle);

    public void delete(Vehicle vehicle);

    public Vehicle findByVehicleNo(String vehicleNo);
}
