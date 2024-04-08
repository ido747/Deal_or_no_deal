import java.util.*;


class Game {

    HashMap<Integer,Double> suitcases = new HashMap<Integer,Double>();
    HashSet<Integer> remainCases = new HashSet<>();
    HashSet<Double> remainAmounts = new HashSet<>();
    ArrayList<Double> offers = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);



    int yourCase = 0;
    int turn = 0;


    public void init_game(){
        this.remainCases.clear();
        this.suitcases.clear();
        this.remainAmounts.clear();
        this.yourCase = 0;
        this.turn = 0;


        ArrayList<Double> moneyList = new ArrayList<>();
        moneyList.add(0.01);
        moneyList.add(1.0);
        moneyList.add(5.0);
        moneyList.add(10.0);
        moneyList.add(25.0);
        moneyList.add(50.0);
        moneyList.add(75.0);
        moneyList.add(100.0);
        moneyList.add(200.0);
        moneyList.add(300.0);
        moneyList.add(400.0);
        moneyList.add(500.0);
        moneyList.add(750.0);
        moneyList.add(1000.0);
        moneyList.add(5000.0);
        moneyList.add(10000.0);
        moneyList.add(25000.0);
        moneyList.add(50000.0);
        moneyList.add(75000.0);
        moneyList.add(100000.0);
        moneyList.add(200000.0);
        moneyList.add(300000.0);
        moneyList.add(400000.0);
        moneyList.add(500000.0);
        moneyList.add(750000.0);
        moneyList.add(1000000.0);

        // Convert ArrayList to HashSet
        this.remainAmounts = new HashSet<>(moneyList);

        // Shuffle the ArrayList
        Collections.shuffle(moneyList);

        //set suitcase to each money
        for (int i=1;i<27;i++){
            this.suitcases.put(i,moneyList.get(i-1));
            this.remainCases.add(i);
        }
    }
    // the user select your suitcase in the start of the game
    public void selectYourCase(){
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select your suitcase");
        int number = scanner.nextInt();
        while(number<0 || number > 26){
            System.out.println("Please select number between 1 to 26");
            number = scanner.nextInt();
        }
        System.out.println("You select "+number);
        this.yourCase = number;
        this.remainCases.remove(number);
    }
    public int getTurn(){
        return this.turn;
    }

    // the banker send offer to the player based on the remain suitcases, amounts, and turn of the player
    public double getOffer(){
        double sum_ev = 0;
        double offer = 0;
        for (Double value : remainAmounts) {
            sum_ev+=value;
        }
        if(this.turn!=9) {
            if (this.turn == 1) {
                offer = 0.15 * (sum_ev / remainAmounts.size());
            } else if (this.turn == 2) {
                offer = 0.25 * (sum_ev / remainAmounts.size());
            } else if (this.turn == 3) {
                offer = 0.35 * (sum_ev / remainAmounts.size());
            } else if (this.turn == 4) {
                offer = 0.45 * (sum_ev / remainAmounts.size());
            } else if (this.turn == 5 ) {
                offer = 0.6 * (sum_ev / remainAmounts.size());
            } else if (this.turn == 6 ) {
                offer = 0.7 * (sum_ev / remainAmounts.size());
            } else if (this.turn == 7) {
                offer = 0.8 * (sum_ev / remainAmounts.size());
            } else if (this.turn > 7) {
                offer = 0.9 * (sum_ev / remainAmounts.size());
            }
            offers.add(offer);
            return offer;
        }
        return find_amount(this.yourCase);

    }

    //the player need to answear to the banker offer, if he take the deal the game is over, otherwise the game continue

    public boolean dealOrNoDealQuestion(double offer){
        System.out.println("If you want to take this deal, press 1, other press 0");
        int number = scanner.nextInt();
        while(number!=1 && number!=0){
            System.out.println("press only 1 (DEAL) or 0 (NO DEAL)");
            number = scanner.nextInt();
        }
        if (number==1){
            System.out.println("You took the deal and earned "+offer);
            System.out.println("Your suitcase has " +find_amount(this.yourCase));
            if (offer > find_amount(this.yourCase)){
                System.out.println("you Made a great deal!");
            }
            else
                System.out.println("you Made a bad deal!");
            return true;
        }
        else
            return false;
    }

    public int getYourSuitCaseNum(){
        return this.yourCase;
    }

    //the user pick suitcases to eliminate
    public void pickNumbers(){
        this.turn++;
        if (this.turn == 1 || this.turn==2){
            for (int i=0;i<6;i++){
                System.out.println("Please select suitcase to eliminate. you have "+(6-i)+" suitcases to pick");
                int number = scanner.nextInt();
                while(number<0 || number > 26 || !remainCases.contains(number)){
                    System.out.println("Please select number between 1 to 26 or unselected number");
                    number = scanner.nextInt();
                }
                this.remainCases.remove(number);
                System.out.println("This case has "+ find_amount(number));
                this.remainAmounts.remove(find_amount(number));

            }

        }

        else if (this.turn == 3){
            for (int i=0;i<5;i++){
                System.out.println("Please select suitcase to eliminate. you have " +(5-i)+ " suitcases to pick");
                int number = scanner.nextInt();
                while(number<0 || number > 26 || !remainCases.contains(number)){
                    System.out.println("Please select number between 1 to 26 or unselected number");
                    number = scanner.nextInt();
                }
                this.remainCases.remove(number);
                System.out.println("This case has "+ find_amount(number));
                this.remainAmounts.remove(find_amount(number));

            }

        }

        else if (this.turn == 4){
            for (int i=0;i<3;i++){
                System.out.println("Please select suitcase to eliminate. you have " +(3-i)+ " suitcases to pick");
                int number = scanner.nextInt();
                while(number<0 || number > 26 || !remainCases.contains(number)){
                    System.out.println("Please select number between 1 to 26 or unselected number");
                    number = scanner.nextInt();
                }
                this.remainCases.remove(number);
                System.out.println("This case has "+ find_amount(number));
                this.remainAmounts.remove(find_amount(number));
            }
        }
        else if(this.turn == 10){
            System.out.println("your stay with your first suitcase! you earned "+find_amount(this.yourCase));
        }

        else{
                for (int i=0;i<1;i++){
                    System.out.println("Please select suitcase to eliminate. you have 1 suitcase to pick");
                    int number = scanner.nextInt();
                    while(number<0 || number > 26 || !remainCases.contains(number)){
                        System.out.println("Please select number between 1 to 26 or unselected number");
                        number = scanner.nextInt();
                    }
                    this.remainCases.remove(number);
                    System.out.println("This case has "+ find_amount(number));
                    this.remainAmounts.remove(find_amount(number));
                }
            }
        }

        // find the amounts from the suitcase number
        public double find_amount(int suitcase){
            return this.suitcases.get(suitcase);
        }

        // show all board remain amounts
        public TreeSet<Double> showBoardAmounts() {
            TreeSet<Double> sortedAmounts = new TreeSet<>(this.remainAmounts);
            return sortedAmounts;
    }
    // show all board remain auitcases
    public TreeSet<Integer> ShowBoardSuitCases(){
            TreeSet<Integer> sortedSuitCases = new TreeSet<>( this.remainCases);
            return sortedSuitCases;

    }
    // show all offers that the banker offer
    public ArrayList<Double> ShowOffers(){
            return this.offers;

        }

}
