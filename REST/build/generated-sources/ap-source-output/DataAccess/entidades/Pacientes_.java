package DataAccess.entidades;

import DataAccess.entidades.Citas;
import DataAccess.entidades.Consultas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-07T00:42:01")
@StaticMetamodel(Pacientes.class)
public class Pacientes_ { 

    public static volatile SingularAttribute<Pacientes, Character> pacSexo;
    public static volatile SingularAttribute<Pacientes, Integer> pacNumSeguro;
    public static volatile SingularAttribute<Pacientes, Date> pacFechaNac;
    public static volatile SingularAttribute<Pacientes, String> pacAlergias;
    public static volatile SingularAttribute<Pacientes, String> pacNombres;
    public static volatile CollectionAttribute<Pacientes, Citas> citasCollection;
    public static volatile SingularAttribute<Pacientes, String> pacApellidos;
    public static volatile SingularAttribute<Pacientes, String> pacNumTelefono;
    public static volatile CollectionAttribute<Pacientes, Consultas> consultasCollection;

}