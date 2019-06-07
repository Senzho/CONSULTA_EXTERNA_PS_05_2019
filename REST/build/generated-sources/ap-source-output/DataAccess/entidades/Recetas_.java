package DataAccess.entidades;

import DataAccess.entidades.Consultas;
import DataAccess.entidades.Medicamento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-07T00:42:01")
@StaticMetamodel(Recetas.class)
public class Recetas_ { 

    public static volatile SingularAttribute<Recetas, Integer> recFolio;
    public static volatile SingularAttribute<Recetas, Date> recFechaCreacion;
    public static volatile SingularAttribute<Recetas, Date> recFechaVencimiento;
    public static volatile CollectionAttribute<Recetas, Medicamento> medicamentoCollection;
    public static volatile SingularAttribute<Recetas, String> recInstrucciones;
    public static volatile CollectionAttribute<Recetas, Consultas> consultasCollection;

}