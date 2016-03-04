package com.pinaka.archcentral.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pinaka.archcentral.exception.BadInputDataException;
import com.pinaka.archcentral.model.Tournament;

@Path("/")
public interface TournamentManager {
	
    @POST
	@Path("/tournaments")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public abstract Tournament addTournament(Tournament t) throws BadInputDataException;
	
    @GET
    @Path("/tournaments/{id:[0-9][0-9]*}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public abstract Tournament findTournamentById(@PathParam("id") long id) throws BadInputDataException;
    
    @GET
    @Path("/tournaments")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public abstract List<Tournament> findAllTournaments();
	
    @PUT
	@Path("/tournaments")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public abstract Tournament updateTournament(Tournament t) throws BadInputDataException;
    
    @DELETE
	@Path("/tournaments/{id:[0-9][0-9]*}")
	public abstract void deleteTournament(@PathParam("id") long id) throws BadInputDataException;
}
