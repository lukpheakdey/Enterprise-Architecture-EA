package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.VehicleDao;
import edu.mum.domain.Vehicle;

@Service
@Transactional 
public class VehicleServiceImpl implements edu.mum.service.VehicleService {
	
 	@Autowired
	private VehicleDao vehicleDao;

    public void update( Vehicle vehicle) {  		
   	 vehicleDao.update(vehicle);
	}
 	
    public void insert( Vehicle vehicle) {  		
   	 vehicleDao.save(vehicle);
	}
 	
  	
 	public Vehicle findByVehicleNo(String vehicleId) {
		return vehicleDao.findByVehicleNo(vehicleId);
	}

	@Override
	public void delete(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

  

}
