/* Add comment block here
 */

//Sirui Zhang and Jinfeng He
public class SmartCard
{
    private int cardID;           // the id of the smartcard
    private char type;            // the type of the smartcard (it can be "C", "A" or "S")
    private double balance;       // the balance available on the smartcard (should have a minimum balance of $5)
    private Journey journey1;             // journey object
    private Journey  journey2;             // journey object - can only be used if the type of the smartcard is "A" or "S"
    private Journey  journey3;             // journey object - can only be used if the type of the smartcard is "S"

    private Journey[] journeys ; // array of Journey
    // set SmartCardID
    public void setSmartCardID(int cardID)
    {
        this.cardID = cardID;
    }
    // get SmartCardID
    public int getSmartCardID()
    {
        return cardID; 
    }


    // constructor

    public SmartCard()
    {


    }


    public SmartCard(int cardID, char type, double balance)
    {
        this.cardID = cardID;
        this.type = type;
        this.balance = balance;
        if(type=='c' || type=='C')
        {
            this.journeys = new Journey[1];
        }
        else if(type=='a' || type=='A')
        {
            this.journeys = new Journey[2];
        }
        else if(type=='s' || type=='S')
        {
            this.journeys = new Journey[3];
        }


    }

    // getter and setter methods

    public void setType(char type)
    {
        this.type = type;
    }

    public char getType()
    {
        return type;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setJourney1(Journey journey1)
    {
        this.journey1 = journey1;
    }

    public Journey getJourney1()
    {
        return journey1;
    }

    public void setJourney2(Journey journey2)
    {
        this.journey2 = journey2;
    }

    public Journey getJourney2()
    {
        return journey2;
    }

    public void setJourney3(Journey journey3)
    {
        this.journey3 = journey3;
    }

    public Journey getJourney3()
    {

        return journey3;
    }

    public void setJourneys(Journey journey, int i)
    {
        this.journeys[i] = journey;
    }

    public Journey[] getJourneys()
    {
        return journeys;
    }






}
