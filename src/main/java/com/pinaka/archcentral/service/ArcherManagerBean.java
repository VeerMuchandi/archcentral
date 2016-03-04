package com.pinaka.archcentral.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.pinaka.archcentral.dao.CrudService;
import com.pinaka.archcentral.exception.BadInputDataException;
import com.pinaka.archcentral.model.Archer;
import com.pinaka.archcentral.model.ScoreCard;
import com.pinaka.archcentral.model.Tournament;

@Stateless
@Local(ArcherManager.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ArcherManagerBean implements ArcherManager {
	
	@Inject
	private EntityManager em;
	@Inject
	private CrudService cs;
	
	@Inject
	Logger log;

	@Override
	public List<Archer> findAllArchers() {
		
		@SuppressWarnings("unchecked")
		List<Archer> archersList = cs.findByNamedQuery("Archer.findAll");		
		return archersList;
	}

	@Override
	public Archer findArcher(long archerId) throws BadInputDataException {
		
		log.info("In findArcher");
		
		Archer a = cs.find(archerId, Archer.class);
		return a;
	}

	@Override
	public Archer addArcher(Archer archer) throws BadInputDataException {
		// TODO Implement OpenId and integrate
		
		//TODO verify archer does not exist
			
		Archer a = cs.create(archer);
		return a;
	}

	@Override
	public Archer updateArcher(Archer archer) throws BadInputDataException {

		Archer updated = cs.update(archer);
		return updated;
	}


}
