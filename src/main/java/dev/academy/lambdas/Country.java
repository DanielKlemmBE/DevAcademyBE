package dev.academy.lambdas;

public class Country {

    private final String _countryName;
    private final String _isoCode;

    private final long _population;
    private final long _leaderId;

    private Country(long leaderId, long population, String countryName, String isoCode) {
        _isoCode = isoCode;
        _countryName = countryName;
        _population = population;
        _leaderId = leaderId;
    }

    public static Country create(long leaderId, long population, String countryName, String isoCode) {
        return new Country(leaderId, population, countryName, isoCode);
    }

    public String countryName() {
        return _countryName;
    }

    public String isoCode() {
        return _isoCode;
    }

    public long population() {
        return _population;
    }

    public long leaderId() {
        return _leaderId;
    }
}
