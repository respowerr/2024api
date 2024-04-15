package fr.callidos.account;

import fr.callidos.account.services.WarehouseService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelixApplication {

    public static void main(String[] args) {
        System.out.println("" +
                "@@@@@@           @@@@@@                       @@@@@@@@@@@@@@@@@@@@@@@                       @@@@@@@                                   @@@@@@@                             @@@                  @@@     \n" +
                "@@@@@@           @@@@@@                       @@@@@@@@@@@@@@@@@@@@@@@                       @@@@@@@                                   @@@@@@@                             @@@@@              @@@@@     \n" +
                "@@@@@@@          @@@@@@                       @@@@@@@@@@@@@@@@@@@@@@@                       @@@@@@@                                      @@@@                             @@@@@@@@        &@@@@@@@     \n" +
                "@@@@@@@@@        @@@@@@                       @@@@@@@@@@                                    @@@@@@@                                   @@   @@                             @@@@@@@@@@    @@@@@@@@@@     \n" +
                "  @@@@@@@@@@@@   @@@@@@                         @@@@@@@@@@@@@@@@@@@@@                       @@@@@@@                                   @@@@@                                  @@@@@@@@@@@@@@@@@@@       \n" +
                "@@   @@@@@@@@@   @@@@@@                       @@  @@@@@@@@@@@@@@@@@@@                       @@@@@@@                                   @@@@@@@                                  @@@@@@@@@@@@@@          \n" +
                "@@@@   @@@@@@@   @@@@@@                       @@@@   @@@@@@@@@@@@@@@@                       @@@@@@@                                   @@@@@@@                                     @@@@@@@@@@           \n" +
                "@@@@@@           @@@@@@                       @@@@@@@                                       @@@@@@@                                   @@@@@@@                                 @@@@  @@@@@@@@@@         \n" +
                "@@@@@@           @@@@@@                       @@@@@@@                                       @@@@@@@                                   @@@@@@@                              @@@@@@@@@   @@@@@@@@@      \n" +
                "@@@@@@           @@@@@@                       @@@@@@@@@@@@@@@@@@@@@@@                       @@@@@@@@@@   @@@@@@                       @@@@@@@                            @@@@@@@@@@      @@@@@@@@@@    \n" +
                "@@@@@@           @@@@@@                       @@@@@@@@@@@@@@@@@@@@@@@                       @@@@@@@@@@@@@   @@@                       @@@@@@@                         @@@@@@@@@@           @@@@@@@@@@" +
                "\n[  VERSION  ]: HELIX V 1.0.0");
        SpringApplication.run(HelixApplication.class, args);
    }
}
