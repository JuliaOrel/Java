package org.example.myClassWork.september_08;

import java.io.*;

public class DTOObject {
    public static byte[] toBytes(Object o){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(o);
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

    public static Object toObject(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        Object o = null;

//        System.out.print("Bytes: ");
//        System.out.println(bytes);

        try {
            in = new ObjectInputStream(bis);
            o = in.readObject();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        return o;
    }
}
