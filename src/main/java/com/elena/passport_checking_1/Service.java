package com.elena.passport_checking_1;

import com.elena.passport_checking_1.Model.Passport;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
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
    private Scanner scanner;

    public Service(TransportClient client) {
        this.client = client;
        scanner = new Scanner(System.in);
    }

    public IndexResponse Add(TransportClient client, String index, String type, Passport passport) throws IOException, ExecutionException, InterruptedException {
        System.out.println("By what id put new data?\n");
        String id = this.scanner.nextLine();

        if (!this.Exist(client, index, type, id)) {
            this.Delete(client, index, type, id);
        }

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("firstname", passport.getFullName().getFirstName())
                .field("secondname", passport.getFullName().getSecondName())
                .field("lastname", passport.getFullName().getLastName())
                .endObject();

        IndexResponse response = client.prepareIndex(index, type, id)
                .setSource(builder).get();
        return response;
    }

    public GetResponse Get(TransportClient client, String index, String type) throws ExecutionException, InterruptedException {
        System.out.println("Write down an id\n");
        String id = scanner.nextLine();
        GetResponse response = client.get(new GetRequest(index, type, id)).get();
        System.out.println(response);
        return response;
    }

    public boolean Exist(TransportClient client, String index, String type, String id) throws ExecutionException, InterruptedException {
        GetResponse response = client.get(new GetRequest(index, type, id)).get();
        System.out.println(response);
        return true;
    }

    public DeleteResponse Delete(TransportClient client, String index, String type, String id) throws ExecutionException, InterruptedException {
        if (id.equals("-1")) {
            System.out.println(" Write down id\n");
            id = scanner.nextLine();
        }
        DeleteResponse response = client.delete(new DeleteRequest(index,type,id)).get();
        System.out.println(response);
        return response;
    }
}
