package me.minecraftbadboys.permPortal.events;

import me.minecraftbadboys.permPortal.Main;
import me.minecraftbadboys.permPortal.utils.ActionsUtils;
import me.minecraftbadboys.permPortal.utils.ConfigurationUtils;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        ServerInfo targetServer = null;
        ProxiedPlayer player = e.getPlayer();
        if (player.hasPermission(ActionsUtils.getAcquiredPermission())) {
            String InfoAKey = ConfigurationUtils.getConfig().getString("Target-Server-A");
            try {
                ServerInfo targetA = Main.getInstance().getProxy().getServerInfo(InfoAKey);
                targetServer = targetA;
            } catch (RuntimeException ignored) {
                System.out.println("Failed getting server (TargetA-Name:" + InfoAKey + ")!");
                System.out.println("Error Code: 0x8633175C");
                System.out.println("Go https://badboysnetworksupport.helpsite.com/articles/102102-boat-team-plugin-error-code-troubleshoot and check!");
            }
            System.out.println("Sending " + player.getDisplayName() + " to " + InfoAKey);
            try {
                player.connect(targetServer);
            } catch (RuntimeException ignored) {
                System.out.println("Failed at sending " + player.getDisplayName() + " to " + InfoAKey + " !");
                System.out.println("Error Code: 0x3186498B");
                System.out.println("Go https://badboysnetworksupport.helpsite.com/articles/102102-boat-team-plugin-error-code-troubleshoot and check!");
            } finally {
                System.out.println("Work done.");
            }
        } else {
            String InfoBKey = ConfigurationUtils.getConfig().getString("Target-Server-B");
            try {
                ServerInfo targetB = Main.getInstance().getProxy().getServerInfo(InfoBKey);
                targetServer = targetB;
            } catch (RuntimeException ignored) {
                System.out.println("Failed getting server (TargetB-Name:" + InfoBKey + ")!");
                System.out.println("Error Code: 0x8633175C");
                System.out.println("Go https://badboysnetworksupport.helpsite.com/articles/102102-boat-team-plugin-error-code-troubleshoot and check!");
            }
            System.out.println("Sending " + player.getDisplayName() + " to " + InfoBKey);
            try {
                player.connect(targetServer);
            } catch (RuntimeException ignored) {
                System.out.println("Failed at sending " + player.getDisplayName() + " to " + InfoBKey + " !");
                System.out.println("Error Code: 0x3186498B");
                System.out.println("Go https://badboysnetworksupport.helpsite.com/articles/102102-boat-team-plugin-error-code-troubleshoot and check!");
            } finally {
                System.out.println("Work done.");
            }
        }
    }

}
