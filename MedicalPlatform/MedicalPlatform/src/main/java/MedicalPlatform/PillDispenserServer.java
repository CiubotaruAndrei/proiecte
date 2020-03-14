package MedicalPlatform;

import MedicalPlatform.dispenserService.PillService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PillDispenserServer implements CommandLineRunner {

    @Autowired
    PillService pillService;

    @Override
    public void run(String args[]) throws IOException, InterruptedException {
        System.out.println("[pillDispenser] starting GRPC Server");
        Server server = ServerBuilder.forPort(9090).addService(pillService).build();

        server.start();

        System.out.println("[pillDispenser] server started at "+ server.getPort());

        server.awaitTermination();

    }
}
