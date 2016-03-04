package com.pinaka.archcentral.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.pinaka.archcentral.exception.BadInputDataException;
import com.pinaka.archcentral.model.Club;
import com.pinaka.archcentral.model.ClubMembership;
import com.pinaka.archcentral.model.MembershipDuration;
import com.pinaka.archcentral.model.MembershipType;
import com.pinaka.archcentral.util.CalendarFormat;

@Path("/")
public interface ClubManager {
	
	@RolesAllowed({"admin"})
	List<Club> findAllClubs();
		
    @GET
    @Path("/clubs/{id:[0-9][0-9]*}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Club findClub(@PathParam("id") long id) throws BadInputDataException;
	
	@GET
	@Path("/clubs")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Club> findClubsByName(@QueryParam("name") String name);
	
	@POST
	@Path("/clubs")
	@Consumes({"application/json","text/xml","application/xml"})
	@Produces({"application/json","application/xml"})
	Club addClub(Club club) throws BadInputDataException;
	
	@PUT
	@Path("/clubs")
	@Consumes({"application/json","text/xml","application/xml"})
	@Produces({"application/json","application/xml"})
	Club updateClub(Club club) throws BadInputDataException;
	
	@PUT
	@Path("/clubs/{id:[0-9][0-9]*}/disable")
	@Consumes({"application/json","text/xml","application/xml"})
	@Produces({"application/json","application/xml"})
	Club disableClub(@PathParam("id") long id) throws BadInputDataException;
	
	@PUT
	@Path("/clubs/{id:[0-9][0-9]*}/enable")
	@Consumes({"application/json","text/xml","application/xml"})
	@Produces({"application/json","application/xml"})
	Club enableClub(@PathParam("id") long id) throws BadInputDataException;
	
	@DELETE
	@Path("/clubs/{id:[0-9][0-9]*}")
	void deleteClub(@PathParam("id") long id) throws BadInputDataException;
	
	@POST
	@Path("/club-memberships")
	@Consumes({"application/json","text/xml","application/xml"})
	@Produces({"application/json","application/xml"})	
	ClubMembership registerSingleArcher(@QueryParam ("club-id") long clubId, 
										@QueryParam ("archer-id") long archerId, 
										@QueryParam ("membership-type") MembershipType membershipType, 
										@QueryParam ("membership-duration") MembershipDuration duration, 
										@QueryParam ("registration-date") @CalendarFormat("MM-dd-yyyy")  Calendar registrationDate) throws BadInputDataException;
	
	List<ClubMembership> registerFamily(long clubId, long mainMemberId, long[] memberIds, MembershipType membershipType, MembershipDuration duration, Calendar registrationDate);
	
	@GET
	@Path("/club-memberships")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<ClubMembership> findClubMembershipsByArcherAndClub(@QueryParam ("club-id") long clubId, @QueryParam ("archer-id") long archerId);
	
	List<ClubMembership> findClubMembershipsByArcher(long archerId);
	
	List<ClubMembership> findClubMembershipsByClub(long clubId);
	
	List<ClubMembership> findAllClubMemberships();
	

}
