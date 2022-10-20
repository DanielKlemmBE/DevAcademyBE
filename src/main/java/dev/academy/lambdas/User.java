package dev.academy.lambdas;

import java.util.List;

public class User {

  private final String _firstName;
  private final String _lastName;

  private final String _dateOfBirth;
  private final long _userId;

  private final List<String> _visitedCountries;

  private User(long userId, String firstName, String lastName, String dateOfBirth, List<String> visitedCountries) {
    _firstName = firstName;
    _lastName = lastName;
    _dateOfBirth = dateOfBirth;
    _userId = userId;
    _visitedCountries = visitedCountries;
  }

  public static User create(long userId, String firstName, String lastName, String dateOfBirth, List<String> visitedCountries) {
    return new User(userId, firstName, lastName, dateOfBirth, visitedCountries);
  }

  public String firstName() {
    return _firstName;
  }

  public String lastName() {
    return _lastName;
  }

  public String dateOfBirth() {
    return _dateOfBirth;
  }

  public long userId() {
    return _userId;
  }
}
