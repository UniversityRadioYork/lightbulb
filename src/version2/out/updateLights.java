package version2.out;

import java.io.FileWriter;
import java.io.IOException;

public class updateLights{
    /**
     * @title updateLights
     * @author Michael G
     * @throws InterruptedException
     * 
     * @purpose Final Stage for Getting the light signal out
     * @description doUpdate takes in hex code as byte
     * 
     * Added to description later - THATS NOT WHAT HAPPENS
     * I mean, ok, nearly, its an int though and a number
     */
    public static int currentLED;

    public static void doUpdate(int commandNumber) {
        int timer;
        //Basically output
        if (commandNumber!=currentLED){
            currentLED = commandNumber;

            try{
                FileWriter output = new FileWriter("*********", false);
                output.write(Integer.toString(commandNumber));
                output.close();
            
            }catch (IOException d){
                ;
            }
        
        timer = 3000;
        
        }else{
            timer = 1000;
        }

        try{
            Thread.sleep(timer);
        }catch (InterruptedException e){
            ;
        }
    }
    
}