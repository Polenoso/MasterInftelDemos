package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-27T13:39:53")
@StaticMetamodel(Ingresos.class)
public class Ingresos_ { 

    public static volatile SingularAttribute<Ingresos, String> fecha;
    public static volatile SingularAttribute<Ingresos, Long> idIngreso;
    public static volatile SingularAttribute<Ingresos, String> concepto;
    public static volatile SingularAttribute<Ingresos, Usuario> idUserIngreso;
    public static volatile SingularAttribute<Ingresos, Long> cantidad;

}