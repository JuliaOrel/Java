package org.example.myClassWork.crm;

import org.example.apps.CRM;
import org.example.apps.Site;
import org.example.servers.ServerSite;

public class mainSite {
    public static void main(String[] args) {
        Site site=new Site();
        ServerSite server = new ServerSite(site);
        Thread t = new Thread(server);
        t.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        site.run();
    }
}
