package me.nexcreep.policestick;

import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Main extends JavaPlugin {
    Logger log = new Logger();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Event(), this);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log.info(String.format("Successfully loaded at %s", dtf.format(now)), true);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log.info(String.format("Successfully unloaded at %s", dtf.format(now)), true);
    }
}
