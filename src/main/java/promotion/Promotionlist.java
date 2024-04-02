package promotion;

import java.util.ArrayList;

public class Promotionlist {
    private static final ArrayList<Promotion> promotions = new ArrayList<>();

    public Promotionlist() {
    }

    public static void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public static void deletePromotion(int index) {
        promotions.remove(index);
    }

    public static boolean itemIsOnPromo(String itemName) {
        for (Promotion promotion : Promotionlist.getPromo()) {
            if (promotion.getItemName().toLowerCase().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Promotion> getPromo() {
        return promotions;
    }
}
