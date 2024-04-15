# Lim Jing Rong, Joel's Project Portfolio Page


## Project: StockMaster

StockMaster is  a desktop inventory and management that provides the user with a range of tools and features to help them
manage and operate their business.


### Code Contribution

- **New Feature:** Added Parser feature
    - What it does: Parses user inputs, and extracts the command word. Then, checks
      if the input matches the required format for the relevant command. It then
      trims the inputs, converts strings to lowercase, and ensures numbers are of an
      appropriate value, throwing exceptions if it fails.
    - Justification: This feature centralises the processing of the user input and 
      filters any erroneous inputs. This allows the commands themselves to be more 
      streamlined as the parameters required would be prepared and checked in advance.
    - Highlights: This feature was challenging as it had to be updated constantly throughout
      the StockMaster's development, while working closely with other team members to
      understand the requirements of their features, especially regarding exception handling.


- **New Feature:** Added the ability to sell items
    - What it does: Sells a stated quantity of an existing item and calculates the total amount
      of money earned. It then updates the remaining quantity of the item in stock.
    - Justification: This feature allows users to easily update the stock of an item when it is
      sold. It also enables the cashier and transaction classes to keep track
      of when and what items were sold, and how much they were sold for.


- **New Feature:** Added the low-stock reminder
    - What it does: It lists all items that are out of stock and low in stock. The threshold 
      for what is considered low in stock can be set by the user when calling the low stock
      command. The reminder is also run automatically during StockMaster's start-up process
      with a default low stock threshold of 10.
    - Justification: It reminds the user upon starting up the app to restock out-of-stock items
      and warn the user about the items that are running low in stock.


- **New Feature:** Detailed help command
    - What it does: An extension of the help command, it gives users more detailed information about 
      a specific command along with examples.
    - Justification: Some of the commands have many input variables, some of which are optional. 
      This feature aims to help users understand the specific requirements of the commands


- **Code Contributed:** [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=Joellimjr&tabRepo=AY2324S2-CS2113-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


- **Documentation**
    - User Guide:
        - Added  documentation for the features `del`,`sell` and `low_stock`
        - Update the command summary
    - Developer Guide:
        - Added implementation details and class diagram of the `Parser` class.
        - Added implementation details and sequence diagram of the `add` feature.


- **J-Unit Testing**
    - Added J-Unit testing:
        - ParserTest
        - AddCommandTest
        - EditCommandTest
        - HelpCommandTest
        - SellCommandTest
        - LowStockCommandTest
  