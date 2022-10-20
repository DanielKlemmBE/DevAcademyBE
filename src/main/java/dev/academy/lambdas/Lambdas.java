package dev.academy.lambdas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambdas {


  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

  public static final List<User> USERS = List.of(
      User.create(1, "Olaf", "Scholz", "14.06.1958", List.of("deu", "fra", "usa", "rou")),
      User.create(2, "Emmanuel", "Macron", "21.12.1977", List.of("usa", "gbr", "ita")),
      User.create(3, "Joe", "Biden", "20.11.1942", List.of("deu", "rou", "ita")),
      User.create(4, "Boris", "Johnson", "19.06.1964", List.of()),
      User.create(5, "Klaus", "Johannis", "13.06.1959", List.of("deu", "swe", "rou")),
      User.create(7, "Pedro", "Sánchez", "29.02.1972", List.of("fra", "usa", "prt")));

  public static final List<Country> COUNTRIES = List.of(
      Country.create(1, 83240000, "Germany", "deu"),
      Country.create(2, 67390000, "France", "fra"),
      Country.create(3, 329500000, "United States of America", "usa"),
      Country.create(4, 67220000, "United Kingdoms", "gbr"),
      Country.create(5, 12290000, "Romania", "rou"),
      Country.create(6, 59550000, "Italy", "ita")
  );

  public static List<String> sortLeadersByAge() {
    return USERS.stream()
        .sorted(Comparator.comparing(user -> toDate(user.dateOfBirth())))
        .map(user -> user.lastName())
        .collect(Collectors.toList());
  }

  public static int ageInYears(String birthday) {
    return Period.between(LocalDate.parse(birthday), LocalDate.now()).getYears();
  }

  private static Date toDate(String dateOfBirth) {
    try {
      return DATE_FORMAT.parse(dateOfBirth);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

//  Country: Germany
//  Population: 83240000
//  Leader: Olaf Scholz
//  --------------
  public static String countryWithPresidentInfoAsString() {
    return COUNTRIES.stream()
        .map(country -> String.format("Country: %s\nPopulation: %s\nLeader: %s\n",
            country.countryName(),
            country.population(),
            leaderNameOfCountry(country).orElse("not found")))
        .collect(Collectors.joining("--------------\n"));
  }

  public static Optional<String> leaderNameOfCountry(Country country) {
    return USERS.stream()
        .filter(user -> user.userId() == country.leaderId())
        .map(user -> String.format("%s %s", user.firstName(), user.lastName()))
        .findFirst();
  }
}
