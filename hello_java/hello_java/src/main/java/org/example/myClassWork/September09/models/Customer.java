package org.example.myClassWork.September09.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myClassWork.September09.SerializableToBytes;

import java.io.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable, SerializableToBytes {
    private UUID customer_id;
    private UUID user_id;
    private String name;

    public byte[] toBytes(){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            bytes = bos.toByteArray();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                bos.close();
            } catch (Exception ex) {}
        }
//        System.out.print("Bytes: ");
//        System.out.println(bytes);
        return bytes;
    }
    public static Customer fromBytes(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        Customer c = null;

//        System.out.print("Bytes: ");
//        System.out.println(bytes);

        try {
            in = new ObjectInputStream(bis);
            c = (Customer) in.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        return c;
    }
    public static Customer fromUser(User u){
        Customer c=new Customer();
        c.setUser_id(u.getUser_id());
        c.setName(u.getName());
        c.setCustomer_id(UUID.randomUUID());
        return c;
    }
}
