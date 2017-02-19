package coreservlets;

import javax.faces.bean.*;

/** From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate/JPA, and Java programming</a>.
 */

@ManagedBean 
public class BankingBean3 extends BankingBeanBase { 
  @ManagedProperty(value="#{customerSimpleMap2}")
  private CustomerLookupService service;
  
  public void setService(CustomerLookupService service) {
    this.service = service;
  }

  @Override
  public String showBalance() {
    if (!password.equals("secret")) {
      return("wrong-password3");
    }
    customer = service.findCustomer(customerId);
    if (customer == null) {
      return("unknown-customer3");
    } else if (customer.getBalance() < 0) {
      return("negative-balance3");
    } else if (customer.getBalance() < 10000) {
      return("normal-balance3");
    } else {
      return("high-balance3");
    }
  }
}
