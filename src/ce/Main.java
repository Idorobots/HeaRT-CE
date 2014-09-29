package ce;

import JHeroic.JHModel;
import JHeroic.JHeroicInterface;
import ce.tcp.ThreadUnsafeJCowardly;
import ce.tcp.JCowardlyInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello HeaRT!");
        final int port = 3128;

        JCowardlyInterface server;
        try {
            server = new ThreadUnsafeJCowardly(new JHeroicInterface() {
                @Override
                public ArrayList<JHModel> getModelList() throws Exception {
                    return null;
                }

                @Override
                public String getUserModel(String s, String s2) throws Exception {
                    return null;
                }

                @Override
                public String addUserModel(String s, String s2, String s3) throws Exception {
                    return null;
                }

                @Override
                public String removeUserModel(String s, String s2) throws Exception {
                    return null;
                }

                @Override
                public String runInference(String s, String s2, String s3, ArrayList<String> strings, String s4) throws Exception {
                    return null;
                }

                @Override
                public String addStateToModel(String s, String s2, String s3, String s4) throws Exception {
                    return null;
                }

                @Override
                public String removeStateFromModel(String s, String s2, String s3) throws Exception {
                    return null;
                }

                @Override
                public String getProtocolVersion() {
                    return null;
                }

                @Override
                public String verifyModel(String s, String s2, String s3) throws Exception {
                    return null;
                }
            });
        } catch (IOException e) {
            System.out.println("Well, fuck 1");
            e.printStackTrace();
            return;
        }
        System.out.println("pre start");
        try {
            server.start(port);
        } catch (IOException e) {
            System.out.println("Well, fuck 2");
            e.printStackTrace();
            return;
        }
        try {
            Socket s = new Socket("localhost",3128);
            System.out.println("1");
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            System.out.println("2");
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("3");
            Thread.sleep(500);
            out.println("some string\n.\n");
            System.out.println("4");
            String string = "";
            System.out.println("5");
            while (string.isEmpty()) {
                System.out.println("6");
                string = in.readLine();
            }
            System.out.println("7");
            System.out.println(string);
            System.out.println("8");
        } catch (IOException e) {
            System.out.println("Well, fuck 3");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after start");
        try {
            server.softShutdown();
        } catch (IllegalStateException e) {
            System.out.println("okay");
            e.printStackTrace();
            return;
        }
        System.out.println("presleep");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Seriously?");
            e.printStackTrace();
            return;
        }
        System.out.println("pre shutdown");
        server.forcedShutdown();
        System.out.println("post shutdown");
    }
}
