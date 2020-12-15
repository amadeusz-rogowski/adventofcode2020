package day1;

final class NumberPair {

    private final Integer firstNumber;
    private final Integer secondNumber;

    public NumberPair(Integer firstNumber, Integer secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int product() {
        return firstNumber * secondNumber;
    }
}
