package dev.academy.debugging.remote;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class RemoteApplication {

    public static void main(String[] args) {
        int countFrom = 5;
        int messageNrOffset = 2;
        double progress;
        // simulation of listening server
        CountDown counter = new CountDown(countFrom);
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                progress = 1.0 / counter.countDown();
                if (progress == 1.0) {
                    ExternalService e = ExternalService.getInstance();
                    String message = e.getValue();
                    String requestNo = MessageHandler.handleMessage(message, messageNrOffset);
                    System.out.println("requestNo: " + requestNo);
                }
            }
            catch(ArithmeticException | InterruptedException e) {
                System.out.println("Can you fix the code, so the arithmetic exception is not needed?");
            }
        }
    }
}
