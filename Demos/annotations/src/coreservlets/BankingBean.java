package coreservlets;

import javax.faces.bean.*;

/** From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF 1.x, JSF 2.0, Ajax, 
 *  GWT, Spring, Hibernate/JPA, and Java programming</a>.
 */

@ManagedBean 
public class BankingBean extends BankingBeanBase {  
  @Override
  public String showBalance() {
    if (!password.equals("secret")) {
      return("wrong-password");
    }
    CustomerLookupService service =
      new CustomerSimpleMap();
    customer = service.findCustomer(customerId);
    if (customer == null) {
      return("unknown-customer");
    } else if (customer.getBalance() < 0) {
      return("negative-balance");
    } else if (customer.getBalance() < 10000) {
      return("normal-balance");
    } else {
      return("high-balance");
    }
  }
}
