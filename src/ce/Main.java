package ce;

import ce.http.HttpServer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello HeaRT!");

        try {
            HttpServer restful = new HttpServer("localhost", 1234);
            restful.start();

            System.out.println("Press any key to exit!");
            System.in.read();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
