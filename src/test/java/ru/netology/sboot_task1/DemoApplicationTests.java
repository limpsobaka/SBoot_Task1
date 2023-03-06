package ru.netology.sboot_task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
  @Autowired
  TestRestTemplate restTemplate;

  @Container
  private static final GenericContainer<?> devApp = new GenericContainer<>("devapp").withExposedPorts(8080);
  @Container
  private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp").withExposedPorts(8081);

  @BeforeAll
  public static void setUp() {
    devApp.start();
    prodApp.start();
  }

  @Test
  void devLoads() {
    String target = "Current profile is dev";
    ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
    Assertions.assertEquals(target, forEntity.getBody());
  }

  @Test
  void prodLoads() {
    String target = "Current profile is production";
    ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
    Assertions.assertEquals(target, forEntity.getBody());
  }
}