import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class ArrivalTime {
    public static boolean checkValidArrivalTime(String input){
        try{
        String[] time = input.split(":");
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int seconds = Integer.parseInt(time[2]);
        if((hours > -1) && (hours < 24)){
            if((minutes > -1) && (minutes < 60)){
                if((seconds > -1) && (seconds < 60)){
                    return true;
                }
            }
        }
        return false;  
    }
    catch(Exception e){
        return false;
    }
    }
    
    public static boolean checkRequiredArrivalTime(String input,String fileTime){
        String line = input;
        String[] lineInformation = line.split(":");
        int hours = Integer.parseInt(lineInformation[0]);
        int minutes = Integer.parseInt(lineInformation[1]);
        int seconds = Integer.parseInt(lineInformation[2]);
        String lineFile = fileTime;
        String[] fileInfo = lineFile.trim().split(":");
        int hoursF = Integer.parseInt(fileInfo[0]);
        int minutesF = Integer.parseInt(fileInfo[1]);
        int secondsF = Integer.parseInt(fileInfo[2]);
        if(hours == hoursF){
            if(minutes == minutesF){
                if(seconds == secondsF){
                    return true;
                }
            }
        }
        return false;
    }

    
}
