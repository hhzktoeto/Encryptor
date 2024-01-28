import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Utils {

    private static Scanner scanner;
    private static ArrayList<Character> list;
    private static ArrayList<Character> key;
    private static char character;
    private static char[] letters;

    protected static void initialize() {
        createKeyLists();
        letUserChoose();
    }

    private static void createKeyLists() {

        scanner = new Scanner(System.in);
        list = new ArrayList<>();
        key = new ArrayList<>();

        character = ' ';

        // ASCII table from 32 to 126 (English alphabet, common symbols)
        for (int i = 32; i < 127; i++) {
            list.add(character);
            character++;
        }
    }

    private static void letUserChoose() {

        while (true) {
            printInfo();
            String response = scanner.nextLine();
            switch (response) {
                case "1":
                    newKey();
                    break;
                case "2":
                    getKey();
                    break;
                case "3":
                    encryptAndPrint();
                    break;
                case "4":
                    decryptAndPrint();
                    break;
                case "5":
                    quit();
                    break;
                default:
                    System.out.printf("""
                            Варианта "%s" нет в меню, пожалуйста выберите действие от 1 до 5.
                                                        
                            There is no option such as "%s" in menu. Please choose option 1 to 5.
                            """, response, response);
            }
        }
    }

    private static void printInfo() {
        System.out.println("-------------------------------------------");
        System.out.println("Выберите действие / Choose what you want to do:");
        System.out.println("1. Сгенерировать новый ключ шифрования / Generate new encryption key;");
        System.out.println("2. Вывести ключ на экран / Print existing key in console;");
        System.out.println("3. Зашифровать послание / Encrypt the message;");
        System.out.println("4. Расшифровать послание / Decrypt the message;");
        System.out.println("5. Выход / Exit;");
    }

    private static void newKey() {

        character = ' ';
        key.clear();

        key = new ArrayList<>(list);
        Collections.shuffle(key);
        System.out.println("Ключ был сгенерирован / Key was generated.");

    }

    private static void getKey() {

        System.out.println("Ключ / Key:");
        for (Character x : key) {
            System.out.print(x);
        }
        System.out.println();
        System.out.println("""
                ===========================        =============================
                НЕ ЗАБУДЬТЕ СОХРАНИТЬ КЛЮЧ!        DON'T FORGET TO SAVE THE KEY!
                ===========================        =============================""");

    }

    private static void insertKey() {

        key.clear();
        System.out.println("Вставьте ключ / Paste a key:");
        char[] response = (scanner.nextLine()).toCharArray();
        int length = response.length;
        for (char c : response) {
            key.add(c);
        }
    }

    private static void encryptAndPrint() {

        System.out.println("Введите сообщение / Enter a message");
        String message = scanner.nextLine();

        letters = message.toCharArray();
        int length = letters.length;
        int size = list.size();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < size; j++) {
                if (letters[i] == list.get(j)) {
                    letters[i] = key.get(j);
                    break;
                }
            }
        }
        System.out.println("Зашифрованное сообщение / Encrypted message:");
        for (char x : letters) {
            System.out.print(x);
        }
        System.out.println();

    }

    private static void decryptAndPrint() {

        insertKey();

        System.out.println("Вставьте зашифрованное сообщение / Paste an encrypted message:");
        String message = scanner.nextLine();

        letters = message.toCharArray();
        int length = letters.length;
        int size = key.size();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < size; j++) {
                if (letters[i] == key.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        System.out.println("Расшифрованное сообщение / Encrypted message:");
        for (char x : letters) {
            System.out.print(x);
        }
        System.out.println();

    }

    private static void quit() {
        System.out.println("""
                Спасибо, что воспользовался моей программой 😀
                 Можешь найти меня в телеграмме @hhzktoeto
                """);
        System.out.println("""
                        Thanks for using my programm 😀
                Feel free to contact me on Telegramm @hhzktoeto
                """);
        System.exit(0);
    }
}
