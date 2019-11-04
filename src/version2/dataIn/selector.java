package version2.dataIn;

import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.time.Instant;

public class selector{

    /**
     * @name selector
     * @author Michael G
     * @purpose Returns tests needing the API
     * @description returns for dead air and obit
     */

     public static String requestAPI(String which_api){ //Returns text from the API
        
        final String api_key = "*********";
        
        //Code Similar to https://alvinalexander.com/blog/post/java/how-open-url-read-contents-httpurl-connection-java, October 2019
        
        try{
            
            URL url = new URL("https://ury.org.uk/api/v2/" + which_api + "?api_key=" + api_key);
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

            return stringBuilder.toString();

        }catch (Exception e){
            //Let's hope this doesn't happen
            return e.toString();
        }
     }

    public static boolean checkDeadAir(){
        /**
         * Checks the API for DeadAir
         * Returns true/false from API
         */
        if (requestAPI("selector/issilence").charAt(25)=='1'){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkObit(){
        /**
         * Checks the API for OBIT
         * Returns true/false
         */
        if (requestAPI("selector/isobithappening").charAt(25)=='t'){
            return true;
        }else{
            return false;
        }
    }

    public static char currentStudio(){
        /**
         * Checks API for selector status
         */
        return requestAPI("selector/query").charAt(35);
    }

    public static boolean currentShow(){
        /**
         * Returns true or false is a show is currently scheduled
         */
        if (requestAPI("timeslot/currenttimeslot").charAt(25)!='n'){
            return true;
        }else{
            return false;
        }
    }

    public static boolean[] showEndTime(boolean endChecked){ 
       /**
        * Gets the End Time of the Current Show
        * Afterwards similar to other.news function
        */
        try{
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();

        String slotData = requestAPI("timeslot/currenttimeslot");

        String slotId = slotData.substring(slotData.substring(slotData.indexOf("\"timeslot_id\"")).indexOf(":")+1+slotData.indexOf("\"timeslot_id\""), slotData.substring(slotData.indexOf("\"timeslot_id\"")).indexOf(",")+slotData.indexOf("\"timeslot_id\""));

        long endTime = Long.parseLong(requestAPI("timeslot/"+slotId+"/endtime").substring(25, 35));
        
        if (timeStampSeconds<endTime-180){
            
            endChecked = false;
            
            boolean[] statement = {false, endChecked};
            return statement;
        
        }else if ((timeStampSeconds>= endTime-180) && (endChecked==false)){
        
            endChecked = true;
        
            boolean[] statement = {true, endChecked};
            return statement;
       
        }else{
       
            endChecked = true;
       
            boolean[] statement = {false, endChecked};
            return statement;
        }
    }catch(Exception e){
        boolean[] statement = {false, false};
        return statement;
    }

    }

}