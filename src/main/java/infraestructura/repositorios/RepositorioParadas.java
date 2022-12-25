package infraestructura.repositorios;

import dominio.mediosTransportes.Linea;
import dominio.mediosTransportes.Parada;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioParadas extends Repository<Parada> {

  public RepositorioLineas repositorioLineas = new RepositorioLineas(Linea.class);

  public RepositorioParadas(Class<Parada> type) {
    super(type);
  }

  public List<Parada> getParadasByLineaId(Integer id) {

//    //Esto no está bien pero funciona :)
//    CriteriaBuilder builder = EntityManagerHelper.entityManager().getCriteriaBuilder();
//    CriteriaQuery<Linea> critera = builder.createQuery(Linea.class);
//    Root<Linea> root =  critera.from(Linea.class);
//    critera
//        .select(root)
//        .where(builder
//            .equal(root.get("id"), id));
//
//    Linea linea = EntityManagerHelper.entityManager().createQuery(critera).getSingleResult();

//    return linea.getParadas();
    return this.repositorioLineas.buscar(id).getParadas();
  }
}
