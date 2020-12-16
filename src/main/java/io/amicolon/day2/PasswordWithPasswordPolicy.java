package io.amicolon.day2;

import java.util.Arrays;
import java.util.regex.Matcher;

public final class PasswordWithPasswordPolicy
{
    private final int firstDigit;
    private final int secondDigit;
    private final String symbol;
    private final String password;

    public PasswordWithPasswordPolicy(Matcher matcher)
    {
        matcher.find();

        this.firstDigit = Integer.parseInt(matcher.group("firstDigit"));
        this.secondDigit = Integer.parseInt(matcher.group("secondDigit"));
        this.symbol = matcher.group("symbol");
        this.password = matcher.group("password");
    }

    public boolean isPasswordValidA()
    {
        long count = Arrays.stream(password.split(""))
                .filter(character -> character.equals(symbol))
                .count();

        return count >= firstDigit && count <= secondDigit;
    }

    public boolean isPasswordValidB()
    {
        final char symbol = this.symbol.charAt(0);

        boolean firstSymbolMatches = isSymbolInPasswordOnPosition(symbol, firstDigit);
        boolean secondSymbolMatches = isSymbolInPasswordOnPosition(symbol, secondDigit);

        return firstSymbolMatches != secondSymbolMatches;
    }

    private boolean isSymbolInPasswordOnPosition(char symbol, int firstDigit)
    {
        return password.charAt(firstDigit - 1) == symbol;
    }
}
