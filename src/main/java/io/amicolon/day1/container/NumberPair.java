package io.amicolon.day1.container;

public final class NumberPair implements NumberContainer
{
    private final Integer firstNumber;
    private final Integer secondNumber;

    public NumberPair(Integer firstNumber, Integer secondNumber)
    {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public Integer getFirstNumber()
    {
        return firstNumber;
    }

    public Integer getSecondNumber()
    {
        return secondNumber;
    }

    public int product()
    {
        return firstNumber * secondNumber;
    }

    @Override
    public int sum()
    {
        return firstNumber + secondNumber;
    }
}
