public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Card card = new Card("dsf");
        System.out.println("Expiry Date: " + card.getExpiryDate());
        System.out.println(card.getCardNum());
        System.out.println(card.getCVV());
    }
}