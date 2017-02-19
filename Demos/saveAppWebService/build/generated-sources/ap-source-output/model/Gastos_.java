package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-27T13:39:53")
@StaticMetamodel(Gastos.class)
public class Gastos_ { 

    public static volatile SingularAttribute<Gastos, Long> idGasto;
    public static volatile SingularAttribute<Gastos, String> fecha;
    public static volatile SingularAttribute<Gastos, String> tipo;
    public static volatile SingularAttribute<Gastos, String> concepto;
    public static volatile SingularAttribute<Gastos, Long> cantidad;
    public static volatile SingularAttribute<Gastos, Usuario> idUserGasto;

}