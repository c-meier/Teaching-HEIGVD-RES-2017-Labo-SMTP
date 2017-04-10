import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by christopher on 06.04.17.
 */
public class Victims {
    private List<String> victims;

    public void loadVictims(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        victims = new ArrayList<>();

        String victim;

        while((victim = br.readLine()) != null) {
            victims.add(victim);
        }

        br.close();
    }

    public void randomizeVictims() {
        Collections.shuffle(victims);
    }

    public String[][] getVictimGroups(int nbGroup) throws Exception{
        String[][] grps = new String[nbGroup][];

        Iterator it = victims.iterator();
        int nbByGrp = victims.size() / nbGroup;

        if(nbByGrp < 3) {
            throw new Exception("Not enought victim per group.");
        }

        int surplus = victims.size() % nbGroup;
        nbByGrp++;
        for(int i = 0; i < surplus; i++) {
            grps[i] = new String[nbByGrp];
            for(int j = 0; j < nbByGrp; j++) {
                grps[i][j] = (String) it.next();
            }
        }
        nbByGrp--;
        for(int i = surplus; i < nbGroup; i++) {
            grps[i] = new String[nbByGrp];
            for(int j = 0; j < nbByGrp; j++) {
                grps[i][j] = (String) it.next();
            }
        }

        return grps;
    }
}
