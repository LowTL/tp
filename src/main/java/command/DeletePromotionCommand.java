//@@author HengShuHong
package command;

import common.Messages;
import exceptions.CommandFormatException;
import promotion.Promotion;
import promotion.Promotionlist;
import storage.PromotionStorage;
import ui.TextUi;

public class DeletePromotionCommand extends Command {

    protected String itemName;

    public DeletePromotionCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Deletes a promotion from the promotion list
     *
     * @throws CommandFormatException is thrown when the promotion for the item does not exists
     */
    @Override
    public void execute() throws CommandFormatException {
        if (Promotionlist.itemIsOnPromo(itemName)) {
            Promotion promotion = Promotionlist.getPromotion(itemName);
            int index = Promotionlist.getIndex(promotion);
            Promotionlist.deletePromotion(index);
            TextUi.replyToUser("Promotion for " + itemName + " has been removed");
            PromotionStorage.overwritePromotionFile(Promotionlist.getAllPromotion());
        } else {
            System.out.println(Messages.ITEM_NOT_ON_PROMO);
        }
    }
}
