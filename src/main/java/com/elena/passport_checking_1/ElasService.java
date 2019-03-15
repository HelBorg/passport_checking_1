package com.elena.passport_checking_1;

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
    private TransportClient client;

    public void Service() throws IOException, ExecutionException, InterruptedException {

        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        Service ser = new Service(client);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Menu:\n -Add\n -Get\n -Delete\n -Exit\n");
            input = scanner.nextLine();
            switch (input) {
                case "Add":
                    ser.Add(client, "passportchecking", "fullname");
                    break;
                case "Update":
                    ser.Update(client, "passportchecking", "fullname");
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
