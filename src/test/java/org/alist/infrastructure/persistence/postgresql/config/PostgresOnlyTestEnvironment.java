package org.alist.infrastructure.persistence.postgresql.config;

import org.microshed.testing.SharedContainerConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class PostgresOnlyTestEnvironment implements SharedContainerConfiguration {
    @Container
    public static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres");

    @Override
    public void startContainers() {
        System.setProperty("quarkus.oidc.enabled", Boolean.FALSE.toString());
        postgresql.start();
    }
}
