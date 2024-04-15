# Min Guanlin - Project Portfolio Page

## Project: StockMaster

StockMaster is  a desktop inventory and management that provides the user with a range of tools and features to help them
manage and operate their business.

### Code Contribution
- **New Feature**: Added the ability to:
   - List all items,
   - List all items of a certain category,
   - List all marked items,
   - List all marked items of a certain category.
  - **What it does**: Allows user to list items in the item list according to the user needs.
  - **Justification**: This feature is crucial for StockMaster, as the main purpose of our application is for store owners
to keep track of their inventory. It also provides a convenient way for users to check a item of a certain category as 
they may have many items in the whole item list, making it hard to search for specific items. In addition, users might 
only want to see their marked items. The mark feature is explained below.

- **New Feature**: Added the ability to mark and unmark specific items in the item list.
   - **What it does**: Allows users to mark specific item in the item list. The user can also unmark a marked item.
   - **Justification**: Users might want to mark items for many reasons, such as to keep track of their sales as they 
  may be a high in demand item. These items might also be from different categories, so user cannot list them by 
  category. This feature hence provides a convenient way for users to access items they want to keep an eye on.

- **New Feature**: Added the ability to edit current item parameters.
  - **What it does**: Allows users to edit specific parameters of existing items, such as quantity, category, name, etc.
  - **Justification**: This feature is crucial to our application as it allows users to rectify user errors when 
  adding items to the item list. In addition, it allows the user to change the items to reflect accurately the status 
  of each item. For example, the user may want to change the selling price of specific items due to a anticipation of a
  surge in demand.
  - **Highlights**: The implementation was challenging as it required integration with `Itemlist` as well as `item` to 
  ensure that the parameters are edited accurately. In addition, this enhancement allows users to edit multiple 
  parameters at once, which increases convenience for the user.

- **Code Contributed** : [RepoSense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=fureimi&breakdown=true)
  
- **Documentation**:
   - User Guide:
      - Added documentation for the features `list_items`, `mark`, `unmark` and `edit`
      - Update the command summary.
   - Developer Guide:
      - Added class diagram of the `EditCommand` class.
      - Added implementation details and sequence diagram of both the `list_items` and `edit` features.
