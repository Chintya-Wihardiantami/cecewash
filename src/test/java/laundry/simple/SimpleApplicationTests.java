package laundry.simple;

import com.cecewash.CeceWashApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CeceWashApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:cecewash;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
class SimpleApplicationTests {

    @Test
    void contextLoads() {
    }
}
