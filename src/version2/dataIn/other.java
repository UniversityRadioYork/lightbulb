package version2.dataIn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class other{
    /**
     * @title other
     * @author Michael G
     * @purpose All the other functions to check
     */

    public static boolean[] checkNews(boolean newsChecked){

        //mmss when the lights should signal
        final int checkTime = 5915;
        final int afterNewsTime = 100;

        //Current Time
        DateFormat df = new SimpleDateFormat("mmss");
        Date dateobj = new Date();
        int current_minute = Integer.valueOf(df.format(dateobj));
        
        if (newsChecked && current_minute>=afterNewsTime && current_minute<checkTime){ //To flash before news
            
            newsChecked = false;
            
            boolean[] statement = {true, newsChecked};
            return statement;
        
        }else if ((!(newsChecked)) &&  current_minute>=checkTime){ //To flash after news
        
            newsChecked = true;

            boolean[] statement = {true, newsChecked};
            return statement;
        
        }else{ //Most of the Time

            boolean[] statement = {false, newsChecked};
            return statement;
        
        }
        
    }

}