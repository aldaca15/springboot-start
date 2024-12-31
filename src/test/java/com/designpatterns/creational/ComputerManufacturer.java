package com.designpatterns.creational;

public abstract class ComputerManufacturer {

    public String buildComputer() {
        String computerBuildLog = addHardDisk();
        computerBuildLog += addRAM();
        computerBuildLog += addKeyboard();
        System.out.println(computerBuildLog);
        return computerBuildLog;
    }

    public abstract String addHardDisk();

    public abstract String addRAM();

    public abstract String addKeyboard();

}
