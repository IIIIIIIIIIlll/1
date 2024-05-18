import java.util.Random;
import java.util.Scanner;
// Sirui Zhang and Jinfeng He

public class SystemInterface
{
    private SmartCard card1;
    private SmartCard card2;
    private SmartCard card3;

    private SmartCard[] smartCards = new SmartCard[10];

    private void run()
    {
        // user choice
        while(true){
            this.menu();
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if(choice == 0){
                System.out.println("exit");
                return;
            }
            else if(choice==1){
                System.out.println("add a new card");
                this.addNewCard();
            }
            else if(choice==2){
                System.out.println("delete a card");
                this.deleteCard();
            }
            else if(choice==3){
                System.out.println("delete a journey from a card");
                this.deleteJourneyFromCard();
            }
            else if(choice==4){
                System.out.println("display cards details");
                this.displayCardsDetails();
            }
            else if(choice==5){
                System.out.println("display journey details from a card");
                this.displayJourneysDetail();
            }
            else if(choice==6){
                System.out.println("list of journeys with a particular transport mode");
                this.listJourneysWithTransportMode();
            }
            else if(choice==7){
                System.out.println("view a summary of the total cost/fare for all transportation modes journeys made by all Smartcards");
                this.summaryOfTotalCost();
            }
            else if(choice==8){
                System.out.println("view a summary of the total cost/fare for all transportation modes journeys made by all Smartcards");
                this.summaryOfTotalCost();
            }
            else{
                System.out.println("enter error please enter a valid choice");
            }
        }
    }

    public void menu(){

        // implement  menu
        System.out.println(" Menu");
        System.out.println("1 : add a new card");
        System.out.println("2 : delete a card");
        System.out.println("3 : delete a journey from a card");
        System.out.println("4 : display cards details");
        System.out.println("5 : display journeys details from a card");
        System.out.println("6 : list of journeys with a particular transport mode");
        System.out.println("7 : view a summary of the total cost/fare for all transportation modes journeys made by all Smartcards");
        System.out.println("8 : view a summary of the total cost/fare for all transportation modes journeys made by all Smartcards");
        System.out.println("0 : exit");
        System.out.println("enter your choice: ");
    }

    public void addNewCard() {
        // add a new card
        int count =-1;  // check the number of cards
        for(int i =0 ; i<smartCards.length; i++){
            if(smartCards[i]==null){
                count = i;
                break;
            }
        }
        if(count==-1){
            System.out.println("You can't add more than 10 cards");
            return;
        }
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Enter the cardId: ");
        int cardID = sc.nextInt();
        // check if the cardID already exists
        for(int i =0; i<smartCards.length; i++){
            if(smartCards[i]!=null && smartCards[i].getSmartCardID()==cardID){
                System.out.println("This cardID already exists ");

                cardID = r.nextInt(1000)+1;

            }
        }
        // check  balance
        System.out.println("Enter the balance of the card at least 5.00");
        double balance;
        while (true) {
            balance = sc.nextDouble();
           if (balance >= 5.00) {
               break;
            } else {
               System.out.println("Please enter a valid balance at least 5.00");
            }
        }
        // check type
        System.out.println("Enter the type of the card (C, A, S upper or lower):");
       char type;
       while (true) {
          type = sc.next().charAt(0);
          if (type == 'C' || type == 'c' || type == 'A' || type == 'a' || type == 's' || type == 'S') {
               break;
           } else {
               System.out.println("Please enter a valid type (C, A, S upper or lower): ");
            }
       }
        SmartCard smartCard = new SmartCard(cardID, type, balance);


        // count the number of journeys
        int length =smartCard.getJourneys().length;
        loop: // if a journey already exists enter a new one
        while(length>0){


            System.out.println("enter the journeyId");
            int journeyID = sc.nextInt();
            // check if the journeyID already exists
            for(int i=0 ; i<smartCard.getJourneys().length; i++){
                if(smartCard.getJourneys()[i]!=null && smartCard.getJourneys()[i].getJourneyID()==journeyID){
                    System.out.println("This journeyID already exists ");
                    journeyID = r.nextInt(1000)+1;
                }
            }
            System.out.println("Enter the transport mode only train, bus or tram upper or lower ");
            String transportMode;
            while (true) {
                transportMode = sc.next();
                if (transportMode.equalsIgnoreCase("train") || transportMode.equalsIgnoreCase("bus") || transportMode.equalsIgnoreCase("tram")) {
                    break;
                } else {
                    System.out.println("Please enter a valid transport mode only train, bus or tram upper or lower ");
                }
            }
            System.out.println("Enter the start of the journey between 1 to 10");
            int startOfJourney;
            while (true) {
                startOfJourney = sc.nextInt();
                if (startOfJourney >= 1 && startOfJourney <= 10) {
                    break;
                } else {
                    System.out.println("Please enter a valid start of the journey between 1 to 10");
                }
            }
            System.out.println("Enter the end of the journey between 1 to 10 and > start of the journey");
            int endOfJourney;
            while (true) {
                endOfJourney = sc.nextInt();
                if (endOfJourney >= 1 && endOfJourney <= 10 && endOfJourney > startOfJourney) {
                    break;
                } else {
                    System.out.println("Please enter a valid end of the journey between 1 to 10 and > start of the journey");
                }
            }
            // check if the journey already exists
            for(int i =0; i<smartCard.getJourneys().length;i++){
                if(smartCard.getJourneys()[i]!=null && smartCard.getJourneys()[i].getTransportMode().equalsIgnoreCase(transportMode)&&  smartCard.getJourneys()[i].getStartOfJourney()==startOfJourney && smartCard.getJourneys()[i].getEndOfJourney()==endOfJourney){
                    System.out.println("This journey already exists enter a new one ");
                    continue loop;

                }
            }
            Journey journey = new Journey(journeyID, transportMode, startOfJourney, endOfJourney);
            for( int i =0;i<smartCard.getJourneys().length;i++){
                if(smartCard.getJourneys()[i]==null){
                    smartCard.setJourneys(journey, i);
                    break;
                }
            }
            length--;

        }
        smartCards[count] = smartCard;
        System.out.println("success");





    }
    public void addNewJourneyToCard() {
        // add a new journey to a card
        Scanner sc = new Scanner(System.in);
        if (card1 == null && card2 == null && card3 == null) {
            System.out.println("You have no card please add a card");
            return;
        }
        System.out.println("Enter the cardID: ");
        SmartCard card = new SmartCard();
        while (true) {   // check if the cardID exists
            int cardID = sc.nextInt();
            if (card1 != null && card1.getSmartCardID() == cardID) {
                card = card1;
                break;
            } else if (card2 != null && card2.getSmartCardID() == cardID) {
                card = card2;
                break;
            } else if (card3 != null && card3.getSmartCardID() == cardID) {
                card = card3;
                break;
            } else {
                System.out.println("This cardID does not exist please enter a valid cardID");
            }

        }

        if (card.getType() == 'c' || card.getType() == 'C') {  // only one journey
            if (card.getJourney1() != null) {
                System.out.println("You can't add more than one journey to this card");
                return;
            } else {
                System.out.println("Enter the journey ID: ");
                int journeyID;
                while (true) {
                    journeyID = sc.nextInt();
                    if (card.getJourney1() != null && card.getJourney1().getJourneyID() == journeyID || card.getJourney2() != null && card.getJourney2().getJourneyID() == journeyID || card.getJourney3() != null && card.getJourney3().getJourneyID() == journeyID){
                        System.out.println("This journey ID already exists please enter a new one");
                    } else {
                        break;
                    }
                }

                System.out.println("Enter the transport mode only train, bus or tram upper or lower ");
                String transportMode;
                while (true) {
                    transportMode = sc.next();
                    if (transportMode.equalsIgnoreCase("train") || transportMode.equalsIgnoreCase("bus") || transportMode.equalsIgnoreCase("tram")) {
                        break;
                    } else {
                        System.out.println("Please enter a valid transport mode only train, bus or tram upper or lower ");
                    }
                }
                System.out.println("Enter the start of the journey between 1 to 10");
                int startOfJourney;
                while (true) {
                    startOfJourney = sc.nextInt();
                    if (startOfJourney >= 1 && startOfJourney <= 10) {
                        break;
                    } else {
                        System.out.println("Please enter a valid start of the journey between 1 to 10");
                    }
                }
                System.out.println("Enter the end of the journey between 1 to 10 and > start of the journey");
                int endOfJourney;
                while (true) {
                    endOfJourney = sc.nextInt();
                    if (endOfJourney >= 1 && endOfJourney <= 10 && endOfJourney > startOfJourney) {
                        break;
                    } else {
                        System.out.println("Please enter a valid end of the journey between 1 to 10 and > start of the journey");
                    }
                }
                Journey journey = new Journey(journeyID, transportMode, startOfJourney, endOfJourney);
                if(card==card1){   // only one journey
                    card1.setJourney1(journey);
                }else if(card==card2){
                    card2.setJourney1(journey);
                }else{
                    card3.setJourney1(journey);

                }

            }

        } else if (card.getType() == 'a' || card.getType() == 'A') {     // only 2 journeys
            if (card.getJourney1() != null && card.getJourney2() != null) {
                System.out.println("You can't add more than two journeys to this card");
                return;
            } else {
                System.out.println("Enter the journey ID: ");
                int journeyID;
                while (true) {
                    journeyID = sc.nextInt();
                    if (card.getJourney1() != null && card.getJourney1().getJourneyID() == journeyID || card.getJourney2() != null && card.getJourney2().getJourneyID() == journeyID || card.getJourney3() != null && card.getJourney3().getJourneyID() == journeyID){
                        System.out.println("This journey ID already exists please enter a new one");
                    } else {
                        break;
                    }
                }
                System.out.println("Enter the transport mode only train, bus or tram upper or lower ");
                String transportMode;
                while (true) {
                    transportMode = sc.next();
                    if (transportMode.equalsIgnoreCase("train") || transportMode.equalsIgnoreCase("bus") || transportMode.equalsIgnoreCase("tram")) {
                        break;
                    } else {
                        System.out.println("Please enter a valid transport mode only train, bus or tram upper or lower ");
                    }
                }
                System.out.println("Enter the start of the journey between 1 to 10");
                int startOfJourney;
                while (true) {
                    startOfJourney = sc.nextInt();
                    if (startOfJourney >= 1 && startOfJourney <= 10) {
                        break;
                    } else {
                        System.out.println("Please enter a valid start of the journey between 1 to 10");
                    }
                }
                System.out.println("Enter the end of the journey between 1 to 10 and > start of the journey");
                int endOfJourney;
                while (true) {
                    endOfJourney = sc.nextInt();
                    if (endOfJourney >= 1 && endOfJourney <= 10 && endOfJourney > startOfJourney) {
                        break;
                    } else {
                        System.out.println("Please enter a valid end of the journey between 1 to 10 and > start of the journey");
                    }
                }
                Journey journey = new Journey(journeyID, transportMode, startOfJourney, endOfJourney);
                if(card==card1){ // only 2 journeys
                    if(card1.getJourney1()==null) {
                        card1.setJourney1(journey);
                    }else{
                        card1.setJourney2(journey);
                    }
                }
                else if(card==card2){
                    if(card2.getJourney1()==null) {
                        card2.setJourney1(journey);
                    }else{
                        card2.setJourney2(journey);
                    }
                }
                else {
                    if (card3.getJourney1() == null) {
                        card3.setJourney1(journey);
                    } else {
                        card3.setJourney2(journey);
                    }
                }
            }
        } else {
            if (card.getJourney1() != null && card.getJourney2() != null && card.getJourney3() != null) {   // 3 journeys
                System.out.println("You can't add more than three journeys to this card");
                return;
            } else {
                System.out.println("Enter the journey ID: ");
                int journeyID;
                while (true) {
                    journeyID = sc.nextInt();
                    if (card.getJourney1() != null && card.getJourney1().getJourneyID() == journeyID || card.getJourney2() != null && card.getJourney2().getJourneyID() == journeyID || card.getJourney3() != null && card.getJourney3().getJourneyID() == journeyID){
                        System.out.println("This journey ID already exists please enter a new one");
                    } else {
                        break;
                    }
                }
                System.out.println("Enter the transport mode only train, bus or tram upper or lower ");
                String transportMode;
                while (true) {
                    transportMode = sc.next();
                    if (transportMode.equalsIgnoreCase("train") || transportMode.equalsIgnoreCase("bus") || transportMode.equalsIgnoreCase("tram")) {
                        break;
                    } else {
                        System.out.println("Please enter a valid transport mode only train, bus or tram upper or lower ");
                    }
                }
                System.out.println("Enter the start of the journey between 1 to 10");
                int startOfJourney;
                while (true) {
                    startOfJourney = sc.nextInt();
                    if (startOfJourney >= 1 && startOfJourney <= 10) {
                        break;
                    } else {
                        System.out.println("Please enter a valid start of the journey between 1 to 10");
                    }
                }
                System.out.println("Enter the end of the journey between 1 to 10 and > start of the journey");
                int endOfJourney;
                while (true) {
                    endOfJourney = sc.nextInt();
                    if (endOfJourney >= 1 && endOfJourney <= 10 && endOfJourney > startOfJourney) {
                        break;
                    } else {
                        System.out.println("Please enter a valid end of the journey between 1 to 10 and > start of the journey");
                    }
                }
                Journey journey = new Journey(journeyID, transportMode, startOfJourney, endOfJourney);
                // 3 journeys
                if(card==card1){
                    if(card1.getJourney1()==null) {
                        card1.setJourney1(journey);
                    }else if(card1.getJourney2()==null){
                        card1.setJourney2(journey);
                    }else{
                        card1.setJourney3(journey);
                    }
                }
                else if(card==card2){
                    if(card2.getJourney1()==null) {
                        card2.setJourney1(journey);
                    }else if(card2.getJourney2()==null){
                        card2.setJourney2(journey);
                    }else{
                        card2.setJourney3(journey);
                    }
                }
                else {
                    if (card3.getJourney1() == null) {
                        card3.setJourney1(journey);
                    } else if (card3.getJourney2() == null) {
                        card3.setJourney2(journey);
                    } else {
                        card3.setJourney3(journey);
                    }

                }

            }
        }
        System.out.println("success");
    }
    public void deleteCard(){
        // delete a card
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the cardID: ");
        int cardID=sc.nextInt();
        for(int i=0; i<smartCards.length;i++){
            if(smartCards[i]!=null && smartCards[i].getSmartCardID()==cardID){
                smartCards[i]=null;
                System.out.println("success");
                return;
            }
        }
        System.out.println("cardId not exist");

    }

    public void deleteJourneyFromCard(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the cardID: ");
        int cardID=sc.nextInt();
        for(int i=0;i<smartCards.length;i++){
            if(smartCards[i]!=null && smartCards[i].getSmartCardID()==cardID){
                SmartCard card = smartCards[i];
                System.out.println("Enter the journeyID: ");
                int journeyID = sc.nextInt();
                for(int j=0;j<card.getJourneys().length;j++){
                    if(card.getJourneys()[j]!=null && card.getJourneys()[j].getJourneyID()==journeyID){
                        card.setJourneys(null, j);
                        System.out.println("success");
                        return;
                    }
                }

                System.out.println("journeyID not exist");
                return;
            }
        }
        System.out.println("cardID not exist");
    }

    public void displayCardsDetails(){
        // display cards details
        int cardSize=0;  //count the number of cards
        for(int i =0 ;i<smartCards.length;i++){
            if(smartCards[i]!=null){
                cardSize++;
                int journeySize=0;  //count the number of journeys
                for(int j =0 ;j< smartCards[i].getJourneys().length;j++){
                    if(smartCards[i].getJourneys()[j]!=null){
                        journeySize++;
                    }
                }
                System.out.println("SmartCard "+smartCards[i].getSmartCardID()+" has type " +smartCards[i].getType() +" and " +journeySize+" journey(s)");
                for(int j =0 ;j< smartCards[i].getJourneys().length;j++){
                    if(smartCards[i].getJourneys()[j]!=null){
                        System.out.println("Journey "+smartCards[i].getJourneys()[j].getJourneyID()+" has transport mode "+smartCards[i].getJourneys()[j].getTransportMode());
                    }
                }
            }
        }
        if(cardSize==0) {
            System.out.println("No SmartCards");
        }



}

    public void displayJourneysDetail(){
        // display journeys details from a card
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the cardID: ");
        int cardID=sc.nextInt();
        for(int i=0; i<smartCards.length;i++){
            if(smartCards[i]!=null && smartCards[i].getSmartCardID()==cardID){
                SmartCard card = smartCards[i];
                int journeySize=0;  //count the number of journeys
                for(int j =0 ;j< card.getJourneys().length;j++){
                    if(card.getJourneys()[j]!=null){
                        journeySize++;
                        System.out.println("Journey "+card.getJourneys()[j].getJourneyID()+" has transport mode "+card.getJourneys()[j].getTransportMode() +" starting from "+card.getJourneys()[j].getStartOfJourney()+" and ending at "+card.getJourneys()[j].getEndOfJourney()+" with journey distance of "+card.getJourneys()[j].getDistanceOfJourney()+ "  station(s)/stop(s)");
                    }
                }
                if(journeySize==0){
                    System.out.println("No journeys");
                }
                return;
            }
        }
        System.out.println("cardID not exist");

    }

    public void listJourneysWithTransportMode() {
        // list of journeys with a particular transport mode

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the transport mode only train, bus or tram upper or lower ");
        String transportMode = sc.next();
        if(transportMode.equalsIgnoreCase("train") || transportMode.equalsIgnoreCase("bus") || transportMode.equalsIgnoreCase("tram"))
        {
            int tmp=0;  //count the number of journeys with that transport mode
            for(int i=0;i<smartCards.length;i++){
                if(smartCards[i]!=null){
                    for(int j=0;j<smartCards[i].getJourneys().length;j++){
                        if(smartCards[i].getJourneys()[j]!=null && smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase(transportMode)){
                            System.out.println("Journey "+smartCards[i].getJourneys()[j].getJourneyID()+" has that transport mode, and it belongs to smartcard"+smartCards[i].getSmartCardID());
                            tmp++;
                        }
                    }
                }
            }
            if(tmp==0){
                System.out.println("No journeys with that transport mode");
            }
        }
        else{
            System.out.println("No approved modes");
        }



           }






        public void summaryOfTotalCost(){
              // view a summary of the total cost/fare for all transportation modes journeys made by all Smartcards

                double trainCost=0;       //total train cost
                double busCost=0;         //total bus cost
                double tramCost=0;       //total tram cost

                for(int i =0 ;i<smartCards.length;i++){
                    if(smartCards[i]!=null){
                        if(smartCards[i].getType()=='c' || smartCards[i].getType()=='C' ){  //type c
                            for(int j =0;j< smartCards[i].getJourneys().length;j++){
                                if(smartCards[i].getJourneys()[j]!=null){
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("train")){
                                        trainCost+=1.5+1.86*smartCards[i].getJourneys()[j].getDistanceOfJourney();


                                    }
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("bus")){
                                        busCost+=1.5+1.86*smartCards[i].getJourneys()[j].getDistanceOfJourney();
                                    }
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("tram")){
                                        tramCost+=1.5+1.86*smartCards[i].getJourneys()[j].getDistanceOfJourney();
                                    }


                                }

                            }

                        }
                        if(smartCards[i].getType()=='a' || smartCards[i].getType()=='A'){ //same with c
                            for(int j=0;j<smartCards[i].getJourneys().length;j++){
                                if(smartCards[i].getJourneys()[j]!=null){
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("train")){
                                        trainCost+=1.5+2.24*smartCards[i].getJourneys()[j].getDistanceOfJourney();
                                    }
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("bus")){
                                        busCost+=1.5+2.24*smartCards[i].getJourneys()[j].getDistanceOfJourney();
                                    }
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("tram")){
                                        tramCost+=1.5+2.24*smartCards[i].getJourneys()[j].getDistanceOfJourney();
                                    }
                                }
                            }

                        }

                        if(smartCards[i].getType()=='s' || smartCards[i].getType()=='S'){ //same with c
                            for(int j=0;j<smartCards[i].getJourneys().length;j++){
                                if(smartCards[i].getJourneys()[j]!=null){
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("train")){
                                        trainCost+=1.5+1.60*smartCards[i].getJourneys()[j].getDistanceOfJourney();
                                    }
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("bus")){
                                        busCost+=1.5+1.60*smartCards[i].getJourneys()[j].getDistanceOfJourney();
                                    }
                                    if(smartCards[i].getJourneys()[j].getTransportMode().equalsIgnoreCase("tram")){
                                        tramCost+=1.5+1.60*smartCards[i].getJourneys()[j].getDistanceOfJourney();
                                    }
                                }
                            }

                        }

                    }
                }
                System.out.println("Total transport mode journeys cost/fare:");
                System.out.println("---------------------------------------------------------");
                System.out.println("Total cost of train journeys is "+trainCost);
                System.out.println("Total cost of bus journeys is "+busCost);
                System.out.println("Total cost of tram journeys is "+tramCost);
                System.out.println("---------------------------------------------------------");







        }


        public static void main (String[]args)
        {
            SystemInterface systemUI = new SystemInterface();
            systemUI.run();
        }

}
