import version2.dataIn.*;
import version2.out.*;

class uryled2{
/**
 * @title uryled2
 * @author Michael Grace, 2019
 * @description Controls the LED lights in Studio Red
 */ 

    public static void main(String[] args){
    
        boolean newsChecked = false;
        boolean endChecked = false;
        boolean[] returnedFunc;
    
        while (true){
        
            //0. Check Master File
                //Ignore, may be implemented later

            //All else should check if show currently happening
            if ((selector.currentShow())||(sisDb.getSisInfo()[1]=="1")){

                //1. News
                returnedFunc = other.checkNews(newsChecked);
                newsChecked = returnedFunc[1];
                if (returnedFunc[0]){
                    updateLights.doUpdate(1);
                    updateLights.doUpdate(0);
                    continue;
                }

                //2. End of Show
                returnedFunc = selector.showEndTime(endChecked);
                endChecked = returnedFunc[1];
                if (returnedFunc[0]){
                    updateLights.doUpdate(2);
                    updateLights.doUpdate(0);
                    continue;
                }

                //3. Dead Air
                if (selector.checkDeadAir()){
                    updateLights.doUpdate(4);
                    updateLights.doUpdate(0);
                    continue;
                }

                //4. OBIT
                if (selector.checkObit()){
                    updateLights.doUpdate(1);
                    updateLights.doUpdate(0);
                    continue;
                } 

                //5. Check Studio Selector for Incorrect Selection
                if ((selector.currentStudio()!='1') && (sisDb.getSisInfo()[1]!="1")){
                    updateLights.doUpdate(2);
                    updateLights.doUpdate(0);
                    continue;
                }

                //6. Check SIS (as show colour, only overrides if selected on SIS)
                String sis_return = sisDb.getSisInfo()[0];
                int toSend;

                switch (sis_return.substring(1,3)){
                    case "Fr":
                        toSend = 4;
                        break;
                    case "Fg":
                        toSend = 2;
                        break;
                    case "Fb":
                        toSend = 1;
                        break;
                    case "Fc":
                        toSend = 3;
                        break;
                    case "Fm":
                        toSend = 5;
                        break;
                    case "Fy":
                        toSend = 6;
                        break;
                    case "Fw":
                        toSend = 7;
                        break;
                    case "Fk":
                        toSend = 0;
                        break;
                    case "Pw":
                        toSend = 23;
                        break;
                    case "Pr":
                        toSend = 22;
                        break;
                    case "Pc":
                        toSend = 21;
                        break;
                    default:
                        toSend = 404;
                }

                if ((sis_return.charAt(0) == '1') && (toSend != 404)){
                    updateLights.doUpdate(toSend);
                    continue;
                }

                //7. Check Current Song for Colour
                int songTitleColour = song.songTitleData();
                if (songTitleColour<400){
                    updateLights.doUpdate(songTitleColour);
                    continue;
                }
                
                //8. Level meter for other songs
                    //Frankly, this is probably easier in Python...
                    //Also, might implement later

                //9. Check for saved colour
                if (toSend!=404){
                    updateLights.doUpdate(toSend);
                    continue;
                }

                //10. Studio Red = Red
                updateLights.doUpdate(4);
            
            }else{
                newsChecked = false;
                updateLights.doUpdate(0);
            }
        }
    }
}