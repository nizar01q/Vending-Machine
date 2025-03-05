package com.progressoft.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private final Map<Money, Integer> cashInventory = new HashMap<>();

    public VendingMachine() {
        initializeInventory();
    }

    private void initializeInventory() {
        cashInventory.put(Money.OnePiaster, 2);
        cashInventory.put(Money.FivePiasters, 0);
        cashInventory.put(Money.TenPiasters, 10);
        cashInventory.put(Money.TwentyFivePiasters, 10);
        cashInventory.put(Money.FiftyPiasters, 3);
        cashInventory.put(Money.OneDinar, 0);
        cashInventory.put(Money.FiveDinars, 1);
        cashInventory.put(Money.TenDinars, 2);
        cashInventory.put(Money.TwentyDinars, 2);
        cashInventory.put(Money.FiftyDinars, 1);
    }

    public boolean canGiveChange(Money changeAmount) {
        double remaining = changeAmount.amount();
        Map<Money, Integer> tempInventory = new HashMap<>(cashInventory);

        List<Money> denominations = new ArrayList<>(cashInventory.keySet());
        denominations.sort((a, b) -> Double.compare(b.amount(), a.amount()));

        for (Money denomination : denominations) {
            while (remaining >= denomination.amount() && tempInventory.get(denomination) > 0) {
                remaining -= denomination.amount();
                tempInventory.put(denomination, tempInventory.get(denomination) - 1);
            }
        }

        return remaining == 0;
    }

    public boolean purchaseItem(Money itemPrice, Money payment) {
        if (payment.amount() < itemPrice.amount()) {
            System.out.println("Insufficient payment.");
            return false;
        }

        Money change = payment.minus(itemPrice);
        if (!canGiveChange(change)) {
            System.out.println("Cannot provide exact change.");
            return false;
        }

        updateInventoryForChange(change);

        System.out.println("Purchase successful! Change given: " + change);
        return true;
    }

    private void updateInventoryForChange(Money change) {
        List<Money> denominations = new ArrayList<>(cashInventory.keySet());
        denominations.sort((a, b) -> Double.compare(b.amount(), a.amount()));

        for (Money denomination : denominations) {
            while (change.amount() >= denomination.amount() && cashInventory.get(denomination) > 0) {
                cashInventory.put(denomination, cashInventory.get(denomination) - 1);
                change = change.minus(denomination);
            }
        }
    }
}
