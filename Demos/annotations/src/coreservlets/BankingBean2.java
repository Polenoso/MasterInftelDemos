package coreservlets;

import java.io.*;
import javax.faces.bean.*;

@ManagedBean
@SessionScoped
public class BankingBean2 extends BankingBean 
                          implements Serializable {
  @Override
  public String showBalance() {
    String origResult = super.showBalance();
    return(origResult + "2?faces-redirect=true");
  }
}
