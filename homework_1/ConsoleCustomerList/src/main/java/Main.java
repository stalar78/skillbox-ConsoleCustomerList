import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger"); // Для логирования ошибок
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);

                switch (tokens[0]) {
                    case "add":
                        executor.addCustomer(tokens[1]);
                        break;
                    case "list":
                        executor.listCustomers();
                        break;
                    case "remove":
                        executor.removeCustomer(tokens[1]);
                        break;
                    case "count":
                        System.out.println("There are " + executor.getCount() + " customers");
                        break;
                    case "help":
                        System.out.println(helpText);
                        break;
                    case "exit":
                        System.out.println("Exiting the program...");
                        return; // Завершение программы
                    default:
                        System.out.println(COMMAND_ERROR);
                }
            } catch (InvalidDataFormatException | InvalidEmailException | InvalidPhoneNumberException e) {
                System.out.println("Ошибка: " + e.getMessage());
                errorLogger.error("Ошибка: " + e.getMessage(), e);
            } catch (Exception e) {
                System.out.println("Неизвестная ошибка: " + e.getMessage());
                errorLogger.error("Неизвестная ошибка: " + e.getMessage(), e);
            }
        }
    }

}
