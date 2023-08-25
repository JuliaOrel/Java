package org.example.tax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxData {
    private Map<String, List<String>> database;

    public TaxData() {
        this.database = new HashMap<>();
    }

    public void printAllRecords() {
        for (Map.Entry<String, List<String>> entry : database.entrySet()) {
            System.out.println("ПИН: " + entry.getKey());
            System.out.println("Штрафы: " + entry.getValue());
            System.out.println();
        }
    }

    public void printRecordsByPIN(String pin) {
        List<String> fines = database.get(pin);
        if (fines != null) {
            System.out.println("ПИН: " + pin);
            System.out.println("Штрафы: " + fines);
        } else {
            System.out.println("Запись с таким ПИН не найдена.");
        }
    }

    public void addNewPerson(String pin) {
        database.put(pin, new ArrayList<>());
        System.out.println("Новая запись с ПИН " + pin + " добавлена.");
    }

    public void addFineToPerson(String pin, String fine) {
        List<String> fines = database.getOrDefault(pin, new ArrayList<>());
        fines.add(fine);
        database.put(pin, fines);
        System.out.println("Штраф добавлен для ПИН " + pin);
    }

    public void deleteFine(String pin, String fine) {
        List<String> fines = database.get(pin);
        if (fines != null) {
            fines.remove(fine);
            System.out.println("Штраф удален для ПИН " + pin);
        } else {
            System.out.println("Запись с таким ПИН не найдена.");
        }
    }

    public void replacePersonInfo(String pin, List<String> fines) {
        database.put(pin, fines);
        System.out.println("Информация о человеке и его штрафах обновлена для ПИН " + pin);
    }
}
