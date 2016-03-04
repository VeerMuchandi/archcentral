package com.pinaka.archcentral.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.pinaka.archcentral.exception.BadInputDataException;
import com.pinaka.archcentral.model.ArcherClass;
import com.pinaka.archcentral.model.ScoreCard;

@Path("/")
public interface ScoreCardManager {
		
	@RolesAllowed({"admin"})
	public abstract List<ScoreCard> findAllScoreCards();
	
	@GET
	@Path("/scorecards")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public abstract List<ScoreCard> findScoreCards(@QueryParam("archer-id") long archerId, 
										    @QueryParam("tournament-id") long tournamentId,
										    @QueryParam("archer-class") ArcherClass archerClass) throws BadInputDataException;
	
	public abstract List<ScoreCard> findScoreCards(@QueryParam("archer-id") long archerId, 
										    		@QueryParam("tournament-id") long tournamentId);
	
	public abstract List<ScoreCard> findScoreCardsByArcher(long archerId);
	
	public abstract List<ScoreCard> findScoreCardsByTournament(long tournamentId);
	
	@POST
	@Path("/scorecards")
	@Consumes({"application/json","text/xml","application/xml"})
	@Produces({"application/json","application/xml"})
	public abstract ScoreCard addScoreCard(ScoreCard sc) throws BadInputDataException;
	    
	@POST
	@Path("/blank-scorecards")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public abstract ScoreCard createBlankScoreCard(@QueryParam("tournament-id") long tournamentId, 
												   @QueryParam("archer-id") long archerId,
												   @QueryParam("archer-class") ArcherClass archerClass) throws BadInputDataException;
	@PUT
	@Path("/scorecards")
	@Consumes({"application/json","text/xml","application/xml"})
	@Produces({"application/json","application/xml"})
	public abstract ScoreCard updateScoreCard(ScoreCard sc) throws BadInputDataException;
}
