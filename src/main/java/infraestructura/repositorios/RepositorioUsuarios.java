package infraestructura.repositorios;

import dominio.usuarios.Usuario;
import shared.helpers.Helpers;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RepositorioUsuarios extends Repository<Usuario>{
  public RepositorioUsuarios(Class<Usuario> type) {
    super(type);
  }

  public Usuario getUsuarioByUsuario(String usuario) {
//    CriteriaBuilder builder = EntityManagerHelper.entityManager().getCriteriaBuilder();
//    CriteriaQuery<Usuario> critera = builder.createQuery(Usuario.class);
//    Root<Usuario> root =  critera.from(Usuario.class);
//    critera
//        .select(root)
//        .where(builder
//            .equal(root.get("nombreUsuario"), usuario));
//
//    return EntityManagerHelper.entityManager().createQuery(critera).
//                                        getResultList().
//                                        stream().findFirst().orElse(null);
    return buscarTodos().stream().filter(u -> Helpers.stringCompare(u.getNombreUsuario(), usuario)).findFirst().orElse(null);
  }
}
