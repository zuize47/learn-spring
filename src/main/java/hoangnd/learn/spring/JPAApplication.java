package hoangnd.learn.spring;

import hoangnd.learn.spring.entity.KnowledgeBase;
import hoangnd.learn.spring.jpa.JPAConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Log4j2
@EnableTransactionManagement
@ComponentScan("hoangnd.learn.spring.jpa")
public class JPAApplication {

    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    public JPAApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void run() {
        log.info(this.jdbcTemplate);
        log.info(this.entityManager);
        var a = this.entityManager.find(KnowledgeBase.class, 1001L);
        log.info(a);
    }

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(JPAApplication.class);
            context.register(DataSourceConfig.class);
            context.register(JPAConfig.class);
            context.refresh();
            context.getBean(JPAApplication.class).run();
        }

    }
}
