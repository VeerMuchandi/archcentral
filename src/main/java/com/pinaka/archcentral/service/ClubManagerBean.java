package com.pinaka.archcentral.service;

import static com.pinaka.archcentral.dao.QueryParameter.with;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.pinaka.archcentral.dao.CrudService;
import com.pinaka.archcentral.exception.BadInputDataException;
import com.pinaka.archcentral.model.Archer;
import com.pinaka.archcentral.model.Club;
import com.pinaka.archcentral.model.ClubMembership;
import com.pinaka.archcentral.model.MembershipDuration;
import com.pinaka.archcentral.model.MembershipType;
import com.pinaka.archcentral.model.ScoreCard;
import com.pinaka.archcentral.model.Tournament;

@Stateless
@Local(ClubManager.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ClubManagerBean implements ClubManager {

	@Inject
	private CrudService cs;
	@Inject
	private ArcherManager am;
	@Inject
	Logger log;
	

	@Override
	public List<Club> findAllClubs() {
		log.info("In findAllClubs");
		List<Club>clubsList = cs.findByNamedQuery("Club.findAll");
		return clubsList;	
	}

	@Override
	public Club findClub(long id) throws BadInputDataException {
		log.info("In findClub");
		
		Club a = cs.find(id, Club.class);
		return a;
	}

	@Override
	public List<Club> findClubsByName(String name) {
	
		log.info("In findClubsByName");
		
		if(name==null) return findAllClubs();
		
		@SuppressWarnings("unchecked")
		List<Club> clubsList = cs.findByNamedQuery("Club.findByName", 
							with("name", name).parameters());
		return clubsList;
		
	}

	@Override
	public Club addClub(Club club) throws BadInputDataException {
		return cs.create(club);
	}

	@Override
	public Club updateClub(Club club) throws BadInputDataException {
		return cs.update(club);
	}

	@Override
	public Club disableClub(long id) throws BadInputDataException {
// TODO - id not null
		
		Club club = this.findClub(id);
		club.setEnabled(false);
		Club updated = this.updateClub(club);		
		
		return updated;
	}

	@Override
	public void deleteClub(long id) throws BadInputDataException {
		cs.delete(id, Club.class);
	}

	@Override
	public ClubMembership registerSingleArcher(long clubId, long archerId, MembershipType membershipType,
			MembershipDuration duration, Calendar registrationDate) throws BadInputDataException {
		
		Club club = this.findClub(clubId);
		Archer archer = am.findArcher(archerId);
		if(registrationDate == null) registrationDate = Calendar.getInstance();
		Calendar expiresOn = Calendar.getInstance(); 
		expiresOn.setTime(registrationDate.getTime()); 
		expiresOn.add(duration.scale(), duration.value());
		
		//TODO - check minimum age
		
		ClubMembership cm = new ClubMembership(archer, club, membershipType, duration, expiresOn);

		return cs.create(cm);
	}

	@Override
	public List<ClubMembership> registerFamily(long clubId, long mainMemberId,
			long[] memberIds, MembershipType membershipType, MembershipDuration duration,
			Calendar registrationDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Club enableClub(long id) throws BadInputDataException {
		// TODO - id not null
		
			Club club = this.findClub(id);
			club.setEnabled(true);
			Club updated = this.updateClub(club);		
			
			return updated;
	}

	@Override
	public List<ClubMembership> findClubMembershipsByClub(long clubId) {

		log.info("In findClubsMembershipsByClub");
		
		@SuppressWarnings("unchecked")
		List<ClubMembership> clubMembershipsList = cs.findByNamedQuery("ClubMembership.findByClub", 
							with("clubId", clubId).parameters());
		return clubMembershipsList;
	}

	@Override
	public List<ClubMembership> findAllClubMemberships() {

		log.info("In findAllClubsMemberships");
		@SuppressWarnings("unchecked")
		List<ClubMembership> clubMembershipsList = cs.findByNamedQuery("ClubMembership.findAll"); 
		return clubMembershipsList;
	}

	@Override
	public List<ClubMembership> findClubMembershipsByArcherAndClub(long clubId,
			long archerId) {
		log.info("In findClubsMembershipsByArcherAndClub");
		
		if(clubId==0 && archerId ==0) return findAllClubMemberships();
		
		if(clubId==0)return findClubMembershipsByArcher(archerId);
		
		if(archerId==0)return findClubMembershipsByClub(clubId);
		
		@SuppressWarnings("unchecked")
		List<ClubMembership> clubMembershipsList = cs.findByNamedQuery("ClubMembership.findByArcherAndClub", 
							with("clubId", clubId).and("archerId",archerId).parameters());
		return clubMembershipsList;
		
	}

	@Override
	public List<ClubMembership> findClubMembershipsByArcher(long archerId) {
		
		log.info("In findClubsMembershipsByArcher");
		@SuppressWarnings("unchecked")
		List<ClubMembership> clubMembershipsList = cs.findByNamedQuery("ClubMembership.findByArcher",
								with("archerId",archerId).parameters()); 
		return clubMembershipsList;
	}
	
	

}
