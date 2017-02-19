package coreservlets;

import javax.faces.bean.*;

@ManagedBean   
@ApplicationScoped // No state, so application scope prevents unnecessary instantiation
public class SimpleBean {
  public String doNavigation() {
    String[] results = 
      { "page1", "page2", "page3" };
    return(RandomUtils.randomElement(results));
  }
}
