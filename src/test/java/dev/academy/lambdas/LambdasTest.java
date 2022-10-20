package dev.academy.lambdas;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

class LambdasTest {
  private static final String EXPECTED_COUNTRIES = "Country: Germany\n" +
      "Population: 83240000\n" +
      "Leader: Olaf Scholz\n" +
      "--------------\n" +
      "Country: France\n" +
      "Population: 67390000\n" +
      "Leader: Emmanuel Macron\n" +
      "--------------\n" +
      "Country: United States of America\n" +
      "Population: 329500000\n" +
      "Leader: Joe Biden\n" +
      "--------------\n" +
      "Country: United Kingdoms\n" +
      "Population: 67220000\n" +
      "Leader: Boris Johnson\n" +
      "--------------\n" +
      "Country: Romania\n" +
      "Population: 12290000\n" +
      "Leader: Klaus Johannis\n" +
      "--------------\n" +
      "Country: Italy\n" +
      "Population: 59550000\n" +
      "Leader: not found\n";
  private static final List<String> EXPECTED_LEADERS = List.of("Biden", "Scholz", "Johannis", "Johnson", "Sánchez", "Macron");

  /**
   * Country: Germany
   * Population: 83240000
   * Leader: Olaf Scholz
   * --------------
   */
  @Test
  public void testCountryWithPresidentInfo() {
    assertThat(EXPECTED_COUNTRIES)
        .isEqualTo(Lambdas.countryWithPresidentInfoAsString());
  }

  private static Date toDate(String dateOfBirth) {
    try {
      return Lambdas.DATE_FORMAT.parse(dateOfBirth);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void execTest() {
    List<String> agesInYears = List.of("2000-01-01", "1994-01-14", "1978-02-02");
    agesInYears.stream()
        .filter(birthday -> !birthday.equals("2000-01-01"))
        .map(Lambdas::ageInYears)
        .peek(age -> System.err.println(age))
        .collect(Collectors.toList());
  }

  @Test
  public void testSortLeadersByAge() {
    ArrayList<User> users = new ArrayList<>(Lambdas.USERS);
    Comparator<User> comparator = new Comparator<User>() {
      @Override
      public int compare(User o1, User o2) {
        Date dateOfBirthUser1 = toDate(o1.dateOfBirth());
        Date dateOfBirthUser2 = toDate(o2.dateOfBirth());
        return dateOfBirthUser1.compareTo(dateOfBirthUser2);
      }
    };
    Collections.sort(users, comparator);

    ArrayList<String> sortedLeaderNames = new ArrayList<>();
    for (User user : users) {
      sortedLeaderNames.add(user.lastName());
    }
    assertThat(EXPECTED_LEADERS)
        .isEqualTo(sortedLeaderNames)
        .isEqualTo(Lambdas.sortLeadersByAge());
  }
}