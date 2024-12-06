import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private static final Logger queryLogger = LogManager.getLogger("QueryLogger");

    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int EXPECTED_COMPONENTS = 4;

        String[] components = data.split("\\s+");

        if (components.length != EXPECTED_COMPONENTS) {
            throw new InvalidDataFormatException(
                    "Invalid number of components in input data. Expected 4 but got " + components.length
            );
        }

        String name = components[0] + " " + components[1];
        String email = components[2];
        String phone = components[3];

        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }

        if (!isValidPhone(phone)) {
            throw new InvalidPhoneNumberException("Invalid phone format: " + phone);
        }

        storage.put(name, new Customer(name, phone, email));
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("^\\+\\d{11}$");
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
        queryLogger.info("Listed all customers");
    }

    public void removeCustomer(String name) {
        if (storage.remove(name) != null) {
            queryLogger.info("Customer removed: " + name);
        } else {
            queryLogger.info("Customer not found for removal: " + name);
        }
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}
