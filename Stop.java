public class Stop {
    public String stopName;
    public int stopNumber;
     
    public Stop(String stopName, int stopNumber){
        this.stopNumber = stopNumber;
        String[] stop = stopName.split(" ");
        if(stop[0].equalsIgnoreCase("FLAGSTOP")){
            String keyWord = stop[0];
            for(int i = 1;i < stop.length;i++){
                stop[i-1] = stop[i];
            }
            stop[stop.length-1] = keyWord;
            String newStopName = String.join(" ",stop);
            this.stopName = newStopName;
        }
        else if(stop[0].equalsIgnoreCase("WB")){
            String keyWord = stop[0];
            for(int i = 1;i < stop.length;i++){
                stop[i-1] = stop[i];
            }
            stop[stop.length-1] = keyWord;
            String newStopName = String.join(" ",stop);
            this.stopName = newStopName;
        }
        else if(stop[0].equalsIgnoreCase("NB")){
            String keyWord = stop[0];
            for(int i = 1;i < stop.length;i++){
                stop[i-1] = stop[i];
            }
            stop[stop.length-1] = keyWord;
            String newStopName = String.join(" ",stop);
            this.stopName = newStopName;
        }
        else if(stop[0].equalsIgnoreCase("SB")){
            String keyWord = stop[0];
            for(int i = 1;i < stop.length;i++){
                stop[i-1] = stop[i];
            }
            stop[stop.length-1] = keyWord;
            String newStopName = String.join(" ",stop);
            this.stopName = newStopName;
        }
        else if(stop[0].equalsIgnoreCase("EB")){
            String keyWord = stop[0];
            for(int i = 1;i < stop.length;i++){
                stop[i-1] = stop[i];
            }
            stop[stop.length-1] = keyWord;
            String newStopName = String.join(" ",stop);
            this.stopName = newStopName;
        }
        else{
            this.stopName = stopName;
        }
    }
}
