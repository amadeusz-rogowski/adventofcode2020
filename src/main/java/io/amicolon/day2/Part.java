package io.amicolon.day2;

import java.util.function.Predicate;

public enum Part
{
    PART_A(PasswordWithPasswordPolicy::isPasswordValidA),
    PART_B(PasswordWithPasswordPolicy::isPasswordValidB);

    private final Predicate<PasswordWithPasswordPolicy> strategy;

    Part(Predicate<PasswordWithPasswordPolicy> strategy)
    {
        this.strategy = strategy;
    }

    public Predicate<PasswordWithPasswordPolicy> getStrategy()
    {
        return strategy;
    }
}
