package ru.otus.spring.service.Impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.service.IOService;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IOStringServiceImplTest {

    IOService ioService;

    @Test
    @DisplayName("correct reading from the console")
    void readAndReadInt() {
        ioService = new IOStringServiceImpl(new Scanner(new ByteArrayInputStream("yes\n4".getBytes())));
        assertEquals("yes", ioService.read());
        assertEquals(4, ioService.readInt());
    }
}