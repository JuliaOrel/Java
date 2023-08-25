package org.example.tax;

import java.util.ArrayList;
import java.util.List;

public class TaxOfficeWork implements Runnable{
    @Override
    public void run() {
        TaxData taxDatabase=new TaxData();
        taxDatabase.addNewPerson("12345");
        taxDatabase.addFineToPerson("12345", "Неправильная парковка");
        taxDatabase.addFineToPerson("12345", "Превышение скорости");

        taxDatabase.addNewPerson("54321");
        taxDatabase.addFineToPerson("54321", "Отсутствие страховки");

        taxDatabase.printAllRecords();

        taxDatabase.deleteFine("12345", "Неправильная парковка");

        List<String> newFines = new ArrayList<>();
        newFines.add("Пьяное вождение");
        taxDatabase.replacePersonInfo("54321", newFines);

        taxDatabase.printRecordsByPIN("12345");
        taxDatabase.printRecordsByPIN("54321");
    }
}
