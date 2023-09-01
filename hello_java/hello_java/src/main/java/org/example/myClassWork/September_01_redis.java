package org.example.myClassWork;

import redis.clients.jedis.Jedis;

public class September_01_redis implements Runnable{
    @Override
    public void run() {
        connectToRedis();
    }

    private void connectToRedis(){
        Jedis redis=new Jedis("localhost", 6379);
        //Put into server data
        redis.set("SomeKey", "SomeValue");
        redis.expire("SomeKey",60);
        String val=redis.get("SomeKey");
        System.out.println("SomeVal= "+val);
    }
}
