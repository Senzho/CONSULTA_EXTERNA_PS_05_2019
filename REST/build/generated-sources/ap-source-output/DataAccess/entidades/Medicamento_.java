package DataAccess.entidades;

import DataAccess.entidades.Recetas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-07T00:42:01")
@StaticMetamodel(Medicamento.class)
public class Medicamento_ { 

    public static volatile SingularAttribute<Medicamento, Date> medFechaCaducidad;
    public static volatile CollectionAttribute<Medicamento, Recetas> recetasCollection;
    public static volatile SingularAttribute<Medicamento, String> medNombre;
    public static volatile SingularAttribute<Medicamento, Integer> medCodigo;
    public static volatile SingularAttribute<Medicamento, Float> medGramaje;

}