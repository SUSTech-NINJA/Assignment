package exer;

public class Car {
    Calculate calculate;
    String carNumber;

    public Car(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCalculate(Calculate calculate) {
        this.calculate = calculate;
    }

    public int parkingPrice(double hours) {
        return calculate.calculateParkingPrice(hours);
    }

    public int increasePoints(double hours) {
        return calculate.calculatePoints(hours);
    }
}