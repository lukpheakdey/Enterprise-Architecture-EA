package edu.mum.dao;

import java.util.List;

import edu.mum.domain.Address;

public interface AddressDao extends GenericDao<Address> {
      
	public List<Address> findAddressBySubSelect();
}
