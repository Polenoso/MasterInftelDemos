/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

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
import model.Gastos;

/**
 *
 * @author aitorpagan
 */
@Stateless
@Path("model.gastos")
public class GastosFacadeREST extends AbstractFacade<Gastos> {
    @PersistenceContext(unitName = "saveAppWebServicePU")
    private EntityManager em;

    public GastosFacadeREST() {
        super(Gastos.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Gastos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Long id, Gastos entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Gastos find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Gastos> findAll() {
        return super.findAll();
    }
    
     @GET
    @Path("byuser/{iduser}")
    @Produces({"application/json"})
    public List<Gastos> findbyuser(@PathParam("iduser") Long iduser){    
       return (List<Gastos>) em.createNamedQuery("Gastos.findByIdUsuario").setParameter("idUserGasto", iduser).getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Gastos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
