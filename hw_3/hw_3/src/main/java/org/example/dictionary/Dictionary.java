package org.example.dictionary;

import java.util.*;

public class Dictionary implements Runnable {
    Map<String, List<String>> dictionary = new HashMap<>();
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить перевод");
            System.out.println("2. Поиск переводов");
            System.out.println("3. Добавить или обновить слово");
            System.out.println("4. Удалить перевод");
            System.out.println("5. Удалить слово");
            System.out.println("6. Показать топ-10 популярных слов");
            System.out.println("7. Показать топ-10 непопулярных слов");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки

            switch (choice) {
                case 1:
                    addTranslation(dictionary, scanner);
                    break;
                case 2:
                    searchTranslations(dictionary, scanner);
                    break;
                case 3:
                    addOrUpdateWord(dictionary, scanner);
                    break;
                case 4:
                    removeTranslation(dictionary, scanner);
                    break;
                case 5:
                    removeWord(dictionary, scanner);
                    break;
                case 6:
                    showTopWords(dictionary, true);
                    break;
                case 7:
                    showTopWords(dictionary, false);
                    break;
                case 0:
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }



    private static void addTranslation(Map<String, List<String>> dictionary, Scanner scanner) {
        String word = scanner.nextLine();
        System.out.print("Введите перевод на испанский: ");
        String translation = scanner.nextLine();

        List<String> translations = dictionary.getOrDefault(word, new ArrayList<>());
        translations.add(translation);
        dictionary.put(word, translations);  System.out.print("Введите слово на английском: ");

    }

    private static void searchTranslations(Map<String, List<String>> dictionary, Scanner scanner) {
        System.out.print("Введите слово на английском: ");
        String word = scanner.nextLine();

        List<String> translations = dictionary.get(word);
        if (translations != null) {
            System.out.println("Переводы слова '" + word + "': " + translations);
        } else {
            System.out.println("Слово '" + word + "' не найдено в словаре.");
        }
    }

    private static void addOrUpdateWord(Map<String, List<String>> dictionary, Scanner scanner) {
        System.out.print("Введите слово на английском: ");
        String word = scanner.nextLine();
        System.out.print("Введите перевод на испанский: ");
        String translation = scanner.nextLine();

        List<String> translations = new ArrayList<>();
        translations.add(translation);
        dictionary.put(word, translations);
    }

    private static void removeTranslation(Map<String, List<String>> dictionary, Scanner scanner) {
        System.out.print("Введите слово на английском: ");
        String word = scanner.nextLine();
        System.out.print("Введите перевод на испанский, который нужно удалить: ");
        String translation = scanner.nextLine();

        List<String> translations = dictionary.get(word);
        if (translations != null && translations.contains(translation)) {
            translations.remove(translation);
            System.out.println("Перевод удален.");
        } else {
            System.out.println("Перевод не найден.");
        }
    }

    private static void removeWord(Map<String, List<String>> dictionary, Scanner scanner) {
        System.out.print("Введите слово на английском, которое нужно удалить: ");
        String word = scanner.nextLine();

        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
            System.out.println("Слово удалено.");
        } else {
            System.out.println("Слово не найдено.");
        }
    }

    private static void showTopWords(Map<String, List<String>> dictionary, boolean popular) {
        Comparator<Map.Entry<String, List<String>>> comparator = Comparator.comparingInt(entry -> entry.getValue().size());
        if (!popular) {
            comparator = comparator.reversed();
        }

        List<Map.Entry<String, List<String>>> entries = new ArrayList<>(dictionary.entrySet());
        entries.sort(comparator);

        System.out.println("Топ-10 " + (popular ? "популярных" : "непопулярных") + " слов:");
        for (int i = 0; i < Math.min(10, entries.size()); i++) {
            System.out.println(entries.get(i).getKey() + ": " + entries.get(i).getValue().size() + " перевод(ов)");
        }
    }


}

