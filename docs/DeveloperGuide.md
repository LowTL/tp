# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Exception
### Itemlist
Itemlist class is an object which contains items to be added to the stock inventory list. Able to add items, remove functions, edit items inside
the list.
### Item
Item class is an object which represents an item in the stock inventory list. Stores data about the item such as item price, 
quantity of item, and others.
### Parser
Parser class processes user inputs and sieves out relevant details before calling the relevant methods.
It contains command formats that must be adhered to for the methods to be called.
### Storage
Storage class contains methods to write description of items to the file `./StockMasterData.txt`, 
and retrieve information from the file when program restarts.
### UI


## Product scope
StockMaster allows users to use the following commands:
* add: add an item, quantity, category, buy price and sell price
* del: delete an item
* edit: edit the quantity of an item
* sell: sell a certain quantity of an item at a stated price
* find: find a specific item using a keyword
* list: list all items in the inventory
* help: list all commands
* exit: exit StockMaster

### Target user profile

Small Business Owners who:
* has a need to manage a significant number of inventory products
* able to track revenue/loss of the business
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

### Value proposition

Help small business owners organise and better manage their inventory faster than 
a typical mouse/GUI driven app


## User Stories

|Version| As a ... | I want to ...               | So that I can ...                                          |
|--------|----------|-----------------------------|------------------------------------------------------------|
|v1.0|new user| see usage instructions      | refer to them when I forget how to use the application     |
|v1.0|user| add new items               |                                                            |
|v1.0|user| make changes to added items | change details about items such as quantity or price       |
|v1.0|user| search for specific items   ||
|v2.0|user| find a to-do item by name   | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *CLI* - Command Line Interface, where the user types commands rather than clicking options.
* *Item* - Item to be sold at the shop, with key information such as quantity, buying/selling price, description etc.

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
