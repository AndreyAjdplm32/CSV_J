package org.example;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee() {
        // Пустой конструктор
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return
                this.id +
                "," + this.firstName+
                "," + this.lastName +
                "," + this.country +
                "," + this.age;
    }
}