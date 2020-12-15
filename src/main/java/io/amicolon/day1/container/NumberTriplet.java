package io.amicolon.day1.container;

public final class NumberTriplet implements NumberContainer
{
    private final Integer firstNumber;
    private final Integer secondNumber;
    private final Integer thirdNumber;

    public NumberTriplet(Integer firstNumber, Integer secondNumber, Integer thirdNumber)
    {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    public NumberTriplet(Integer thirdNumber, NumberPair numberPair)
    {
        this.firstNumber = numberPair.getFirstNumber();
        this.secondNumber = numberPair.getSecondNumber();
        this.thirdNumber = thirdNumber;
    }

    public int product()
    {
        return firstNumber * secondNumber * thirdNumber;
    }

    @Override
    public int sum()
    {
        return firstNumber + secondNumber + thirdNumber;
    }
}
