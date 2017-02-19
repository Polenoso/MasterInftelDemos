/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreservlets;

/**
 *
 * @author aitorpagan
 */

import javax.faces.bean.*;

/** From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF 1.x, JSF 2.0, Ajax, 
 *  GWT, Spring, Hibernate/JPA, and Java programming</a>.
 */

@ManagedBean
public class MyBean {
    private String helloWorld = "helloWorld";

    public String getHelloWorld() {
        return helloWorld;
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }
    
    public String showHelloWorld(){
        return ("hello-world");
    }
}
