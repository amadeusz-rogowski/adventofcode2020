package io.amicolon.day4;

import java.util.Map;
import java.util.function.Predicate;

public enum Validation
{
    BASIC(PassportValidator::basicValidation),
    EXTENDED(PassportValidator::extendedValidation);

    private final Predicate<Map<String, String>> validator;

    Validation(Predicate<Map<String, String>> predicate)
    {
        this.validator = predicate;
    }

    public Predicate<Map<String, String>> getValidator()
    {
        return validator;
    }
}
