package org.griddynamics.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:env.properties")
public interface EnviormentConfiguration extends Config {

    @Key("site.url")
    String siteUrl();

    @Key("remote.url")
    String remoteUrl();
}
