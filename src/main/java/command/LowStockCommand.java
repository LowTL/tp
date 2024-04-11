package command;

import reminder.LowStockReminder;

public class LowStockCommand extends Command{
    @Override
    public void execute() {
        LowStockReminder.execute();
    }
}
