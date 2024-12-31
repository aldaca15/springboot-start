package com.designpatterns.creational;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemplateMethodTest {

    @Test
    public void templateMethodTest() {
        ComputerManufacturer createDevice = new LaptopManufacturer();
        String deviceCreationLog = createDevice.buildComputer();
        assertFalse(deviceCreationLog.contains("Desktop"));
        assertFalse(deviceCreationLog.contains("accessory"));
        assertTrue(deviceCreationLog.contains("M.2"));
        assertTrue(deviceCreationLog.contains("SO-DIMM"));

        createDevice = new DesktopManufacturer();
        deviceCreationLog = createDevice.buildComputer();
        assertFalse(deviceCreationLog.contains("Laptop"));
        assertFalse(deviceCreationLog.contains("integrated"));
        assertTrue(deviceCreationLog.contains("SSD"));
        assertTrue(deviceCreationLog.contains("DIMM"));
    }

}
