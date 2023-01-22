package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Employee[] dataBase =
                {new Employee(1, "Jonh", "Smith", "USA", 25),
                        new Employee(2, "Inav", "Petrov", "RU", 23)};
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        csvBuilder(dataBase, fileName);
        List<Employee> list = parseCSV(columnMapping, fileName);
        //list.forEach(System.out::println);

        String jsonString = listToJson(list);
        System.out.println(jsonString);

        String jsonFile = "data.json";
        createJson(jsonString, jsonFile);
    }

    public static List<Employee> parseCSV(String[] columhMapping, String fileName) {
        List<Employee> list = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columhMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            list = csv.parse();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return list;
    }

    public static String listToJson(List<Employee> list) {
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(list, listType);
    }

    public static void createJson(String input, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(input);
            writer.flush();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void csvBuilder(Employee[] dataBase, String fileName) throws IOException {
        for (Employee employee : dataBase) {
            String[] emp = employee.toString().split(",");
            try (CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))) {
                writer.writeNext(emp);
            } catch (IOException exception) {
                exception.printStackTrace();
        }
        }
    }

}
