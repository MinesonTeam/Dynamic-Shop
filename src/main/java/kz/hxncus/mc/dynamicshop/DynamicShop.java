package kz.hxncus.mc.dynamicshop;

import kz.hxncus.mc.dynamicshop.service.EconomyService;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class DynamicShop extends JavaPlugin {

    private Economy economy;
    private EconomyService economyService;

    @Getter
    private static DynamicShop instance;

    @Override
    public void onEnable() {
        instance = this;

        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        this.economyService = new EconomyService(this);

        getLogger().info(getName() + " enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled");
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp =
                Bukkit.getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            return false;
        }

        economy = rsp.getProvider();
        return true;
    }
}