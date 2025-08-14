package org.griddynamics.config;

import org.aeonbits.owner.ConfigFactory;

public class Configuration {
    private static final EnviormentConfiguration ENV = ConfigFactory.create(EnviormentConfiguration.class);
    private static final RunConfiguration RUN = ConfigFactory.create(RunConfiguration.class);

    public static EnviormentConfiguration getEnv() {
        return ENV;
    }

    public static RunConfiguration getRun() {
        return RUN;
    }
}
