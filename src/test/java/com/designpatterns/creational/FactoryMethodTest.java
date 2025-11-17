package com.designpatterns.creational;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactoryMethodTest {

    /**
     * Example of a factory method implementation
     * This example refers to a classic example of exchanging file formats
     * for a reporter software
     * Advantages: Real implementation of how report is created is hidden
     */
    @Test
    public void testFactoryMethodWithReportsExample() {
        // When client is called it does not couple concrete types on the objects
        ReportFactory factory = new ReportPDFFactory();
        Report report1 = factory.create();
        report1.generar();
        Assertions.assertEquals(1, report1.getType());

        // Changing to call excel report
        factory = new ReportExcelFactory();
        Report report2 = factory.create();
        report2.generar();
        Assertions.assertEquals(2, report2.getType());
    }

}

// Contrato
interface Report { void generar(); int getType(); }

class ReportPDF implements Report {
    public void generar() { System.out.println("Generation: PDF"); }
    public int getType() { return 1; }
}
class ReportExcel implements Report {
    public void generar() { System.out.println("Generation: Excel"); }
    public int getType() { return 2; }
}

interface ReportFactory {
    Report create();
}
class ReportPDFFactory implements ReportFactory {
    public Report create() {
        return new ReportPDF();
    }
}
class ReportExcelFactory implements ReportFactory {
    public Report create() {
        return new ReportExcel();
    }
}
