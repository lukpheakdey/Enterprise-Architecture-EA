package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.dao.VehicleDao;
import edu.mum.domain.Vehicle;

@Service
public class VehicleServiceImpl implements edu.mum.service.VehicleService {
	
 	@Autowired
	private VehicleDao vehicleDao;

    public void update( Vehicle member) {  		
   	 vehicleDao.update(member);
	}
 	
    public void insert( Vehicle member) {  		
   	 vehicleDao.save(member);
	}
 	
  	
 	public Vehicle findByVehicleNo(String memberId) {
		return vehicleDao.findByVehicleNo(memberId);
	}

	@Override
	public void delete(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

  

}
