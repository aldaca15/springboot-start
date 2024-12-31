package com.designpatterns.creational;

public class LaptopManufacturer extends ComputerManufacturer {
    @Override
    public String addHardDisk() {
        return "Laptop now has a M.2 storage unit\n";
    }

    @Override
    public String addRAM() {
        return "Laptop now has a SO-DIMM RAM\n";
    }

    @Override
    public String addKeyboard() {
        return "Laptop now has an integrated keyboard\n";
    }
}
