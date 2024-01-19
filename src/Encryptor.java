import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Encryptor {
    
	private Scanner scanner;
	private ArrayList<Character> list;
	private ArrayList<Character> key;
	private char character;
	private char[] letters;
	
	/*
	 * Конструктор для шифратора
	 * Encryptor constructor
	 */	
		Encryptor() {
		scanner = new Scanner(System.in);
		list = new ArrayList();
		key = new ArrayList();
		
		/*
		 * Заполняем лист значениями из ASCII таблицы (Основные символы, английский алфавит).
		 * Filling a list with ASCII elements (Common symbols, English alphabet).
		 */	

		character = ' ';
		
		for (int i = 32; i < 127; i++) {
			list.add(Character.valueOf(character));
			character++;
		}
		askQuestion();
	}
	
	private void askQuestion(){
		/*
		 * Приветственный метод, для выбора действия
		 * Welcome method, choosing what to do next
		 */
			while (true) {
			System.out.println("-------------------------------------------");
			System.out.println("Выберите действие / Choose whan you want to do:");
			System.out.println("1. Сгенерировать новый ключ шифрования / Generate new encryption key;");
			System.out.println("2. Вывести ключ на экран / Print existing key in console;"); 
			System.out.println("3. Зашифровать послание / Encrypt the message;"); 
			System.out.println("4. Расшифровать послание / Decrypt the message;");
			System.out.println("5. Выход / Exit;");
			String response = scanner.nextLine();
			switch (response) {
			case "1":
				newKey();
				break;
			case "2":
				getKey();
				break;
			case "3":
				encrypt();
				break;
			case "4":
				decrypt();
				break;
			case "5":
				quit();
				break;
			default:
				System.out.println("Варианта " + response + " нет в меню, пожалуйста выберите действие от 1 до 5.\n"
						+ "There is no option such as " + response + " in menu. Please choose option 1 to 5.");
			}
		}
	}
	private void newKey() {
		
		/*  
		 * Очищаем всю информацию о старых ключах
		 * Clearing an information about existing key
		 */
		
		character = ' ';
		key.clear();	
		
		key = new ArrayList(list);
		Collections.shuffle(key);
		System.out.println("Ключ был сгенерирован / Key was generated.");
		
	}
	private void getKey() {
		
		/* 
		 * Вывод сгенерированного ключа на экран
		 * Printing generated key in console
		 * */
		
		System.out.println("Ключ / Key:");
		for (Character x : key) {
			System.out.print(x);
		}
		System.out.println();
		System.out.println("===========================        =============================\n" +
				   "НЕ ЗАБУДЬТЕ СОХРАНИТЬ КЛЮЧ!        DON'T FORGET TO SAVE THE KEY!\n" +
				   "===========================        =============================");
	}
	
	private void insertKey() {
		
		key.clear();
		System.out.println("Вставьте ключ / Paste a key:");
		char[] response = (scanner.nextLine()).toCharArray();
		int length = response.length;
		for (int i = 0; i < length; i++) {
			key.add(response[i]);
		}
	}
	
	private void encrypt() {
		
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
	private void decrypt() {
		
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
		System.out.println("Расшифрованное сообщение / Encrypted message: ");
		for (char x : letters) {
			System.out.print(x);
		}
		System.out.println();
		
	}
	private void quit() {
		System.out.println("Спасибо, что воспользовался моей программой 😀\n" +
						   "Можешь найти меня в телеграмме @hhzktoeto\n");
		System.out.println("Thank for using my programm 😀\n" +
				   		   "Feel free to contact me on Telegramm @hhzktoeto\n");
		System.exit(0);
	}
}