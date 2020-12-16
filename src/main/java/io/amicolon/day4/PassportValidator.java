package io.amicolon.day4;

import io.amicolon.common.Pair;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public final class PassportValidator
{
    public static boolean validateLine(String line, Validation validation)
    {
        String[] properties = line.split(" ");

        Map<String, String> propertiesMap = Arrays.stream(properties)
                .map(property -> property.split(":"))
                .map(property -> new Pair<>(property[0], property[1]))
                .filter(property -> !property.getK().equals("cid"))
                .collect(toMap(Pair::getK, Pair::getV));

        return validation.getValidator().test(propertiesMap);
    }

    static boolean basicValidation(Map<String, String> propertiesMap)
    {
        return propertiesMap.size() == 7;
    }

    static boolean extendedValidation(Map<String, String> propertiesMap)
    {
        if (basicValidation(propertiesMap))
        {
            return propertiesMap.entrySet().stream()
                    .map(PassportValidator::validationResult)
                    .reduce(Boolean.TRUE, Boolean::logicalAnd);
        }
        else
        {
            return false;
        }
    }

    static boolean validationResult(Map.Entry<String, String> entry)
    {
        final PropertyValidation validation = Arrays.stream(PropertyValidation.values())
                .filter(e -> e.key.equals(entry.getKey()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        return validation.getPredicate().test(entry);
    }

    enum PropertyValidation
    {
        BIRTH_YEAR("byr", yearEntry -> isYearValid(yearEntry, 1920, 2002)),
        ISSUE_YEAR("iyr", yearEntry -> isYearValid(yearEntry, 2010, 2020)),
        EXPIRATION_YEAR("eyr", yearEntry -> isYearValid(yearEntry, 2020, 2030)),
        HEIGHT("hgt", PropertyValidation::isHeightValid),
        HAIR_COLOR("hcl", PropertyValidation::isHairColorValid),
        EYE_COLOR("ecl", PropertyValidation::isEyeColorValid),
        PASSPORT_ID("pid", PropertyValidation::isPassportIdValid);

        private final String key;

        private final Predicate<Map.Entry<String, String>> predicate;

        PropertyValidation(String key, Predicate<Map.Entry<String, String>> predicate)
        {
            this.key = key;
            this.predicate = predicate;
        }

        public Predicate<Map.Entry<String, String>> getPredicate()
        {
            return predicate;
        }

        private static boolean isYearValid(Map.Entry<String, String> yearEntry, int lowerBound, int upperBound)
        {
            final String year = yearEntry.getValue();
            final int yearAsInt = Integer.parseInt(year);

            return year.length() == 4 && isNumberInRange(yearAsInt, lowerBound, upperBound);
        }

        private static boolean isNumberInRange(int number, int lowerBound, int upperBound)
        {
            return lowerBound <= number && number <= upperBound;
        }

        private static boolean isHeightValid(Map.Entry<String, String> heightEntry)
        {
            String height = heightEntry.getValue();

            Pattern pattern = Pattern.compile("^(?<heightValue>\\d{2,3})(?<unit>in|cm)$");
            Matcher matcher = pattern.matcher(height);

            if (matcher.find())
            {
                final int heightValue = Integer.parseInt(matcher.group("heightValue"));
                final String unit = matcher.group("unit");

                if (unit.equals("cm"))
                {
                    return 150 <= heightValue && heightValue <= 193;
                }
                else if (unit.equals("in"))
                {
                    return 59 <= heightValue && heightValue <= 76;
                }
            }

            return false;
        }

        private static boolean isHairColorValid(Map.Entry<String, String> hairColorEntry)
        {
            String hairColor = hairColorEntry.getValue();

            return hairColor.matches("^#[a-f\\d]{6}$");
        }

        private static boolean isEyeColorValid(Map.Entry<String, String> eyeColorEntry)
        {
            final Set<String> colors = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

            return colors.contains(eyeColorEntry.getValue());
        }

        private static boolean isPassportIdValid(Map.Entry<String, String> passportIdEntry)
        {
            String passportId = passportIdEntry.getValue();

            return passportId.matches("\\d{9}$");
        }
    }

}