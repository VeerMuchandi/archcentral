package com.pinaka.archcentral.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pinaka.archcentral.exception.BadInputDataException;
import com.pinaka.archcentral.model.Archer;

@Path("/")
public interface ArcherManager {
    @GET
    @Path("/archers")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract List<Archer> findAllArchers();
    
    @GET
    @Path("/archers/{id:[0-9][0-9]*}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public abstract Archer findArcher(@PathParam("id") long archerId)
    		throws BadInputDataException;
    
    @POST
	@Path("/archers")
	@Consumes({"text/xml","application/xml","application/json"})
	@Produces({"application/xml","application/json"})
    public abstract Archer addArcher(Archer archer) throws BadInputDataException;
    
    
    @PUT
	@Path("/archers")
	@Consumes({"text/xml","application/xml","application/json"})
	@Produces({"application/xml","application/json"})
    public abstract Archer updateArcher(Archer archer) throws BadInputDataException;

}
