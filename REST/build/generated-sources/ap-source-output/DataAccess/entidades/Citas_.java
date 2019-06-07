package DataAccess.entidades;

import DataAccess.entidades.Pacientes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-07T00:42:01")
@StaticMetamodel(Citas.class)
public class Citas_ { 

    public static volatile SingularAttribute<Citas, Integer> citIdPersonal;
    public static volatile SingularAttribute<Citas, Pacientes> citNumSeguroPaciente;
    public static volatile SingularAttribute<Citas, Date> citFechaHoraReserva;
    public static volatile SingularAttribute<Citas, Integer> citEstado;
    public static volatile SingularAttribute<Citas, Integer> citId;

}