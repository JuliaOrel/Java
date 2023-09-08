package org.example.myClassWork.september_06.crm.crm;

import org.example.myClassWork.september_06.crm.apps.CRM;
import org.example.myClassWork.september_06.crm.servers.ServerCRM;

public class mainCRM {
    public static void main(String[] args) {
        CRM work = new CRM();
        ServerCRM server = new ServerCRM(work);
        Thread t = new Thread(server);
        t.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        work.run();
    }

}
