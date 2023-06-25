package me.minecraftbadboys.permPortal;

import me.minecraftbadboys.permPortal.events.LoginListener;
import me.minecraftbadboys.permPortal.utils.ActionsUtils;
import me.minecraftbadboys.permPortal.utils.ConfigurationUtils;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.List;

public class Main extends Plugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigurationUtils.registerConfiguration();

        System.out.println("Checking Servers......");
        List<String> a = ActionsUtils.chkAcquiredServers();

        for (String b : a) {
            System.out.println(b);
        }

        System.out.println("Registering Listeners......");
        try {
            registerListener(new LoginListener());
        } catch (RuntimeException ignored) {
            System.out.println("Failed at registering Event Listeners!");
            System.out.println("Error Code: 0x3186498B");
            System.out.println("Go https://badboysnetworksupport.helpsite.com/articles/102102-boat-team-plugin-error-code-troubleshoot and check!");
        }

        System.out.println("[PermissionPortal] Plugin loaded.");
    }

    @Override
    public void onDisable() {
        System.out.println("[PermissionPortal] Plugin unloaded.");
    }

    public static Main getInstance() {
        return instance;
    }

    public void registerListener(Listener listener) {
        getProxy().getPluginManager().registerListener(this, listener);
    }

}
