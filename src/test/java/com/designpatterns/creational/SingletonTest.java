package com.designpatterns.creational;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonTest {

    @Test
    public void singletonTest() throws IOException, ClassNotFoundException {
        DateUtil dateUtil1 = DateUtil.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File("/Users/Usuario/Documents/singleton/dateUtil.ser")));
        oos.writeObject(dateUtil1);

        DateUtil dateUtil2 = null;
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(new File("/Users/Usuario/Documents/singleton/dateUtil.ser")));
        dateUtil2 = (DateUtil) ois.readObject();

        oos.close();
        ois.close();

        System.out.println(dateUtil1 == dateUtil2);
        assertEquals(dateUtil1, dateUtil2);

    }

    @Test
    public void singletonLoggerTest() {
        Logger logger = Logger.getInstance();

        logger.log("This is first log message.");
        logger.log("Singleton Logger working just fine");
    }

}
