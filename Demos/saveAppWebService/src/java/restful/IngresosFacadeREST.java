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
import model.Ingresos;

/**
 *
 * @author aitorpagan
 */
@Stateless
@Path("model.ingresos")
public class IngresosFacadeREST extends AbstractFacade<Ingresos> {
    @PersistenceContext(unitName = "saveAppWebServicePU")
    private EntityManager em;

    public IngresosFacadeREST() {
        super(Ingresos.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Ingresos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Long id, Ingresos entity) {
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
    public Ingresos find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @GET
    @Path("byuser/{iduser}")
    @Produces({"application/json"})
    public List<Ingresos> findbyuser(@PathParam("iduser") Long iduser){    
       return (List<Ingresos>) em.createNamedQuery("Ingresos.findByIdUsuario").setParameter("idUserIngreso", iduser).getResultList();
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Ingresos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Ingresos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
