Цель практической работы
Научиться:

использовать механизм исключений с помощью их создания и «отлова» в коде;
писать конфигурацию для логирования событий при исполнении программы.


Что нужно сделать
Выполните задание в репозитории java_basics в проекте ExceptionsDebuggingAndTesting/homework_1/ConsoleCustomerList.

Проект ConsoleCustomerList — консольное приложение, в котором можно хранить список клиентов. Запустите его, введите в консоль слово help и нажмите Enter. Программа выведет список доступных команд и примеры их выполнения.
Поэкспериментируйте с программой: попробуйте ввести разные значения и команды, которые не будут соответствовать примерам. Добейтесь того, чтобы программа «вылетела», то есть произошёл Exception.
Напишите в классе CustomerStorage проекта ConsoleCustomerList все варианты защиты от некорректных данных, которые вам удастся придумать и обнаружить. Создайте собственные классы исключений, которые должны выбрасываться:
при некорректном количестве компонентов в переданной строке с данными;
при неверном формате номера телефона;
при неправильном формате email.
При создании собственных классов исключений требуется наследовать их от RuntimeException.
Защитите программу от преждевременного завершения метода addCustomer() — напишите код, который будет «отлавливать» исключения и выводить эту информацию в консоль в понятном пользователю виде и в лог (см. ниже).
Подключите библиотеку Log4j2 к проекту:

<dependency>
   <groupId>org.apache.logging.log4j</groupId>
   <artifactId>log4j-api</artifactId>
   <version>2.18.0</version>
</dependency>
<dependency>
   <groupId>org.apache.logging.log4j</groupId>
   <artifactId>log4j-core</artifactId>
   <version>2.18.0</version>
</dependency>
Пример использования библиотеки:

import org.apache.logging.log4j.*;
public class Main {
   private static final Logger logger =
       LogManager.getLogger(Main.class);
   public static void main(String[] args) {
       logger.log(Level.WARN, "Сообщение об ошибке");
   }
}
Сделайте два отдельных лога в папке logs проекта с помощью Log4j2 и настройте конфигурацию библиотеки Log4j следующим образом:
logs/queries.log — заполняется информацией обо всех запросах к приложению;
logs/errors.log — заполняется информацией обо всех ошибках (возникших исключениях со всеми деталями).