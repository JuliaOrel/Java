package org.example.myClassWork;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;


public class September_01_Mongo implements Runnable{
    @Override
    public void run() {
        connectToMongo();
    }

    private void connectToMongo(){

        String connectionStr="mongodb://root:password@localhost:27017";

        MongoClient mongoClient = MongoClients.create(connectionStr);

        MongoDatabase mongoDatabase=mongoClient.getDatabase("pv121");

        MongoCollection<Document> collection=mongoDatabase.getCollection("documents");

        Document newDoc=new Document("key", "val");
        newDoc.append("otherKey","otherValue");
        ArrayList<Integer>vars=new ArrayList<>();
        vars.add(10);
        newDoc.append("someArr",vars);

        collection.insertOne(newDoc);


    }
}
