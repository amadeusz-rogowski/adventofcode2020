package io.amicolon.day1;

public class NumberTriplet {

    private final Integer firstNumber;
    private final Integer secondNumber;
    private final Integer thirdNumber;

    public NumberTriplet(Integer firstNumber, Integer secondNumber, Integer thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    public int product() {
        return firstNumber * secondNumber * thirdNumber;
    }
}
