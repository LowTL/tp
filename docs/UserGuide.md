# User Guide

## Introduction

StockMaster is a desktop inventory and management that provides the user with a range of tools and features to help them
manage and operate their business. It is targeted at those who can type fast and prefer Command Line Interface (CLI) application.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `StockMaster` from [here](https://github.com/AY2324S2-CS2113-T15-4/tp/releases/tag/v2.1b).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar CS2113-T15-4.StockMaster.jar` command to run the application.

## Features

### Adding an item: `add`
Adds a new item to the list of items.

Format: `add ITEM_NAME qty/ITEM_QUANTITY /UNIT_OF_MEASUREMENT [cat/CATEGORY] buy/BUY_PRICE sell/SELL_PRICE`

* `CATEGORY` is an optional field. If blank, it will default to `N/A`.

> [ !NOTE ]
> 
> Addition of an item with the same `ITEM_NAME` will increase the `ITEM_QUANTITY`,  and update the `UNIT_OF_MEASUREMENT`, 
> `CATEGORY`, `BUY_PRICE` and `SELL_PRICE` instead of adding a duplicated entry for the same item.

Example of usage: 
```
add apple qty/50 /pieces cat/fruits buy/4 sell/5
add phone qty/5 /pieces cat/Electronics buy/100 sell/500
```

### Listing items: `list_items`
Lists stored items according to what user requests.

Format: `list_items [marked] [cat/CATEGORY]`

* By default, `list_items` on its own will list all stored items.
* `marked` is an optional field. If used, it will only list marked items.
* `CATEGORY` is an optional field. If used, it will only list items of that category.

Example of usage:
```
list_items
list_items cat/Electronics
list_items marked cat/fruits
```
Example output:
```
1. [ ] apple (Qty: 50 pieces, Buy: $4.0, Sell: $5.0, Category: fruits)
```
_**Note**_<br>
- _marked **MUST** be before cat/[CATEGORY]_
- The `[ ]` before each item represents if an item is marked or not. More info on mark feature can be found below.

### Deleting an item: `del`
Deletes the item from the list of items.

Format: `del ITEM_NAME`

Example of usage:
```
del apples
```

### Selling an item: `sell`
Sells a quantity of an item from the list of items at a stated price.

Format: `sell ITEM_NAME qty/SELL_QUANTITY`


Example of usage:
```
sell apple qty/20 
```

### Marking an item: `mark`
Marks items in the inventory list.

Format: `mark ITEM_NAME`

Example of usage:
```
mark apple iphone
```

### Unmarking an item: `unmark`
Unmarks a marked item in the inventory list.

Format: `unmark ITEM_NAME`

Example of usage:
```
unmark apple iphone
```

### Editing an item: `edit`
Edits the parameters of the item.

Format: `edit ITEM_NAME [name/NEW_NAME] [qty/NEW_QUANTITY] [uom/NEW_UOM] 
[cat/NEW_CATEGORY] [buy/NEW_BUY_PRICE] [sell/NEW_SELL_PRICE]`<br/>

Example of usage:
```
edit apple name/green apple qty/10 uom/pieces cat/fruit buy/1.00 sell/2.00
edit fish name/Salmon qty/1 uom/pieces cat/fish device buy/1.00 sell/10.00
```
User can choose to edit at least 1 parameter up to all available parameters.

Example of usage:
```
edit apple qty/45 buy/3.50 sell/5.50
edit fish name/Salmon qty/1 cat/fish
```

Example Output:
```
Edited: 
Quantity of apple from 50 to 45
Buy Price of apple from 4.0 to 3.5
Sell Price of apple from 5.0 to 5.5
End of Edits
```

### Finding an item: `find`
Finds all items that contains `KEYWORD` 

Format: `find [/FILTER1/FILTER2] KEYWORD`

* Filter are optional and will specify the parameters that is being searched.
* Filters can be `item`, `qty`, `cat`, `uom`, `buy`, `sell`. Any other values will show an empty list.
* If no filter is applied, it will search all items that contains `KEYWORD.
* The search is case-insensitive e.g. `apple` will match `Apple`.
* Partial words will be matched e.g. `app` will match `Apple`.

Example of usage:
```
find /qty/cat Apple // search for `Apple` under `ITEM_QUANTITY` and `CATEGORY`
find Apple // search all items that contains `Apple`
```



### Get bestselling item: `bestseller`.
Reads all the Transactions and returns the item with the highest profit.

Format: `bestseller`

Example of usage:
```
bestseller
```

Expected output:
```
The current best-selling item is {ITEM_NAME}.
```

### Get total profits: `total_profit`
Reads all the Transactions and returns the total profits.

Format: `total_profit`

Example of usage:
```
total_profit
```

Expected output:
```
You have earned {PROFIT} in profits so far. 
```

### Get total revenue: `total_revenue`
Reads all the Transactions and returns the total profits.

Format: `total_revenue`

Example of usage:
```
total_revenue
```

Expected output:
```
You have earned {REVENUE} in revenue so far. 
```

### Add promotion to items: `promotion`
Creates a promotion for items that changes the sell price.

Format: `promotion ITEM_NAME discount/DISCOUNT period /from DAY MONTH YEAR /to DAY MONTH YEAR
time /from START_TIME /to END_TIME`

* `DISCOUNT` ranges from 0 to 100 and can take in up to 2 decimal place.
*  Format for `MONTH` is 3 alphabets `MMM`. E.g. For January, `Jan`. For December, `Dec`.  
* `DAY` must be a valid for the specific `[MONTH]` E.g. `30 Feb 2024` is not allowed.
* `START_TIME` & `END_TIME` must range from 0000 to 2359. In addition, time range must be valid.

Example of usage:
```
promotion apple discount/50 period /from 2 Apr 2024 /to 4 Apr 2025 time /from 0000 /to 2359
```
### Delete a promotion: `del_promo`
Deletes a promotion for an item.

Format: `del_promo ITEM_NAME`

Example of usage:
```
del_promo apple
```
### List promotions: `list_promotions`
List all the promotions created.

Format: `list_promotions`

Example of usage:
```
list_promotions
```

### Low Stock Reminder: `low_stock`
lists all out-of-stock items and low-in-stock items. </br>
An item is considered low-in-stock if its quantity is below the stated threshold amount.

Format: `low_stock /AMOUNT`

Example of usage:
```
low_stock /15
```

### List all available commands: `help`
Lists all commands as per the command summary below.

Format: `help [c/COMMAND]`

* `COMMAND` is optional. Specifying the command will give a more comprehensive
  description of the command.

Example of usage:
```
help
help c/add
```


### Closing the app: `exit`
Saves and closes the app safely.

Format: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Simply copy and paste the saved folders that is created upon launch of 
the application.

## Command Summary

| Action             | Format, Examples                                                                                                                                                                                                          |
|--------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add item           | `add ITEM_NAME qty/ITEM_QUANTITY /UNIT_OF_MEASUREMENT [cat/CATEGORY] buy/BUY_PRICE sell/SELL_PRICE`<br/> `e.g. add apple qty/50 /pieces cat/fruits buy/4.50 sell/5`                                                       |
| Delete item        | `del ITEM_NAME`<br/> `e.g. del Apple`                                                                                                                                                                                     |
| Edit item          | `edit ITEM_NAME [name/NEW_NAME] [qty/NEW_QUANTITY] [uom/NEW_UOM] [cat/NEW_CATEGORY] [buy/NEW_BUY_PRICE] [sell/NEW_SELL_PRICE]`<br/>`e.g. edit apple name/green apple qty/10 uom/pieces cat/fruit buy/1.00 sell/2.00`      |
| Find item          | `find [/FILTER] KEYWORD`<br/> `e.g. find University`                                                                                                                                                                      |
| Sell item          | `sell ITEM_NAME qty/SELL_QUANTITY `<br/> `e.g. sell apple qty/50`                                                                                                                                                         |
| Mark item          | `mark ITEM_NAME`                                                                                                                                                                                                          |
| Unmark item        | `unmark ITEM_NAME`                                                                                                                                                                                                        |
| List Inventory     | `list_items [marked] [cat/CATEGORY]`                                                                                                                                                                                      |
| Get Best Seller    | `bestseller`                                                                                                                                                                                                              |
| Get Profit         | `total_profit`                                                                                                                                                                                                            |
| Get Revenue        | `total_revenue`                                                                                                                                                                                                           |
| Create Promotion   | `promotion ITEM_NAME discount/DISCOUNT period /from DATE MONTH YEAR /to DATE MONTH YEAR time /from TIME /to TIME`<br/> `e.g. promotion apple discount/50 period /from 2 Apr 2024 /to 4 Apr 2024 time /from 1200 /to 1500` |
| Delete Promotion   | `del_promo ITEM_NAME` <br/> `e.g. del_promo apple`                                                                                                                                                                        |
| List Promotion     | `list_promotions`                                                                                                                                                                                                         |
| Low Stock Reminder | `low_stock /AMOUNT`                                                                                                                                                                                                       |
| Help               | `help [c/COMMAND]`                                                                                                                                                                                                        |
| Exit               | `exit`                                                                                                                                                                                                                    |
