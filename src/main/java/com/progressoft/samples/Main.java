package com.progressoft.samples;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // Case 1: Successful purchase with exact change available
        machine.purchaseItem(Money.FiveDinars, Money.TenDinars);

        // Case 2: Successful purchase with no change required
        machine.purchaseItem(Money.TenDinars, Money.FiveDinars.plus(Money.FiveDinars));

        // Case 3: Insufficient payment
        machine.purchaseItem(Money.TenDinars, Money.FiveDinars);

        // Case 4: Cannot provide change
        machine.purchaseItem(Money.OnePiaster, Money.FivePiasters);

        // Case 5: Cannot provide change
        machine.purchaseItem(Money.FiveDinars, Money.TenDinars);
    }
}
