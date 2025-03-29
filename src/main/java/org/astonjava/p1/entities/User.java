package org.astonjava.p1.entities;

import org.astonjava.p1.strategy.ComparingStrategyBuilder;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

public class User extends ThreeMemberClass {

  static {
    validationPattern =
        Pattern.compile(
            "^[A-Za-zА-Яа-яёЁ\\s-]{2,50},\\s*[\\w!@#$%^&*()_+-={}|;:'\",.<>?]{8,30},\\s*[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
  }

  public static Comparator<User> member1Comparator =
          Comparator.comparing(User::getName);
  public static Comparator<User> member2Comparator =
          Comparator.comparing(User::getPassword);
  public static Comparator<User> member3Comparator =
          Comparator.comparing(User::getEmail);

  public static String member1RuName = "Имя";
  public static String member2RuName = "Пароль";
  public static String member3RuName = "Почта";

  private String name; // Имя пользователя
  private String password; // Пароль
  private String email; // Электронная почта

  public User(String name, String password, String email) {
    this.name = name;
    this.password = password;
    this.email = email;
  }

  // Конструктор из валидной строки (без проверки)
  public User(String validString) {
    String[] members = validString.split(",");
    this.name = members[0].strip();
    this.password = members[1].strip();
    this.email = members[2].strip();
  }

  // Конструктор по умолчанию со случайными значениями
  public User() {
    Random rand = new Random();
    this.name = generateRandomName();
    this.password = generateRandomPassword();
    this.email = generateRandomEmail();
  }

  private String generateRandomName() {
    String[] firstNames = {
      "Иван",
      "Алексей",
      "Мария",
      "Екатерина",
      "Дмитрий",
      "Анна",
      "Сергей",
      "Ольга",
      "Андрей",
      "Наталья"
    };
    String[] lastNames = {
      "Иванов",
      "Петров",
      "Сидоров",
      "Смирнова",
      "Кузнецов",
      "Васильева",
      "Попов",
      "Новикова",
      "Федоров",
      "Морозова"
    };

    return firstNames[new Random().nextInt(firstNames.length)]
        + " "
        + lastNames[new Random().nextInt(lastNames.length)];
  }

  private String generateRandomPassword() {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
    StringBuilder sb = new StringBuilder();
    Random rand = new Random();

    for (int i = 0; i < 10 + rand.nextInt(11); i++) {
      sb.append(chars.charAt(rand.nextInt(chars.length())));
    }

    return sb.toString();
  }

  private String generateRandomEmail() {
    String[] domains = {"gmail.com", "yandex.ru", "mail.ru", "outlook.com", "yahoo.com"};
    String[] names = {"user", "client", "subscriber", "customer", "member"};

    Random rand = new Random();
    return names[rand.nextInt(names.length)]
        + rand.nextInt(1000)
        + "@"
        + domains[rand.nextInt(domains.length)];
  }

  @Override
  public String toString() {
    return "User {%s, %s, %s}".formatted(name, password, email);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(name, user.name)
        && Objects.equals(password, user.password)
        && Objects.equals(email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, password, email);
  }

  // Геттеры и сеттеры
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // "Билдит" кастомный компаратор
  public static Comparator<User> createSortingStrategy(String[] sortingOrder) {
    ComparingStrategyBuilder<User> builder = new ComparingStrategyBuilder<>();
    Comparator<User> nextBlock = null;
    for (String s : sortingOrder) {
      nextBlock =
              switch (s) {
                case "1" -> member1Comparator;
                case "-1" -> member1Comparator.reversed();
                case "2" -> member2Comparator;
                case "-2" -> member2Comparator.reversed();
                case "3" -> member3Comparator;
                case "-3" -> member3Comparator.reversed();
                default -> nextBlock;
              };
      builder = builder.addComparator(nextBlock);
    }
    return builder.build();
  }

  public static void main(String[] args) {
    User user = new User("Иван Иванов", "Qwerty123!", "ivan@mail.ru");
    System.out.println(user);

    System.out.println(User.validateString("Алексей Петров, P@ssw0rd, alex@yandex.ru"));
    System.out.println(User.validateString("invalid, data, example"));

    User randomUser = new User();
    System.out.println(randomUser);

    System.out.println("------");
    for (int i = 0; i < 15; i++) {
      System.out.println(new User());
    }

    System.out.println("------------------------");
    System.out.println(new User("Мария Сидорова, M@ry123, maria@gmail.com"));
  }
}

