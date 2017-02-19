package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Gastos;
import model.Ingresos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-27T13:39:53")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Long> idUser;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile CollectionAttribute<Usuario, Gastos> gastosCollection;
    public static volatile SingularAttribute<Usuario, String> nickname;
    public static volatile CollectionAttribute<Usuario, Ingresos> ingresosCollection;

}