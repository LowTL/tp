## Product Overview

The product that my group created is called StockMaster. StockMaster helps small business owners organise and manage 
their business. The purpose of such application is to provide users with a range of tools and features to help them 
better operate their business.

## Code Contribution

### Enhancement Implemented

#### Promotion Feature

During version `v2.0`, I had implemented a `Promotion` feature inside our application. A `Promotion` is when there is a 
`discount` on a certain item within a certain range.  This `Promotion` feature works similarly to how an actual 
promotion will happen.

As a store owner, he/she is able to add a `Promotion` for a certain `ITEM` by entering the `discount`, `startDate`, 
`endDate`, `startTime` and `endTime`. When a `Promotion` is added for an `ITEM`, when the store owner uses the sell
feature in the application, the discount will be immediately applied to the `sell price` and the discounted price will 
be shown. 

In addition, this feature takes in the `Time` and `Date`, such that an `ITEM` on promotion will only be discounted when 
it is within the promotion `Date/Time`. 

For instance, if a promotion was created for `apples`, at a `20`% discount, from `11 Apr 2024` to `13 Apr 2024`, 
`1200 to 1800` hours, the user can input the following into the application to achieve the above.

```
promotion apples discount/20 period /from 11 Apr 2024 /to 13 Apr 2024 time /from 1200 /to 1800
```
The promotion will be successfully created. 




This is the [link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=hengshuhong&breakdown=true) to my code contribution for StockMaster

