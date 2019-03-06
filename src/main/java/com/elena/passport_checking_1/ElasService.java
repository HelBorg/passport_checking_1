package com.elena.passport_checking_1;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
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

        Service ser = new Service();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Menu:\n -Add\n -Delete\n");
            String input = scanner.nextLine();
            switch (input) {
                case "Add":
                    ser.Add(client,"passportchecking", "fullname", "3");
                    break;
                case "Get":
                    ser.Get(client, "passportchecking", "fullname", "1");
                    break;
                case "Delete":
                    break;
                case "Exit":
                    continue;
                default:
                    System.out.println("Something got wrong");

            }
        }
    }
}
