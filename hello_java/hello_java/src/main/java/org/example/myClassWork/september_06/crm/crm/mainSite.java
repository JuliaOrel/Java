package org.example.myClassWork.september_06.crm.crm;

import org.example.myClassWork.september_06.crm.apps.Site;
import org.example.myClassWork.september_06.crm.servers.ServerSite;

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
