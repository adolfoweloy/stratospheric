package com.myorg;

import dev.stratospheric.cdk.DockerRepository;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

import java.util.Objects;

public class DockerRepositoryApp {

    public static void main(final String[] args) {
      App app = new App();

      String accountId = (String) app
        .getNode()
        .tryGetContext("accountId");
      Objects.requireNonNull(accountId, "context variable 'accountId' must not be null");

      String region = (String) app
        .getNode()
        .tryGetContext("region");
      Objects.requireNonNull(region, "context variable 'region' must not be null");

      String applicationName = (String) app
        .getNode()
        .tryGetContext("applicationName");
      Objects.requireNonNull(applicationName, "context variable 'applicationName' must not be null");

      Environment env = Environment.builder()
        .account(accountId)
        .region(region)
        .build();

      Stack dockerRepositoryStack = new Stack(
        app,
        "DockerRepositoryStack",
        StackProps.builder()
          .stackName(applicationName + "-DockerRepository")
          .env(env)
          .build()
      );

      new DockerRepository(
        dockerRepositoryStack,
        "DockerRepository",
        env,
        new DockerRepository.DockerRepositoryInputParameters(applicationName, accountId)
      );

      app.synth();
    }


}
