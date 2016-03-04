package com.pinaka.archcentral.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.pinaka.archcentral.dao.CrudService;
import com.pinaka.archcentral.exception.BadInputDataException;
import com.pinaka.archcentral.model.Archer;
import com.pinaka.archcentral.model.Tournament;

@Stateless
@Local(TournamentManager.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TournamentManagerBean implements TournamentManager {
	@Inject
	private EntityManager em;
	@Inject
	Logger log;
	@Inject
	private CrudService cs;
	
	@Override
	public Tournament addTournament(Tournament t) throws BadInputDataException {

		//TODO verify tournament does not exist
		Tournament added = cs.create(t);
		return added;
	}

	@Override
	public Tournament findTournamentById(long id) throws BadInputDataException {
		log.info("In findTournamentById");
		//TODO - id should not be null
		Tournament found = cs.find(id, Tournament.class);
		
		return found;
	}

	@Override
	public Tournament updateTournament(Tournament t) throws BadInputDataException {
		Tournament updated = cs.update(t);
		return updated;
	}

	@Override
	public void deleteTournament(long id) throws BadInputDataException {
		cs.delete(id, Tournament.class);
	}

	@Override
	public List<Tournament> findAllTournaments() {
		
		@SuppressWarnings("unchecked")
		List<Tournament> tlist = cs.findByNamedQuery("Tournament.findAll");
		
		return tlist;
	}

}
