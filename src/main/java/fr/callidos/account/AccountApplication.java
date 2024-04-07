package fr.callidos.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication {

    public static void main(String[] args) {
        System.out.println("\n\n\n\n" +
                " | | | | ____| |   |_ _\\ \\/ /            / \\  / ___|_   _|\n" +
                " | |_| |  _| | |    | | \\  /   _____    / _ \\| |     | |  \n" +
                " |  _  | |___| |___ | | /  \\  |_____|  / ___ \\ |___  | |  \n" +
                " |_| |_|_____|_____|___/_/\\_\\         /_/   \\_\\____| |_|  " +
                " 0.0.1 ");
        SpringApplication.run(AccountApplication.class, args);


    }

}
