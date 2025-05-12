package com.example.demo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonClassTests {
    @Test
    void testPersonCreation() {
        Person person = new Person(1, "Alice");
        assertEquals(1, person.getId());
        assertEquals("Alice", person.getName());
    }

    @Test
    void testPersonSetters() {
        Person person = new Person();
        person.setId(2);
        person.setName("Bob");

        assertEquals(2, person.getId());
        assertEquals("Bob", person.getName());
    }
}
