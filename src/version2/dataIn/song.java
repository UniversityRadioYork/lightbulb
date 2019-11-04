package version2.dataIn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @title song
 * @author Michael G
 * @purpose Data for the LEDs based on either song title, or song volume.
 * Song volume stuff will hopefully be later
 * Currently, just based on name
 */

public class song{

    public static int songTitleData(){
        try{
            
            URL url = new URL("https://ury.org.uk/audio/status-json.xsl");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            httpCon.setReadTimeout(3000);
            httpCon.connect();

            BufferedReader reader   = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            String songTitle = stringBuilder.substring(stringBuilder.substring(stringBuilder.indexOf("\"title\"")).indexOf(":")+2+stringBuilder.indexOf("\"title\""), stringBuilder.substring(stringBuilder.indexOf("\"title\"")).indexOf("\",")+stringBuilder.indexOf("\"title\""));

            String[] titleWords = songTitle.split(" ");
            
            /**
             * Could this be some sort of dictionary like object? Yes
             * Is it? No
             * Why not? I have a seminar soon, and I really just cba to change it
             * 
             * Also, I'm hungry
             * 
             * I promise, I will change it, hopefully soon.
             */
            
            String[] colours = {"black", "blue", "green", "cyan", "red", "magenta", "yellow", "white", "orange", "cold", "rainbow", "warm", "hot", "water", "fire", "indigo"};
            int[] colourcodes ={0, 1, 2, 3, 4, 5, 6, 7, 6, 21, 22, 23, 23, 21, 23, 1};
            
            for (String i:titleWords){
                for (int j =0; j<(colours.length-1); j++) {
                    
                    if (colours[j].equals(i.toLowerCase())){
                        return colourcodes[j];
                    }
                }
            }
            return 444;
        }catch (Exception e){
            //Let's hope this doesn't happen
            return 404;
        }
    }
}