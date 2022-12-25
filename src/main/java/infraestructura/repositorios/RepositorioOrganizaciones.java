package infraestructura.repositorios;

import infraestructura.repositorios.interfaces.IRepositorioOrganizaciones;
import dominio.organizacion.Organizacion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

public class RepositorioOrganizaciones extends Repository<Organizacion> implements IRepositorioOrganizaciones {
    public RepositorioOrganizaciones(Class type) {
        super(type);
    }

    public List<Organizacion> getOrganizaciones() {
        return generarOrganizaciones();
    }
    private List<Organizacion> generarOrganizaciones() {
        return Arrays.asList(
                new Organizacion(null, null, "andgomez@frba.utn.edu.ar", null, null, null),
                new Organizacion(null, null, "cdesiderio@frba.utn.edu.ar", null, null, null),
                new Organizacion(null, null, "framirez@frba.utn.edu.ar", null, null, null),
                new Organizacion(null, null, "carlovelasquezmenchaca@frba.utn.edu.ar", null, null, null),
                new Organizacion(null, null, "sbramuglia@frba.utn.edu.ar", null, null, null)
        );
    }

    @Override
    public List<Organizacion> getOrganizacionesPorMunicipio(Integer idMunicipio) { return null; }

    @Override
    public List<Organizacion> getOrganizacionesPorProvincia(Integer idProvincia) { return null; }

    public Organizacion getByUsuarioId(Integer usuarioId) {
//        CriteriaBuilder builder = EntityManagerHelper.entityManager().getCriteriaBuilder();
//        CriteriaQuery<Organizacion> critera = builder.createQuery(Organizacion.class);
//        Root<Organizacion> root =  critera.from(Organizacion.class);
//        critera
//            .select(root)
//            .where(builder
//                .equal(root.get("usuario"), usuarioId));
//        return EntityManagerHelper.entityManager().createQuery(critera).getResultList().stream().findFirst().orElse(null);
        return buscarTodos().stream().filter(o -> o.getUsuario().getId().equals(usuarioId)).findFirst().orElse(null);
    }
}
