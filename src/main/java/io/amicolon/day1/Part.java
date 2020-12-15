package io.amicolon.day1;

import io.amicolon.day1.container.NumberContainer;
import io.amicolon.day1.container.NumberPair;
import io.amicolon.day1.container.NumberTriplet;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.Integer.sum;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public enum Part
{
    PART_1(Part::getNumberPairWhichSumTo2020),
    PART_2(Part::getNumberTripleWhichSumTo2020);

    private final Function<Set<? extends Integer>, NumberContainer> retrieveNumberContainerStrategy;

    Part(Function<Set<? extends Integer>, NumberContainer> strategy)
    {
        this.retrieveNumberContainerStrategy = strategy;
    }

    public Function<Set<? extends Integer>, NumberContainer> getRetrieveNumberContainerStrategy()
    {
        return retrieveNumberContainerStrategy;
    }

    private static NumberContainer getNumberPairWhichSumTo2020(Set<? extends Integer> numbers)
    {
        return numbers.stream()
                .map(num -> mapToNumberPairOrNull(num, numbers))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static NumberContainer mapToNumberPairOrNull(Integer number, Set<? extends Integer> numbers)
    {
        return numbers.stream()
                .filter(potentialAddend -> !potentialAddend.equals(number))
                .filter(potentialAddend -> sum(potentialAddend, number) == 2020)
                .map(addend -> new NumberPair(number, addend))
                .findAny()
                .orElse(null);
    }

    private static NumberContainer getNumberTripleWhichSumTo2020(Set<? extends Integer> firstAddends)
    {
        for (Integer firstAddend : firstAddends)
        {
            Set<Integer> secondAddends = firstAddends.stream().filter(num -> !num.equals(firstAddend)).collect(toSet());
            NavigableSet<Integer> thirdAddends = secondAddends.stream().sorted(comparingInt(Integer::intValue).reversed()).collect(toCollection(TreeSet::new));

            Stream<NumberPair> numberPairStream = getNumberPairStream(secondAddends, thirdAddends);

            NumberContainer numberContainer = numberPairStream
                    .filter(numberPair -> numberPair.sum() + firstAddend == 2020)
                    .map(numberPair -> NumberTriplet.newNumberTriplet(firstAddend, numberPair))
                    .findAny()
                    .orElse(null);

            if (numberContainer != null)
            {
                return numberContainer;
            }
        }

        throw new IllegalStateException("Couldn't find any 3 numbers which sum up to 2020");
    }

    private static Stream<NumberPair> getNumberPairStream(Set<Integer> secondAddends, NavigableSet<Integer> thirdAddends)
    {
        Stream.Builder<Stream<NumberPair>> streamBuilder = Stream.builder();

        for (Integer secondAddend : secondAddends)
        {
            thirdAddends.pollLast();
            Stream.Builder<NumberPair> numberPairStreamBuilder = Stream.builder();

            for (Integer thirdAddend : thirdAddends)
            {
                if (secondAddend + thirdAddend <= 2020)
                {
                    numberPairStreamBuilder.add(new NumberPair(secondAddend, thirdAddend));
                }
            }

            Stream<NumberPair> numberPairs = numberPairStreamBuilder.build();
            streamBuilder.add(numberPairs);
        }

        return streamBuilder.build().flatMap(Function.identity());
    }

}
