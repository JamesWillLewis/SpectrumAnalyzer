/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.services;

import java.util.List;
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
import za.ac.uct.cs.rfsaws.entities.Node;

/**
 *
 * @author James
 */
@Stateless
@Path("/db/node")
public class NodeFacadeREST extends AbstractFacade<Node> {
    
    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;

    public NodeFacadeREST() {
        super(Node.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Node entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Node entity) {
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
    public Node find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Node> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Node> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
