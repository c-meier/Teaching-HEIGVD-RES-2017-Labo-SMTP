import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private static String smtpServer;
    private static int smtpPort;
    private static int nbGrps;

    private static final String VICTIMS_FILEPATH = "data/victims.txt";
    private static final String PRANK_CONTENT_FILEFOLDER = "data/messages/";

    public static void main(String[] args) {
        smtpServer = args[0];
        smtpPort = Integer.parseInt(args[1]);
        nbGrps = Integer.parseInt(args[2]);

        // Load prank messages
        PrankMessageGenerator messageGenerator = new PrankMessageGenerator();
        messageGenerator.loadPranks(PRANK_CONTENT_FILEFOLDER);
        if(!messageGenerator.hasPrankMessage()) {
            LOG.log(Level.WARNING, "No prank message have been loaded");
            return;
        }

        // Load victims
        Victims victims = new Victims();
        try {
            victims.loadVictims(new FileInputStream(VICTIMS_FILEPATH));
            victims.randomizeVictims();

            ISMTPClient client = new PrankClient("prank.com");

            try {
                client.connect(smtpServer, smtpPort);

                try {
                    String[][] victimGroups = victims.getVictimGroups(nbGrps);

                    for(String[] grp : victimGroups) {
                        PrankMessage pm = messageGenerator.getRandomPrankMessage();
                        // The first element of grp is the (false) sender
                        for (int i = 1; i < grp.length; i++) {
                            pm.setFrom(grp[0]);
                            pm.setTo(grp[i]);
                            client.sendMessage(pm);
                        }
                    }
                } catch (Exception e) {
                    LOG.log(Level.SEVERE,"Problem with the victim groups: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (IOException e) {
                LOG.log(Level.SEVERE,"Problem with the sending of a SMTP message");
                e.printStackTrace();
            } finally {
                try {
                    client.disconnect();
                } catch (IOException e) {
                    LOG.log(Level.SEVERE,"Problem with the disconnection of the SMTP client");
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            LOG.log(Level.SEVERE,"Problem with the victims file");
            e.printStackTrace();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"Problem with the loading of the victim");
            e.printStackTrace();
        }


    }
}
