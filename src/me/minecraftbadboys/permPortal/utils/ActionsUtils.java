package me.minecraftbadboys.permPortal.utils;

import me.minecraftbadboys.permPortal.Main;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class ActionsUtils {

    public static String getAcquiredPermission() {
        return ConfigurationUtils.getConfig().getString("target-permission");
    }

    public static List<String> chkAcquiredServers() {

        String demoStatus = null;

        String targetA = ConfigurationUtils.getConfig().getString("Target-Server-A");
        String targetB = ConfigurationUtils.getConfig().getString("Target-Server-B");

        int errors = 0;
        int errorNum = 0;
        String errorNumS = null;

        List<String> status = new ArrayList<>();
        // Check target-A

        status.add("-----------------------------------------------------------");
        status.add("----- [[[ PERMISSION PORTAL SERVER CHECK(BUNGEE) ]]] ------");
        status.add("-----------------------------------------------------------");
        status.add(" ");

        try {
            ServerInfo a = Main.getInstance().getProxy().getServerInfo(targetA);
            demoStatus = "OK";
        } catch (RuntimeException ignored) {
            demoStatus = "R.I.P";
            errors = errorNum + 1;
        } finally {
            status.add(targetA + " --- [" + demoStatus + "] (Target A Server)");
            demoStatus = null;
        }

        status.add(" ");
        try {
            ServerInfo b = Main.getInstance().getProxy().getServerInfo(targetB);
            demoStatus = "OK";
        } catch (RuntimeException ignored) {
            demoStatus = "R.I.P";
            errors = errorNum + 1;
        } finally {
            status.add(targetB + " --- [" + demoStatus + "] (Target B Server)");
            demoStatus = null;
        }
        status.add(" ");
        status.add("-----------------------------------------------------------");

        if (errors == 0) {
            status.add("0 error(s) in 2 servers.");
        } else if (errors == 1) {
            status.add("1 error(s) in 2 servers. (1/2)");
            status.add("Have they been setup-ed in the configuration file of BungeeCord?");
        } else if (errors == 2) {
            status.add("2 error(s) in 2 servers. (2/2)");
            status.add("Have they been setup-ed in the configuration file of BungeeCord?");
        }
        status.add(" ");
        status.add("-----------------------------------------------------------");

        return status;
    }

}
