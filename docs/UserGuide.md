# User Guide

## Introduction

StockMaster is a platform aimed at helping SMEs track and organise their inventory.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `StockMaster` from [here](http://link.to/duke).
3. Run the jar file

## Features 


### Adding an item: `add`
Adds a new item to the list of items.

Format: `add ITEM_NAME qty/ITEM_QUANTITY /UNIT_OF_MEASUREMENT [cat/CATEGORY] buy/[BUY_PRICE] sell/[SELL_PRICE]`

* `CATEGORY` is an optional field. If blank, it will default to `N/A`.

> [ !NOTE ]
> 
> Addition of an item with the same `ITEM_NAME` will edit the `ITEM_QUANTITY` instead

Example of usage: 
```
add Apple qty/50 /pieces cat/fruits buy/4 sell/5
add Phone qty/5 /pieces cat/Electronics buy/100 sell/500
```

### Deleting an item: `del`
Deletes the item from the list of items.

Format: `del ITEM_NAME`

Example of usage:
```
del Apples
```

### Editing an item: `edit`
Edits the parameters of the item.

Format: `edit [ITEM_NAME] name/[NEW_NAME] qty/[NEW_QUANTITY] uom/[NEW_UOM] cat/[NEW_CATEGORY] buy/[NEW_BUY_PRICE] sell/[NEW_SELL_PRICE]`<br/>`

Example of usage:
```
`edit apple name/green apple qty/10 uom/pieces cat/fruit buy/1.00 sell/2.00`
`edit fish name/Salmon qty/1 uom/pieces cat/fish device buy/1.00 sell/10.00`
```
User can choose to edit at least 1 parameter up to all available parameters.

Example of usage:
```
edit apple buy/1.00 sell/2.00
edit fish name/Salmon qty/1 cat/fish
```

### Finding an item: `find`
Finds all items that contains `KEYWORD` 

Format: `find [/filter1/filter2] KEYWORD`

* Filter will specify the parameters that is being searched.
* If no filter is applied, it will search all items that contains `KEYWORD.
* The search is case-insensitive e.g. `apple` will match `Apple`.
* Partial words will be matched e.g. `app` will match `Apple`.

Example of usage:
```
find /qty/cat Apple //search for `Apple` under `ITEM_QUANTITY` and `CATEGORY`
find Apple //search all items that contains `Apple`
```

### Listing all items: `list`
Lists all stored items.

Format: `list [cat/CATEGORY]`

* `CATEGORY` is an optional field. By default, it will list all the stored items.

Example of usage:
```
list
list Electronics
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

### Getting the bestseller: `bestseller`
Gets the item that has made the most profits.

Format: `bestseller`


### Getting total profits: `profit`
Gets the total profits made by all transactions.

Format: `profit`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Simply copy and paste the saved folder that is created upon launch of 
the application.

## Command Summary

| Action      | Format, Examples                                                                                                                                                                                                      |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add Item    | `add ITEM_NAME qty/ITEM_QUANTITY /UNIT_OF_MEASUREMENT [cat/CATEGORY] buy/[BUY_PRICE] sell/[SELL_PRICE]`<br/> `e.g. add apple qty/50 /pieces cat/fruits buy/4.50 sell/5`                                               |
| Delete Item | `del ITEM_NAME`<br/> `e.g. del Apple`                                                                                                                                                                                 |
| Edit Item   | `edit [ITEM_NAME] name/[NEW_NAME] qty/[NEW_QUANTITY] uom/[NEW_UOM] cat/[NEW_CATEGORY] buy/[NEW_BUY_PRICE] sell/[NEW_SELL_PRICE]`<br/>`e.g. edit apple name/green apple qty/10 uom/pieces cat/fruit buy/1.00 sell/2.00` |
| Find Item   | `find KEYWORD`<br/> `e.g. find University`                                                                                                                                                                            |
| Sell Item   | `sell [ITEM_NAME] qty/[SELL_QUANTITY] price/[SELL_PRICE]`<br/> `e.g. sell apple qty/50 price/4.50`                                                                                                                    |
| List        | `list`                                                                                                                                                                                                                |
| Help        | `help`                                                                                                                                                                                                                |
| Exit        | `exit`                                                                                                                                                                                                                |
| Bestseller | `bestseller` |
| Get Total Profit | `profit` |
| Get Total Revenue | `revenue` |
