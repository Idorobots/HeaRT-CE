package ce;

import JHeroic.JHeroic;
import JHeroic.JHModel;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello HeaRT!");

        JHeroic jh = new JHeroic(1, "localhost", 8090);

        try {
            for(JHModel m : jh.getModelList()) {
                System.out.println(m.getReplayString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
