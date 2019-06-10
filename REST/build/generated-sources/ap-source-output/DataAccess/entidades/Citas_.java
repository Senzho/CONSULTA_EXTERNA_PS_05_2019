package DataAccess.entidades;

import DataAccess.entidades.Pacientes;
import DataAccess.entidades.Personal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-10T00:46:21")
@StaticMetamodel(Citas.class)
public class Citas_ { 

    public static volatile SingularAttribute<Citas, Pacientes> citNumSeguroPaciente;
    public static volatile SingularAttribute<Citas, Date> citFechaHoraReserva;
    public static volatile SingularAttribute<Citas, Integer> citEstado;
    public static volatile SingularAttribute<Citas, Integer> citId;
    public static volatile SingularAttribute<Citas, Personal> citPrRfc;

}