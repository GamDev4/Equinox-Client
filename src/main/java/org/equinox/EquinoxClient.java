package org.equinox;

import net.fabricmc.api.ModInitializer;
import org.equinox.modules.*;
import org.equinox.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquinoxClient implements ModInitializer {
    public static final String MOD_ID = "equinox_client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String PREFIX = "§3[Equinox] §f";

    @Override
    public void onInitialize() {
        Help.help();

        Flight.module();
        Speed.module();
        FlySpeed.module();
        ServerBrand.module();
        ElytraFly.module();
        Pitch.module();
        Yaw.module();
    }

}
