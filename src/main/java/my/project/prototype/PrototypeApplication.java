package my.project.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import my.project.prototype.aview.CLI;

@SpringBootApplication
public class PrototypeApplication implements CommandLineRunner {

    @Autowired
    private CLI cli;

    public static void main(String[] args) {
        SpringApplication.run(PrototypeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && "no-shell".equals(args[0])) {
            return;
        }
        cli.start();
    }
}