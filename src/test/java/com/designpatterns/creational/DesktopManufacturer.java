package com.designpatterns.creational;

public class DesktopManufacturer extends ComputerManufacturer {
    @Override
    public String addHardDisk() {
        return "Desktop now has a SSD\n";
    }

    @Override
    public String addRAM() {
        return "Desktop now has a DIMM RAM\n";
    }

    @Override
    public String addKeyboard() {
        return "Desktop now has an accessory keyboard\n";
    }
}
