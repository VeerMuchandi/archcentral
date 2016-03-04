package com.pinaka.archcentral.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.pinaka.archcentral.dao.CrudService;
import com.pinaka.archcentral.exception.BadInputDataException;
import com.pinaka.archcentral.model.Archer;
import com.pinaka.archcentral.model.ArcherClass;
import com.pinaka.archcentral.model.Score;
import com.pinaka.archcentral.model.ScoreCard;
import com.pinaka.archcentral.model.ScoreLine;
import com.pinaka.archcentral.model.Tournament;

import static com.pinaka.archcentral.dao.QueryParameter.*;

@Stateless
@Local(ScoreCardManager.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ScoreCardManagerBean implements ScoreCardManager {

	@Inject
	private CrudService cs;
	@Inject
	private ArcherManager archerManager;
	@Inject
	private TournamentManager tournamentManager;
	
	@Inject
	Logger log;

	@Override
	public List<ScoreCard> findAllScoreCards() {
		log.info("In findAllScoreCards");
		List<ScoreCard>scoreCardsList = cs.findByNamedQuery("ScoreCard.findAll");
		return scoreCardsList;	
	}

	@Override
	public ScoreCard addScoreCard(ScoreCard sc) throws BadInputDataException {		
		log.info("In addScoreCard");
		//TODO- add security to check who is adding scorecard
		
		long archerId = sc.getParticipant().getId();
		Archer archer = archerManager.findArcher(archerId);
		sc.setParticipant(archer);
		
		long tournamentId = sc.getTournament().getId();
		Tournament t = tournamentManager.findTournamentById(tournamentId);
		sc.setTournament(t);
		
		if(this.findScoreCards(archerId, tournamentId, sc.getArcherClass()).size() > 0) 
															return this.updateScoreCard(sc);
					
		ScoreCard created = cs.create(sc);

		return created;
	}

	@Override
	public ScoreCard createBlankScoreCard(long tournamentId, long archerId, ArcherClass archerClass) throws BadInputDataException {
		log.info("In createBlankScoreCard");
		
		Tournament t = tournamentManager.findTournamentById(tournamentId);
		Archer a = archerManager.findArcher(archerId);
		if(archerClass==null) archerClass = a.getArcherClass();
		
		if(this.findScoreCards(archerId, tournamentId, archerClass).size()>0) 																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									
											throw new BadInputDataException("ScoreCard already exists");
		
		ScoreCard sc = new ScoreCard(a,t,a.getArcherClass());
		
		//TODO - add logic to set the division based on the age of the archer and the
		//tournament rules
		sc.setDivision("junior");
		
		ScoreCard created = cs.create(sc);
		
		
		return created;
	}

	@Override
	public List<ScoreCard> findScoreCards(long archerId, long tournamentId, ArcherClass archerClass) throws BadInputDataException {
		
		log.info("In findScoreCards by archer, tournament and archerclass");
		
		if (archerId == 0 && tournamentId == 0 && archerClass == null) 
									return this.findAllScoreCards();
		if(tournamentId==0)
				return this.findScoreCardsByArcher(archerId);
		
		if(archerId==0)
				return this.findScoreCardsByTournament(tournamentId);
		
		if (archerClass == null) 	return this.findScoreCards(archerId, tournamentId);
		
		@SuppressWarnings("unchecked")
		List<ScoreCard> sclist = cs.findByNamedQuery("ScoreCard.findUniqueScoreCard", 
				with("archerId", archerId).and("tournamentId",tournamentId).and("archerClass", archerClass).parameters());

		if(sclist.size()>1) throw new BadInputDataException("Duplicate Scorecards exist");
		
		return sclist;
	}
	
	@Override
	public List<ScoreCard> findScoreCards(long archerId, long tournamentId) {
		
		log.info("In findScoreCards by archer, tournament");
		@SuppressWarnings("unchecked")
		List<ScoreCard> scoreCardsList = cs.findByNamedQuery("ScoreCard.findByArcherAndTournament", 
				with("archerId", archerId).and("tournamentId",tournamentId).parameters());
		

		return scoreCardsList;
	}

	@Override
	public ScoreCard updateScoreCard(ScoreCard sc) throws BadInputDataException {
		
		log.info("In updateScoreCard");
		long archerId = sc.getParticipant().getId();
		Archer archer = archerManager.findArcher(archerId);
		sc.setParticipant(archer);
		
		long tournamentId = sc.getTournament().getId();
		Tournament t = tournamentManager.findTournamentById(tournamentId);
		sc.setTournament(t);
		
		ScoreCard updated = cs.update(sc);
		return updated;
	}

	@Override
	public List<ScoreCard> findScoreCardsByArcher(long archerId) {
		
		log.info("In findScoreCards by archer");
		@SuppressWarnings("unchecked")
		List<ScoreCard> scoreCardsList = cs.findByNamedQuery("ScoreCard.findByArcher", 
				with("archerId", archerId).parameters());
		return scoreCardsList;
	}

	@Override
	public List<ScoreCard> findScoreCardsByTournament(long tournamentId) {

		log.info("In findScoreCards by tournament");
		@SuppressWarnings("unchecked")
		List<ScoreCard> scoreCardsList = cs.findByNamedQuery("ScoreCard.findByTournament", 
				with("tournamentId",tournamentId).parameters());
		return scoreCardsList;
	}

}
