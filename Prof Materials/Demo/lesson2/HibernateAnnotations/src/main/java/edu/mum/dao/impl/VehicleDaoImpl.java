package edu.mum.dao.impl;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import edu.mum.dao.VehicleDao;
import edu.mum.domain.Vehicle;


@Repository
public class VehicleDaoImpl extends GenericDaoImpl<Vehicle> implements VehicleDao {

	public VehicleDaoImpl() {
		super.setDaoType(Vehicle.class );
		}

	public Vehicle findByVehicleNo(String number) {

		Transaction tx=null;
		 try {
		     tx = this.getSession().beginTransaction();
				Query query = super.getSession().createQuery("select v from VEHICLE v  where v.vehicleNo =:number");
			   Vehicle vehicle = (Vehicle) query.setParameter("number", number).uniqueResult();
		     tx.commit();
		     
		     return vehicle;
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
		 finally {
			 getSession().close();
		 }			     

	}
}
