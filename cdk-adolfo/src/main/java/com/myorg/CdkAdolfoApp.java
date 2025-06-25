package com.myorg;

import dev.stratospheric.cdk.SpringBootApplicationStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

import java.util.Arrays;
import java.util.Objects;

public class CdkAdolfoApp {
    public static void main(final String[] args) {
        App app = new App();

        // context variables that come from the command line
        String accountId = (String) app
          .getNode()
          .tryGetContext("accountId");
        Objects.requireNonNull(accountId, "context variable 'accountId' must not be null");

        String region = (String) app
          .getNode()
          .tryGetContext("region");
        Objects.requireNonNull(region, "context variable 'region' must not be null");

        new SpringBootApplicationStack(
          app,
          "SpringBootApplication",
          makeEnv(accountId, region),
          "docker.io/adolfoweloy/todo-app-v1:latest"
        );

        app.synth();
    }

  private static Environment makeEnv(String accountId, String region) {
        return Environment.builder()
                .account(accountId)
                .region(region)
                .build();
  }
}

