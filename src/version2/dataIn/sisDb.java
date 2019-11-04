package version2.dataIn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class sisDb {

    /**
     * @title sisDb
     * @author Michael G
     * @purpose Gets info from SIS
     * Also gets from computing.txt, so we can force it to think a show is on.
     * @description TBD
     */

    public static String[] getSisInfo(){

        String filePath = "*********";

        try{
            FileReader fileReader = new FileReader(filePath+"sisData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String commandCode = bufferedReader.readLine();

            FileReader comp_read = new FileReader(filePath+"computing.txt");
            BufferedReader compBuffer = new BufferedReader(comp_read);
            String comp_team = compBuffer.readLine();
            
            bufferedReader.close();
            compBuffer.close();

            String[] toReturn = {commandCode, comp_team};
            return toReturn;

        }catch(IOException e){

            String[] toReturn = {"0Fz", "0"};
            return toReturn;
        }
    }

}