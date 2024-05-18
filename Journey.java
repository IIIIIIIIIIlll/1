
import java.lang.String;
// Sirui Zhang and Jinfeng He

public class Journey
{
    private int journeyID;                 // the id of the journey
    private String transportMode;          // the public transport mode of the journey (can be only “train”, “bus” or “tram”) 
    private int startOfJourney;            // the starting point of the journey. It can be only a number between [1..10] 
    private int endOfJourney;              // the ending point of the journey. It can be only a number between [1..10] (should be different from the starting point of the journey)
    private int distanceOfJourney;         // the distance of the journey (i.e. the difference in number of stations/stops travelled between startOfJourney and endOfJourney)

    // set Transport Mode
    public void setTransportMode(String transportMode)
    {
        this.transportMode = transportMode;
    }
    // get Transport Mode
    public String getTransportMode()
    {
        return transportMode;
    }

    //constructor

    public Journey(){

    }

    public Journey(int journeyID, String transportMode, int startOfJourney, int endOfJourney)
    {
        this.journeyID = journeyID;
        this.transportMode = transportMode;
        this.startOfJourney = startOfJourney;
        this.endOfJourney = endOfJourney;
        this.distanceOfJourney = endOfJourney- startOfJourney;
    }

    // getter and setter methods
    public void setJourneyID(int journeyId){
        this.journeyID = journeyId;

    }
    public int getJourneyID(){
        return journeyID;
    }

    public void setStartOfJourney(int startOfJourney){
        this.startOfJourney = startOfJourney;
    }

    public int getStartOfJourney(){
        return startOfJourney;
    }

    public void setEndOfJourney(int endOfJourney){
        this.endOfJourney = endOfJourney;
    }

    public int getEndOfJourney(){
        return endOfJourney;
    }

    public void setDistanceOfJourney(){
        this.distanceOfJourney = this.endOfJourney - this.startOfJourney;
    }

    public int getDistanceOfJourney(){
        return distanceOfJourney;
    }
}
