package ru.netology.sboot_task1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.sboot_task1.profile.DevProfile;
import ru.netology.sboot_task1.profile.ProductionProfile;
import ru.netology.sboot_task1.profile.SystemProfile;

@Configuration
public class JavaConfig {
  @Bean
  @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "true")
  public SystemProfile devProfile() {
    return new DevProfile();
  }

  @Bean
  @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "false")
  public SystemProfile prodProfile() {
    return new ProductionProfile();
  }
}
