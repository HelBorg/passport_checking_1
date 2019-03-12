package com.elena.passport_checking_1;

import com.elena.passport_checking_1.Model.FullName;
import com.elena.passport_checking_1.Model.Passport;
import com.elena.passport_checking_1.Model.Word;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ElasService {
    private static Passport getPassport() {
        Scanner scanner = new Scanner(System.in);
        Passport passport = new Passport();

        System.out.println("Write down first name, second name and last name");
        passport.setFullName(new FullName(
                new Word(scanner.nextLine()),
                new Word(scanner.nextLine()),
                new Word(scanner.nextLine())));

        System.out.println("Write down sex");
        passport.setSex(scanner.nextLine());

        System.out.println("Write down number of passport");
        passport.setNumber(Integer.parseInt(scanner.nextLine()));

        System.out.println("Write down series of passport");
        passport.setSeries(Integer.parseInt(scanner.nextLine()));

        return passport;
    }

    public static void Service() throws IOException, ExecutionException, InterruptedException {
        TransportClient client;
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        Service ser = new Service(client);

        Scanner scanner = new Scanner(System.in);
        String input;

        while(true) {
            System.out.println("Menu:\n -Add\n -Get\n -Delete\n -Exit\n");
            input = scanner.nextLine();
            switch (input) {
                case "Add":
                    Passport passport = getPassport();
                    ser.Add(client,"passportchecking", "fullname", passport);
                    break;
                case "Get":
                    GetResponse response = ser.Get(client, "passportchecking", "fullname");
                    System.out.println(response);
                    break;
                case "Delete":
                    ser.Delete(client, "passportchecking", "fullname", "-1");
                    break;
                case "Exit":
                    client.close();
                    continue;
                default:
                    System.out.println("Something got wrong");

            }
        }
    }
}
