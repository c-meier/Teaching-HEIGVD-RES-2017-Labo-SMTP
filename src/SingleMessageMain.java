import java.io.IOException;

public class SingleMessageMain {
    public static void main(String[] args) {

        ISMTPClient client = new PrankClient("prank.com");

        try {
            client.connect("mailcl0.heig-vd.ch", 25);
            PrankMessage pm = new PrankMessage(
                    "miguel.santamaria@heig-vd.ch",
                    "olivier.liechti@heig-vd.ch",
                    "[RES] SMTP - christopher.meier@heig-vd.ch",
                    "Laboratoire réussi.");
            client.sendMessage(pm);
        } catch (IOException ex) {
            System.out.println("Problem");
        } finally {
            try {
                client.disconnect();
            } catch (IOException ex) {
                System.out.println("Problem");
            }
        }
    }
}
