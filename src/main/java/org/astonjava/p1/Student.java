package org.astonjava.p1;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

public class Student extends ThreeMemberClass {

  static {
    validationPattern = Pattern.compile("^\\d{1,6},\\s*\\d{1,3}[A-Za-z]?,\\s*[A-Za-z]{2}\\d{6}$");
  }

  public static Comparator<Student> member1Comparator =
      Comparator.comparing(Student::getRecordBookNumber);
  public static Comparator<Student> member2Comparator =
      Comparator.comparing(Student::getGroupNumber);
  public static Comparator<Student> member3Comparator =
      Comparator.comparing(Student::getAverageGrade);

  public static String member1RuName = "Номер зачетной книжки";
  public static String member2RuName = "Номер группы";
  public static String member3RuName = "Средний балл";

  private String recordBookNumber; // Номер зачетной книжки (формат: буквы+цифры)
  private String groupNumber; // Номер группы (может содержать цифры и буквы)
  private double averageGrade; // Средний балл

  public Student(String recordBookNumber, String groupNumber, double averageGrade) {
    this.recordBookNumber = recordBookNumber;
    this.groupNumber = groupNumber;
    this.averageGrade = averageGrade;
  }

  // Конструктор из валидной строки (без проверки)
  public Student(String validString) {
    String[] members = validString.split(",");
    this.recordBookNumber = members[0].strip();
    this.groupNumber = members[1].strip();
    this.averageGrade = Double.parseDouble(members[2].strip());
  }

  // Конструктор по умолчанию со случайными значениями
  public Student() {
    Random rand = new Random();

    // Генерация номера зачетной книжки (2 буквы + 6 цифр)
    this.recordBookNumber = generateRandomBookNumber();

    // Генерация номера группы (1-3 цифры + возможна 1 буква)
    this.groupNumber = generateRandomGroupNumber();

    // Генерация среднего балла (от 2.0 до 5.0)
    this.averageGrade = 2.0 + (3.0 * rand.nextDouble());
    this.averageGrade =
        Math.round(this.averageGrade * 10) / 10.0; // Округление до 1 знака после запятой
  }

  private String generateRandomBookNumber() {
    Random rand = new Random();
    StringBuilder sb = new StringBuilder();
    // 2 случайные буквы
    sb.append((char) ('A' + rand.nextInt(26)));
    sb.append((char) ('A' + rand.nextInt(26)));
    // 6 случайных цифр
    for (int i = 0; i < 6; i++) {
      sb.append(rand.nextInt(10));
    }
    return sb.toString();
  }

  private String generateRandomGroupNumber() {
    Random rand = new Random();
    StringBuilder sb = new StringBuilder();
    // 1-3 цифры
    int digits = 1 + rand.nextInt(3);
    for (int i = 0; i < digits; i++) {
      sb.append(rand.nextInt(10));
    }
    // Возможна 1 буква (50% chance)
    if (rand.nextBoolean()) {
      sb.append((char) ('A' + rand.nextInt(26)));
    }
    return sb.toString();
  }

  @Override
  public String toString() {
//    return "Student {Номер зачетки: %s, Группа: %s, Средний балл: %.1f}"
    return "%s, %s, %.1f"
    .formatted(recordBookNumber, groupNumber, averageGrade);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Double.compare(student.averageGrade, averageGrade) == 0
        && Objects.equals(recordBookNumber, student.recordBookNumber)
        && Objects.equals(groupNumber, student.groupNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recordBookNumber, groupNumber, averageGrade);
  }

  //   Геттеры и сеттеры
  public String getRecordBookNumber() {
    return recordBookNumber;
  }

  public void setRecordBookNumber(String recordBookNumber) {
    this.recordBookNumber = recordBookNumber;
  }

  public String getGroupNumber() {
    return groupNumber;
  }

  public void setGroupNumber(String groupNumber) {
    this.groupNumber = groupNumber;
  }

  public double getAverageGrade() {
    return averageGrade;
  }

  public void setAverageGrade(double averageGrade) {
    this.averageGrade = averageGrade;
  }

  // "Билдит" кастомный компаратор
  public static Comparator<Student> createSortingStrategy(String[] sortingOrder) {
    ComparingStrategyBuilder<Student> builder = new ComparingStrategyBuilder<>();
    Comparator<Student> nextBlock = null;
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
      builder.addComparator(nextBlock);
    }
    return builder.build();
  }

  // Тестовый main
  public static void main(String[] args) {

    Student student = new Student("AB123456", "123A", 4.5);
    System.out.println(student);

    System.out.println(Student.validateString("CD654321, 456B, 3.8"));
    System.out.println(Student.validateString("PL711851, 79K, 4.1"));
    System.out.println(Student.validateString("Invalid, Data, Example"));

    Student randomStudent = new Student();
    System.out.println(randomStudent);

    System.out.println("------");
    for (int i = 0; i < 15; i++) {
      System.out.println(new Student());
    }

    System.out.println("------------------------");
    System.out.println(new Student("EF987654, 789C, 4.2"));
  }
}
