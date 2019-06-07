package DataAccess.entidades;

import DataAccess.entidades.Personal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-07T00:42:01")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> usuNombre;
    public static volatile SingularAttribute<Usuarios, Integer> usuId;
    public static volatile CollectionAttribute<Usuarios, Personal> personalCollection;
    public static volatile SingularAttribute<Usuarios, String> usuRol;
    public static volatile SingularAttribute<Usuarios, String> usuContrasena;

}