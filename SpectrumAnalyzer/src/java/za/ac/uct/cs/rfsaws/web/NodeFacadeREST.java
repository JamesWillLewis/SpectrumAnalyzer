/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.web;

import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import za.ac.uct.cs.rfsaws.ejb.AbstractFacade;
import za.ac.uct.cs.rfsaws.entities.SecondaryUserNode;

/**
 *
 * @author James
 */
@Stateless
@Path("/db/node")
public class NodeFacadeREST extends AbstractFacade<SecondaryUserNode> {
    
    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    public NodeFacadeREST() {
        super(SecondaryUserNode.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(SecondaryUserNode entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(SecondaryUserNode entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public SecondaryUserNode find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @GET
    @Path("/test")
    @Produces({"application/xml", "application/json"})
    public String test() {
        return "<html>test ok</html>";
    }
    
    

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<SecondaryUserNode> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<SecondaryUserNode> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
