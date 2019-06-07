package DataAccess.entidades;

import DataAccess.entidades.Pacientes;
import DataAccess.entidades.Recetas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-07T00:42:01")
@StaticMetamodel(Consultas.class)
public class Consultas_ { 

    public static volatile SingularAttribute<Consultas, Date> conHoraFin;
    public static volatile SingularAttribute<Consultas, String> conPresion;
    public static volatile SingularAttribute<Consultas, Pacientes> conSeguroPaciente;
    public static volatile SingularAttribute<Consultas, Float> conEstatura;
    public static volatile SingularAttribute<Consultas, Date> conHoraInicio;
    public static volatile SingularAttribute<Consultas, String> conDiagnostico;
    public static volatile SingularAttribute<Consultas, Float> conPeso;
    public static volatile SingularAttribute<Consultas, Integer> conIdPersonal;
    public static volatile SingularAttribute<Consultas, Recetas> conFolioReceta;
    public static volatile SingularAttribute<Consultas, Integer> conId;
    public static volatile SingularAttribute<Consultas, Date> conFecha;
    public static volatile SingularAttribute<Consultas, Float> conTemperatura;

}