package io.amicolon.day2;

import java.util.Arrays;
import java.util.regex.Matcher;

public final class PasswordWithPasswordPolicy
{
    private final int symbolMinOccurances;
    private final int symbolMaxOccurances;
    private final String symbol;
    private final String password;

    public PasswordWithPasswordPolicy(Matcher matcher)
    {
        matcher.find();

        this.symbolMinOccurances = Integer.parseInt(matcher.group("minOccurences"));
        this.symbolMaxOccurances = Integer.parseInt(matcher.group("maxOccurences"));
        this.symbol = matcher.group("symbol");
        this.password = matcher.group("password");
    }

    public boolean isPasswordValid()
    {
        long count = Arrays.stream(password.split(""))
                .filter(character -> character.equals(symbol))
                .count();

        return count >= symbolMinOccurances && count <= symbolMaxOccurances;
    }
}
