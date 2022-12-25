package infraestructura.db;

import java.util.Properties;

import dominio.actividades.*;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.mediosTransportes.*;
import dominio.organizacion.Clasificacion;
import dominio.organizacion.Miembro;
import dominio.organizacion.Organizacion;
import dominio.organizacion.Sector;
import dominio.usuarios.AgenteSectorial;
import dominio.usuarios.Persona;
import dominio.usuarios.Usuario;
import dominio.viajes.InvitacionTramo;
import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;

import javax.sound.sampled.Line;

public class HibernateUtils {

  //XML based configuration
  private static SessionFactory sessionFactory;

  //Annotation based configuration
  private static SessionFactory sessionAnnotationFactory;

  //Property based configuration
  private static SessionFactory sessionJavaConfigFactory;

  private static SessionFactory buildSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      Configuration configuration = new Configuration();
      //configuration.configure("hibernate.cfg.xml");
      System.out.println("Hibernate Configuration loaded");


      configuration.addAnnotatedClass(Actividad.class);
      configuration.addAnnotatedClass(CombustionFija.class);
      configuration.addAnnotatedClass(CombustionMovil.class);
      configuration.addAnnotatedClass(ElectricaAdquiridaConsumida.class);
      configuration.addAnnotatedClass(LogisticaProductosResiduos.class);

      configuration.addAnnotatedClass(FactorEmision.class);
      configuration.addAnnotatedClass(Combustible.class);

      configuration.addAnnotatedClass(Linea.class);
      configuration.addAnnotatedClass(Parada.class);
      configuration.addAnnotatedClass(MediosPropios.class);
      configuration.addAnnotatedClass(MedioTransporte.class);
      configuration.addAnnotatedClass(ServicioContratado.class);
      configuration.addAnnotatedClass(TipoServContratado.class);
      configuration.addAnnotatedClass(TransportePublico.class);
      configuration.addAnnotatedClass(VehiculoParticular.class);
      configuration.addAnnotatedClass(MediosPropios.class);

      configuration.addAnnotatedClass(Organizacion.class);
      configuration.addAnnotatedClass(Clasificacion.class);
      configuration.addAnnotatedClass(Miembro.class);
      configuration.addAnnotatedClass(Sector.class);

      configuration.addAnnotatedClass(AgenteSectorial.class);
      configuration.addAnnotatedClass(Persona.class);
      configuration.addAnnotatedClass(Usuario.class);

      configuration.addAnnotatedClass(InvitacionTramo.class);
      configuration.addAnnotatedClass(Tramo.class);
      configuration.addAnnotatedClass(Trayecto.class);

      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
          .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
          .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
//          .applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/huella?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
//          .applySetting("hibernate.connection.username", "root")
//          .applySetting("hibernate.connection.password", "123456")
          .applySetting("hibernate.connection.url", "jdbc:mysql://huella.mysql.database.azure.com:3306/huella?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false")
          .applySetting("hibernate.connection.username", "PatitasDevs")
          .applySetting("hibernate.connection.password", "Pass.123")
          .applySetting("hibernate.current_session_context_class", "thread")
          .applySetting("hibernate.c3p0.timeout", "8000")
          .applySettings(configuration.getProperties())
          .build();

  //    MetadataSources metadataSources = new MetadataSources(serviceRegistry);
//      metadataSources.addAnnotatedClass(Combustible.class);
//      metadataSources.addAnnotatedClass(FactorEmision.class);

    //  Metadata metadata = metadataSources.getMetadataBuilder().build();

      System.out.println("Hibernate serviceRegistry created");

//      SessionFactory sessionFactory = metadata.buildSessionFactory();

      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

      return sessionFactory;
    }
    catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  private static SessionFactory buildSessionAnnotationFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      Configuration configuration = new Configuration();
      configuration.configure("hibernate-annotation.cfg.xml");
      System.out.println("Hibernate Annotation Configuration loaded");

      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
      System.out.println("Hibernate Annotation serviceRegistry created");

      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

      return sessionFactory;
    }
    catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }


  public static SessionFactory getSessionFactory() {
    if(sessionFactory == null) sessionFactory = buildSessionFactory();
    return sessionFactory;
  }

  public static SessionFactory getSessionAnnotationFactory() {
    if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
    return sessionAnnotationFactory;
  }
}
