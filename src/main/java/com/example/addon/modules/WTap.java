package me.noah.wtap;

import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.events.entity.player.AttackEntityEvent;
import meteordevelopment.orbit.EventHandler;

public class WTap extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Integer> delayMs = sgGeneral.add(
        new IntSetting.Builder()
            .name("delay-ms")
            .description("Sprint reset delay in milliseconds")
            .defaultValue(80)
            .min(10)
            .max(300)
            .build()
    );

    private long lastHit;
    private boolean active;

    public WTap() {
        super(Category.COMBAT, "WTap", "Automatically W-taps on hit");
    }

    @EventHandler
    private void onAttack(AttackEntityEvent event) {
        if (mc.player == null) return;

        lastHit = System.currentTimeMillis();
        active = true;
        mc.player.setSprinting(false);
    }

    @Override
    public void tick() {
        if (!active || mc.player == null) return;

        if (System.currentTimeMillis() - lastHit >= delayMs.get()) {
            mc.player.setSprinting(true);
            active = false;
        }
    }
}
