public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to DEAL OR NO DEAL");
        Game game = new Game();
        game.init_game();
        System.out.println("Board amounts:");
        System.out.println(game.showBoardAmounts());
        System.out.println();
        System.out.println("Board suitcases:");
        System.out.println(game.ShowBoardSuitCases());
        System.out.println();
        game.selectYourCase();
        for(int i=0;i<10;i++){
            game.pickNumbers();
            System.out.println("Board amounts:");
            System.out.println(game.showBoardAmounts());
            System.out.println();
            System.out.println("Board suitcases:");
            System.out.println(game.ShowBoardSuitCases());
            double offer = game.getOffer();
            System.out.println("Banker offer: "+offer);
            System.out.println("Last offers: "+game.ShowOffers());
            boolean decision = game.dealOrNoDealQuestion(offer);
            if(decision==false && game.ShowBoardSuitCases().size()!=1){
                continue;
            }
            else if(decision==false && game.ShowBoardSuitCases().size()==1){
                double caseMoney = game.find_amount(game.getYourSuitCaseNum());
                System.out.println("Your suitcase has " +caseMoney);
                if (offer < caseMoney){
                    System.out.println("you Made a great deal!");
                }
                else
                    System.out.println("you Made a bad deal!");
                break;
            }
            else
                break;
        }
    }
}
