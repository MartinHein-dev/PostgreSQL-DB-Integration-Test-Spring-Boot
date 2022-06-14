package de.weconvert.testcontainers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.weconvert.testcontainers.util.CentralPostgresqlContainer;

@SpringBootTest
class TestcontainersApplicationTests extends CentralPostgresqlContainer {

	@Test
	void contextLoads() {
	}

	
}
