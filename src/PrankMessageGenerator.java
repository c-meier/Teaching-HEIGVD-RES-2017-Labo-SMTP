import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by christopher on 06.04.17.
 */
public class PrankMessageGenerator {
    private static final Logger LOG = Logger.getLogger(PrankMessageGenerator.class.getName());

    List<PrankMessage> pranks;

    public PrankMessageGenerator() {
        pranks = new ArrayList<>();
    }

    public void loadPranks(String contentFolderPath) {
        File prankFolder = new File(contentFolderPath);
        File[] prankFiles = prankFolder.listFiles();
        int count = 0;
        for(File prankFile : prankFiles) {
            try {
                PrankMessage pm = parsePrankMessageStream(new FileInputStream(prankFile));
                pranks.add(pm);
                count++;
            } catch (IOException e) {
                LOG.log(Level.WARNING, "Could not parse " + prankFile.getPath() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
        LOG.log(Level.INFO, "Loaded " + count + " prank message(s).");
    }

    private PrankMessage parsePrankMessageStream(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        PrankMessage pm = new PrankMessage();

        pm.setSubject(br.readLine());

        String content = "";
        String line;
        while((line = br.readLine()) != null) {
            content += line + SMTPProtocol.END_LINE;
        }
        pm.setContent(content);

        br.close();

        return pm;
    }

    public PrankMessage getRandomPrankMessage() {
        Random rand = new Random();
        return pranks.get(rand.nextInt(pranks.size()));
    }

    public boolean hasPrankMessage() {
        return !pranks.isEmpty();
    }
}
