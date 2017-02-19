package coreservlets;

import javax.faces.bean.*;

/** The only reason for the subclass is so that I can use "eager" 
 *  here but not in previous example that used CustomerSimpleMap.
 */

@ManagedBean(eager=true)
@ApplicationScoped
public class CustomerSimpleMap2 
       extends CustomerSimpleMap {
}
