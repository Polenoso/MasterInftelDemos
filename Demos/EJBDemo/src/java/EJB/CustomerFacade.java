/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import JPA.Customer;
import JPA.PurchaseOrder;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aitorpagan
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {
    @PersistenceContext(unitName = "EJBDemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
    public BigDecimal getOderTotal(Customer customer){
        BigDecimal total1 = new BigDecimal(BigInteger.ZERO);
        BigDecimal total2 = new BigDecimal(BigInteger.ZERO);
        int total;
        for(PurchaseOrder po : customer.getPurchaseOrderCollection()){
            total = po.getQuantity();
            total1 = po.getProductId().getPurchaseCost();
            total1 = total1.multiply(BigDecimal.valueOf(total));
            total2 = total2.add(total1);
        }
        return total2;
    }
}
