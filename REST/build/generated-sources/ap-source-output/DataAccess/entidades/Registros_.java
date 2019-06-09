package DataAccess.entidades;

import DataAccess.entidades.Personal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-08T17:59:26")
@StaticMetamodel(Registros.class)
public class Registros_ { 

    public static volatile SingularAttribute<Registros, Date> regHoraEntrada;
    public static volatile SingularAttribute<Registros, Date> regHoraSalida;
    public static volatile SingularAttribute<Registros, Integer> regId;
    public static volatile SingularAttribute<Registros, String> regLugarEstadia;
    public static volatile SingularAttribute<Registros, Personal> personalPrRfc;

}