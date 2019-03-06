package com.elena.passport_checking_1;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Service {
    private TransportClient client;

    public IndexResponse Add(TransportClient client, String index, String type, String id) throws IOException {

        this.client = client;

        System.out.println("Write down your fullname");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        String secondName = scanner.nextLine();
        String lastName = scanner.nextLine();


        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("firstname", firstName)
                .field("secondname", secondName)
                .field("lastname", lastName)
                .endObject();

        IndexResponse response = client.prepareIndex(index, type, id)
                .setSource(builder).get();
        return response;
    }

    public GetResponse Get(TransportClient client, String index, String type, String id) throws ExecutionException, InterruptedException {
        this.client = client;

        GetResponse response = client.get(new GetRequest(index, type, id)).get();
        System.out.println(response);
        return response;
    }
}
