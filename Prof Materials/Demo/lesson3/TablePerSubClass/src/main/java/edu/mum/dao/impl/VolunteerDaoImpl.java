package edu.mum.dao.impl;

 

import org.springframework.stereotype.Repository;

import edu.mum.dao.VolunteerDao;
import edu.mum.domain.Volunteer;


@SuppressWarnings("unchecked")
@Repository
public class VolunteerDaoImpl extends GenericDaoImpl<Volunteer> implements VolunteerDao {

	public VolunteerDaoImpl() {
		super.setDaoType(Volunteer.class );
		}

 
 }