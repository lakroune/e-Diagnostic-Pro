package ma.youcode.config;

import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class JPAInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Persistence.createEntityManagerFactory("eDiagnosticProPU");
            System.out.println("====== JPA Loaded: Tables Created ======");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}