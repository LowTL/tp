# Heng Shu Hong's Project Portfolio Page


## Project: StockMaster

StockMaster is  a desktop inventory and management that provides the user with a range of tools and features to help them
manage and operate their business.


### Code Contribution

- **New Feature: Added the capability to add/delete/list promotions**
    - What it does: allows the user to add a Promotion for a certain ITEM by entering the discount, startDate,
      endDate, startTime and endTime. When a Promotion is added for an ITEM, and the store owner uses the sell
      feature in the application, the discount will be immediately applied to the sell price and the discounted price will
      be shown if it is within the promotion period.
    - Justification: This feature improves the application as the user now have the option to create a discount campaign
      and make adjustments to the sell price automatically rather than having to manually use the edit feature.
    - Highlights: This enhancement was challenging as it involved the integration with the sell command. In particular,
      one difficulty was to ensure that the original price of the ITEM would not be changed even during a promotion period. 
      Another difficulty is to check if the item is within the promotion period for the given date and time.


- **New Feature: Enhanced the find command to allow the user to search based on the specified item information**
    - What it does: allows the user to look through a filtered list to find the ITEM information based on the KEYWORD
      For instance, the user can find all ITEMS that has a buy price or sell price of $`1`.
    - Justification: This feature increases the capabilities of the search function, as it can allow the user to quickly
      retrieve the data that is being searched for by narrow the scope of the search.
    - Highlights: The difficulty in this enhancement was the approach to implement this improvement. This is because this
      enhancement is an extension of the previous find command, meaning that it must still be able to retain its original
      feature, but yet still able to provide more than 1 filter to narrow the scope.


- **New Feature: Enhanced the add command to prevent duplicate entries**
    - What it does: It prevents the user from accidentally inputting duplicated items. Instead, when the user adds a
      duplicated item, the item information will be updated based on the latest input and the quantity will be increased.
    - Justification: This feature enables the user to not have duplicate entries of the same items to avoid the
      overflowing of the inventory list with duplicated item.

- **Code Contributed:** [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=hengshuhong&breakdown=true)

- **Documentation**
    - User Guide:
        - Add documentation for the features `add`, `find`, `promotion`, `del_promo` and `list_promotions` [#86](https://github.com/AY2324S2-CS2113-T15-4/tp/pull/86)
        - Add the Quick Start
        - Update the Command Summary

    - Developer Guide:
        - Add implementation details of the `promotion` feature. [#176](https://github.com/AY2324S2-CS2113-T15-4/tp/pull/176)
        - Update Product Scope [#181](https://github.com/AY2324S2-CS2113-T15-4/tp/pull/181/files)
        - Add non-functional requirements. [#181](https://github.com/AY2324S2-CS2113-T15-4/tp/pull/181/files)

- **Review Contributions**
    - Fix bug for team members that caused the application to crash. [#184](https://github.com/AY2324S2-CS2113-T15-4/tp/pull/184)
    - Fix test case that failed [#187](https://github.com/AY2324S2-CS2113-T15-4/tp/pull/187)
    - Review team's UML Diagrams