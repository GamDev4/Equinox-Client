package org.equinox;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.equinox.commands.*;
import org.equinox.modules.movement.*;
import org.equinox.modules.player.*;

public class EquinoxClient implements ModInitializer {
    public static final String MOD_ID = "equinox_client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String CLIENT_PREFIX = "§3[Equinox] §f";

    @Override
    public void onInitialize() {
        Help.command();

        Flight.module();
        Speed.module();
        FlySpeed.module();
        ServerBrand.command();
        ElytraFly.module();
        Pitch.module();
        Yaw.module();
    }

}
