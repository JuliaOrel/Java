package org.example.myClassWork;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.files.Human;
import org.example.entities.files.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class August29Files implements Runnable{
   private String filePath = "humans.json";
   private String filePathBin = "persons.bin";
    @Override
    public void run() {
        //writeFile();
        //writeFileOutputStream();
        //saveObj();
        //loadObj();
        //saveObjToJson();
        //loadObjFromJson();
        //saveListToJson();
        //loadListFromJson();
        //saveListToBinary();
        loadListFromBinary();
    }

    private void loadListFromBinary() {
        List<Person> persons = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePathBin))) {
            persons = (List<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Person person : persons) {
            System.out.printf("Name: %s \t Age: %d\n",
                    person.getName(), person.getAge());
        }
    }

    private void saveListToBinary() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 25));
        persons.add(new Person("Bob", 30));


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePathBin))) {
            oos.writeObject(persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveListToJson() {
        List<Human> humans = new ArrayList<>();
        humans.add(new Human("Samanta", 45, true));
        humans.add(new Human("John", 30, false));

        ObjectMapper objectMapper = new ObjectMapper();

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            objectMapper.writeValue(fileWriter, humans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadListFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Human> loadedHumans=new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath)) {
            TypeReference<List<Human>> typeReference = new TypeReference<List<Human>>() {};
            loadedHumans= objectMapper.readValue(fileReader, typeReference);
        } catch (IOException e) {
            e.printStackTrace();

        }
        for (Human human : loadedHumans) {
            System.out.printf("Name: %s \t Age: %d \t Is Married: %b\n",
                    human.getName(), human.getAge(), human.isMarried());
        }
    }

    private void saveObjToJson(){
        Human human=new Human("Samanta",45, true);
        // 1. Экземпляр класса, который будет формировать из моего объекта JSON строку
        ObjectMapper objectMapper = new ObjectMapper();

        // 2. Открываю файл на запись
        try (FileWriter fileWriter = new FileWriter("human.json")) {
            // 3. Записываю результат трансформации (объекта в json) в файл
            objectMapper.writeValue(fileWriter, human);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void loadObjFromJson(){
        // 1. Экземпляр класса, который будет формировать из JSON строки объект
        ObjectMapper objectMapper = new ObjectMapper();

        // 2. Открыть файл с данными на чтение
        try (FileReader fileReader = new FileReader("human.json")) {
            // 3. Считать стоку и сформировать на ее основе объект
            // Мне нужно описать @JsonCreator - правила по которому будет формироваться объект
            // из полей JSON строки
            Human human = objectMapper.readValue(fileReader, Human.class);

            System.out.printf("Name: %s \t Age: %d \t Is Married: %b\n", human.getName(), human.getAge(), human.isMarried());
        } catch (IOException e) {
            e.getMessage();
        }


    }

    private void saveObj(){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("person.dat"))){
            Person p=new Person("Sam",33);
            oos.writeObject(p);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void loadObj(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat")))
        {
            Person p=(Person)ois.readObject();
            System.out.printf("Name: %s \t Age: %d \n", p.getName(), p.getAge());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void  writeFile(){
        try(FileWriter writer = new FileWriter("notes.txt", false))
        {
            // запись всей строки
            String text = "Hello World!";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.flush();

            writer.write(" File Open ");
            writer.flush(); // Будет вызван в момент закрытия файла
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    private void writeFileOutputStream(){
        String text = "Hello world!"; // строка для записи

        try(FileOutputStream fos=new FileOutputStream("notes.txt"))
        {
            // перевод строки в байты
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("The file has been written");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void readFileByChar() {
        try(FileReader reader = new FileReader("notes.txt"))
        {
            // читаем посимвольно
            int c;
            StringBuilder sb = new StringBuilder();
            while((c=reader.read())!=-1){
                System.out.print((char)c);
                sb.append((char) c);
            }
            System.out.println(sb.toString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void readFileInputStreamToBuffer() {
        try(FileInputStream fin=new FileInputStream("notes.txt"))
        {
            // Лучший вариант чтения файла с помощью потока - это подготовить буфер соответствующего размера
            // Например - получить размер файла - и создать для него буффер в оперативной памяти
            File file = new File("notes.txt"); // Укажите путь к вашему файлу
            int fileSizeBytes = (int) file.length();
            byte[] buffer = new byte[fileSizeBytes];

            int count;
            while((count=fin.read(buffer))!=-1){
//                System.out.println(count);
//                buffer[count] = '\n';
//                for(int i=0; i<count;i++){
//                    System.out.print((char)buffer[i]);
//                }
            }
            String str = new String(buffer, StandardCharsets.UTF_8);
            System.out.println(str);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    private void readFileInputStream() {
        try(FileInputStream fin=new FileInputStream("notes.txt"))
        {
            int i;
            while((i=fin.read())!=-1){
                System.out.print((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
