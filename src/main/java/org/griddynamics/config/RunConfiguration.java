package org.griddynamics.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:run.properties")
public interface RunConfiguration extends Config {

    @Key("browser.name")
    String browser();

    @Key("browser.width")
    String width();

    @Key("browser.height")
    String height();

    @Key("is.remote")
    Boolean isRemote();
}
