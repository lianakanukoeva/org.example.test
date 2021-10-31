package org.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Main {
    @SpringBootApplication
    public static class AccessingDataMongodbApplication implements CommandLineRunner {

        @Autowired
        private UserRepository repository;

        public static void main(String[] args) {
            SpringApplication.run(AccessingDataMongodbApplication.class);
        }

        @Override
        public void run(String... args) throws Exception {
            // методы доавления данных и поска по полям
            repository.deleteAll();
            repository.save(new User("Tester", "Testerov", "test@test.com", "+1005224"));
            repository.save(new User("SecondUser", "His LastName", "test@test.com", "+795001"));

            System.out.println("Customers found with findAll():");
            // ищем всех пользователей
            System.out.println("--------------------");
            for (User user : repository.findAll()) {
                System.out.println(user);
            }
            System.out.println();

            // ищем одного пользователя по имени
            System.out.println("findByFirstName('Tester'):");
            System.out.println(repository.findByFirstName("Tester"));

            //ищем по мылу
            System.out.println("findByEmail('test@test.com'):");
            for (User user : repository.findByEmail("test@test.com")) {
                System.out.println(user);
            }
        }
    }


}