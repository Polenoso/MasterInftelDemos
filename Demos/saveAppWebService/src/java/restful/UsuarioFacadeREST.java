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
import model.Usuario;

/**
 *
 * @author aitorpagan
 */
@Stateless
@Path("model.usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "saveAppWebServicePU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Usuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Long id, Usuario entity) {
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
    public Usuario find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @GET
    @Path("login/{nickname}/{password}")
    @Produces({"application/json"})
    public Usuario login(@PathParam("nickname") String nickname, @PathParam("password") String password){
        
        if(!em.createNamedQuery("Usuario.findByNickname").setParameter("nickname", nickname).getResultList().isEmpty()){
            Usuario user = (Usuario) em.createNamedQuery("Usuario.findByNickname").setParameter("nickname", nickname).getSingleResult();
            if(user.getPassword().equals(password)){
                return user;
            }else{
                return new Usuario(-1L,nickname,"null");
            }
        }else{
            return new Usuario(-2L,"null","null");
        }
        
    }
    
    @GET
    @Path("bynickname/{nickname}")
    @Produces({"application/json"})
    public Usuario findbynickname(@PathParam("nickname") String nickname){
        
        return (Usuario) em.createNamedQuery("Usuario.findByNickname").setParameter("nickname", nickname).getSingleResult();
    }
            

    @GET
    @Override
    @Produces({"application/json"})
    public List<Usuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
