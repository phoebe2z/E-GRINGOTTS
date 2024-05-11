import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Date;


public class Card {
    private String cardNum;
    private int cVV;
    private Date expiryDate;
    private String userId;
    private static List<String> generatedCardNums = new ArrayList<>();
    Random random = new Random();


    public Card(String userId) {
        this.userId = userId;
        generateCardNum();
        generateCVV();
        generateExpiryDate();
    }

    public String getCardNum() {

        return cardNum;
    }

    public void generateCardNum() {
//        generate a 16-digit card number
        StringBuilder stringBuilder = new StringBuilder();
        do {
            for (int i = 0; i < 16; i++) {
                stringBuilder.append(random.nextInt(10));
            }
        } while (generatedCardNums.contains(stringBuilder.toString()));
//        avoid duplicate card numbers
        this.cardNum = stringBuilder.toString();
        generatedCardNums.add(cardNum);
    }

    public int getCVV() {

        return cVV;
    }

    public void generateCVV() {
//generate a 3-digit cvv
        this.cVV = random.nextInt(900) + 100;
    }

    public String getExpiryDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM / YY");

        return dateFormat.format(expiryDate);
    }

    public void generateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
//        the card will expire in 10 years

        calendar.add(Calendar.YEAR, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        this.expiryDate = calendar.getTime();
    }

    public boolean isExpired() {
        return expiryDate.before(new Date());
    }

    public void updateExpiryDate() {

        Calendar currentCalendar = Calendar.getInstance();
        Calendar expiryCalendar = Calendar.getInstance();
//            user can update the expiration time within three months before the expiration date
        currentCalendar.add(Calendar.MONTH, 3);
        if (currentCalendar.after(expiryDate)) {
            expiryCalendar.setTime(expiryDate);
//            Update expiry date by 20 years
            expiryCalendar.add(Calendar.YEAR, 20);
            expiryDate = expiryCalendar.getTime();
        } else {
            System.out.println("Your card will expire for a long time so don't need to update it yet.");
        }

    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Card{" + "cardNum='" + cardNum + '\'' + ", cVV=" + cVV + ", expiryDate=" + expiryDate + ", userId='" + userId + '\'' + '}';
    }
}
