package me.noah.wtap;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class WTapAddon extends MeteorAddon {
    @Override
    public void onInitialize() {
        Modules.get().add(new WTap());
    }

    @Override
    public String getPackage() {
        return "me.noah.wtap";
    }
}
