# Testing spring cloud config property override

We have a simple config client `testservice` that has two local configuration files in `src/main/resources`

- application.yml
- application-dev.yml

Also the service uses a config server (`testconfigserver`) to retrieve centralized configuration.

The repository used is `testrepo` and contains two additional files:

- application-dev.yml
- testservice-api.yml

## Run the config server

Run config server:
```bash
cd testconfigserver
gradle bootRun
```

## Use case 1  - get properties without profile

Run service:
```bash
cd testservice
gradle bootRun
```

Get the evaluated config properties using a custom REST endpoint:

```bash
http :8080/foo-config
```

Response:
```json
{
    "foo.bar": "some-specific",
    "foo.buz": "some",
    "foo.joe": "some"
}
```

The configuration from the configuration repository in testservice-api.yml is overriding the application.yml properties.

## Use case 2  - get properties with dev profile

Run service:
```bash
cd testservice
SPRING_PROFILES_ACTIVE=dev gradle bootRun
```

Get the evaluated config properties using a custom REST endpoint:

```bash
http :8080/foo-config
```

Response:
```json
{
    "foo.bar": "some-general-dev",
    "foo.buz": "some-general-dev",
    "foo.joe": "some"
}
```

The configuration from the configuration repository in application-dev.yml is overriding the testservice-api.yml and the local application.yml properties. Profile specific configuration have a higher priority.

## Use case 3  - override properties with environment variables

Run service:
```bash
cd testservice
FOO_BAR=some-env FOO_JOE=some-env gradle bootRun
```

Get the evaluated config properties using a custom REST endpoint:

```bash
http :8080/foo-config
```

Response:
```json
{
    "foo.bar": "some-specific",
    "foo.buz": "some",
    "foo.joe": "some-env"
}
```

The environment variable just overrides the local property `foo.joe` but not the property `foo.bar` that originates from the config server repository.

Is this a bug? Raised SO question - http://stackoverflow.com/questions/37316835/spring-cloud-config-server-priority-of-environment-variables
