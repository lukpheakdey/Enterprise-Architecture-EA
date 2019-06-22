package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.dao.VehicleDao;
import edu.mum.domain.Vehicle;


public class VehicleServiceImpl implements edu.mum.service.VehicleService {
	
 	@Autowired
	private VehicleDao vehicleDao;

 /*   @Autowired
    public void setDao( GenericDao<MemberDao> memberDao ){
    	this.memberDao = memberDao;
    	memberDao.setDaoType(Member.class);
    }

*/ 	
    public void update( Vehicle vehicle) {  		
   	 vehicleDao.update(vehicle);
	}
 	
    public void insert( Vehicle vehicle) {  		
   	 vehicleDao.save(vehicle);
	}
 	
  	
 	public Vehicle findByVehicleNo(String memberId) {
		return vehicleDao.findByVehicleNo(memberId);
	}

	@Override
	public void delete(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

  

}
