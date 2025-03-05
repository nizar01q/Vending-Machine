package com.progressoft.samples;

import java.util.Objects;

public class Money {
    public static final Money Zero = new Money(0);
    public static final Money OnePiaster = new Money(0.01);
    public static final Money FivePiasters = new Money(0.05);
    public static final Money TenPiasters = new Money(0.1);
    public static final Money TwentyFivePiasters = new Money(0.25);
    public static final Money FiftyPiasters = new Money(0.50);
    public static final Money OneDinar = new Money(1.00);
    public static final Money FiveDinars = new Money(5.00);
    public static final Money TenDinars = new Money(10.00);
    public static final Money TwentyDinars = new Money(20.00);
    public static final Money FiftyDinars = new Money(50.00);
    private final double amount;

    public Money(double amount) {
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public Money times(int count) {
        if(count<0){
            throw new IllegalArgumentException("Multiplication count cannot be negative");
        }

        return new Money(this.amount * count);
    }

    public static Money sum(Money... items) {
        double total = 0;

        for (Money item:items){
            total += item.amount();
        }

        return new Money(total);
    }

    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money minus(Money other) {
        if(this.amount < other.amount){
            throw new IllegalArgumentException("Cannot have negative money");
        }

        return new Money(this.amount - other.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return Double.compare(money.amount, this.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return String.format("%.2f", amount);
    }
}

