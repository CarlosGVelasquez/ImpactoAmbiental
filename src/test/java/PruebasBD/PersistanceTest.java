//package PruebasBD;
//
//
//import org.junit.jupiter.api.Test;
//
//import static org.mockito.ArgumentMatchers.any;
//
//public class PersistanceTest {
//
//  @Test
//  public void persistirUsuario() {
//
////  Usuario usuario = new Usuario("Juan", "123456", RolesUsuario.MIEMBRO);
////  Persona persona = new Persona("Cosme", "123456", "Fulanito", usuario, "mail@mail.com", TipoDocumento.DNI);
//
////  EntityManagerHelper.beginTransaction();
////  EntityManagerHelper.getEntityManager().persist(persona);
////  EntityManagerHelper.commit();
//  }
//
//  @Test
//  public void persistirTrayecto() {
//
////    Direccion inicio = new Direccion("Avenida siemprevivas", "555", 42);
////    Direccion fin = new Direccion("El cochinote", "999", 43);
////
////    Trayecto trayecto = new Trayecto(inicio, fin);
////    MedioTransporte bici = new MediosPropios("bici");
////
////    Tramo tramo1 = new Tramo(inicio, fin, "De Springfield a la planta nuclear", bici);
////    Tramo tramo2 = new Tramo(fin, inicio, "De la planta nuclear a lo de Ned Flanders", bici);
////
////    trayecto.addTramo(tramo1);
////    trayecto.addTramo(tramo2);
////
////    EntityManagerHelper.beginTransaction();
////    EntityManagerHelper.getEntityManager().persist(trayecto);
////    EntityManagerHelper.commit();
//  }
//
//  @Test
//  public void recuperarPersona() {
////    Persona CosmeFulanito = (Persona) EntityManagerHelper.createQuery("from Persona where nombre='Cosme'").getResultList().get(0);
////    Assert.assertEquals("Cosme", CosmeFulanito.getNombre());
//  }
//
//  @Test
//  public void persistirInvitacionTramo() {
////    Persona CosmeFulanito = (Persona) EntityManagerHelper.createQuery("from Persona where nombre='Cosme'").getResultList().get(0);
////    Persona DonBarredora = (Persona) EntityManagerHelper.createQuery("from Persona where nombre='Don'").getResultList().get(0);
////    Miembro miembro1 = new Miembro(CosmeFulanito, null, null, true);
////    Miembro miembro2 = new Miembro(DonBarredora, null, null, true);
//
////    Miembro miembro1 = (Miembro) EntityManagerHelper.createQuery("from Miembro where id=1").getResultList().get(0);
////    Miembro miembro2 = (Miembro) EntityManagerHelper.createQuery("from Miembro where id=2").getResultList().get(0);
////    Trayecto trayectoACasaDeNed = (Trayecto) EntityManagerHelper.createQuery("from Trayecto where id=2").getResultList().get(0);
////    Trayecto trayectoACampus = (Trayecto) EntityManagerHelper.createQuery("from Trayecto where id=1").getResultList().get(0);
////
////    miembro1.setTrayecto(trayectoACampus);
////    miembro2.setTrayecto(trayectoACasaDeNed);
////
////    EntityManagerHelper.beginTransaction();
////    EntityManagerHelper.getEntityManager().merge(miembro1);
////    EntityManagerHelper.getEntityManager().merge(miembro2);
////    EntityManagerHelper.commit();
//  }
//
//  @Test
//  public void testInvitarMiembro() {
////    Miembro miembro1 = (Miembro) EntityManagerHelper.createQuery("from Miembro where id=1").getResultList().get(0);
////    Miembro miembro2 = (Miembro) EntityManagerHelper.createQuery("from Miembro where id=2").getResultList().get(0);
////    Tramo tramoCompartido = (Tramo) EntityManagerHelper.createQuery("from Tramo where id=1").getResultList().get(0);
//
////    IRepositorioInvitacionTramos repoMock = mock(IRepositorioInvitacionTramos.class);
////
////    doNothing().when(repoMock.add(any()));
////    ServicioTramos s = new ServicioTramos(repoMock);
//
////    InvitacionTramo i = s.compartirTramoEntre(miembro1, miembro2, tramoCompartido);
//
////    InvitacionTramo i = (InvitacionTramo) EntityManagerHelper.createQuery("from InvitacionTramo where id=1").getResultList().get(0);
////
////    i.aceptar();
////    EntityManagerHelper.beginTransaction();
////    EntityManagerHelper.getEntityManager().merge(i);
////    EntityManagerHelper.commit();
//
//  }
//
//}