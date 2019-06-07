package DataAccess.entidades;

import DataAccess.entidades.Registros;
import DataAccess.entidades.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-07T00:42:01")
@StaticMetamodel(Personal.class)
public class Personal_ { 

    public static volatile SingularAttribute<Personal, String> prRfc;
    public static volatile CollectionAttribute<Personal, Registros> registrosCollection;
    public static volatile SingularAttribute<Personal, String> perNumPersonal;
    public static volatile SingularAttribute<Personal, Short> perEstado;
    public static volatile SingularAttribute<Personal, String> perApellidos;
    public static volatile SingularAttribute<Personal, String> perNumTelefono;
    public static volatile SingularAttribute<Personal, Character> perSexo;
    public static volatile SingularAttribute<Personal, String> perNombres;
    public static volatile SingularAttribute<Personal, String> perTurno;
    public static volatile SingularAttribute<Personal, Date> perFechaNac;
    public static volatile SingularAttribute<Personal, Usuarios> usuariosUsuId;

}