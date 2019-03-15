package com.elena.passport_checking_1;

import com.elena.passport_checking_1.model.Passport;
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

    private Passport getPassport() {
        Scanner scanner = new Scanner(System.in);
        Passport passport = new Passport();

        System.out.println("Write down first name, second name and last name");
        passport.setFirstName(scanner.nextLine());
        passport.setSecondName(scanner.nextLine());
        passport.setLastName(scanner.nextLine());

        System.out.println("Write down sex");
        passport.setSex(scanner.nextLine());

        System.out.println("Write down number of passport");
        passport.setNumber(Integer.parseInt(scanner.nextLine()));

        System.out.println("Write down series of passport");
        passport.setSeries(Integer.parseInt(scanner.nextLine()));

        return passport;
    }

    public IndexResponse Add(TransportClient client, String index, String type) throws IOException, ExecutionException, InterruptedException {
        Passport passport = getPassport();
        System.out.println("By what id put new data?\n");
        String id = this.scanner.nextLine();

        if (!this.Exist(client, index, type, id)) {
            this.Delete(client, index, type, id);
        }

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("firstname", passport.getFirstName())
                .field("secondname", passport.getSecondName())
                .field("lastname", passport.getLastName())
                .field("sex", passport.getSex())
                .field("number", passport.getNumber())
                .field("series", passport.getSeries())
                .field("birthday", passport.getBirthday())
                .field("birthplace", passport.getBirthplace())
                .field("deliveryDay", passport.getDeliveryDay())
                .field("deliveryPlace", passport.getDeliveryPlace())
                .endObject();

        IndexResponse response = client.prepareIndex(index, type, id)
                .setSource(builder).get();
        return response;
    }

    public IndexResponse Update(TransportClient client, String index, String type) throws InterruptedException, ExecutionException, IOException {
        return this.Add(client, index, type);
    }

    public GetResponse Get(TransportClient client, String index, String type) throws ExecutionException, InterruptedException {
        System.out.println("Write down an id\n");
        String id = scanner.nextLine();
        GetResponse response = client.get(new GetRequest(index, type, id)).get();
        return response;
    }

    public boolean Exist(TransportClient client, String index, String type, String id) throws ExecutionException, InterruptedException {
        GetResponse response = client.get(new GetRequest(index, type, id)).get();
        System.out.println(response);
        System.out.println(response.getField("found"));
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
