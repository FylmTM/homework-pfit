# Homework

Insurance goes brrrr.

## Prerequisites

* Java 14

## Notes

**Why no framework/DI library is used?**

I have made conscious decision to stick with pure Java (no libraries) just
for this homework. Some places would look much nicer with DI, but hey! 
At some point in life you might find yourself on an inhabitable island without internet access,
with only JDK 14 available on your laptop, and that's all you got. Practice now, be prepared.

**Any code quality thoughts?**

Apart from just not writing strange things, there are number of automated tools to keep code
quality under control (code coverage, linting, static analysis). They are not in scope for configuring for this homework.
I promise to keep my code formatted manually!

## Assumptions

**Ignore policy status**

We are implementing premium calculator logic, not "do we need to calculate premium?" logic.
Because there is no extra-logic specified for status handling, we are ignoring this.
However, we should ask analyst about this point, just to be extra sure there is no
specific behaviour (e.g. if status is registered - return zero or throw exception) needed.

**No runnable application**

We are implementing business logic. Please, take look at tests to see behaviour.

**All amounts are EUR-based**

There is no currency handling. To introduce one we would need to have exchange rates available to normalize
amounts before comparing to limits.

## Design decisions

**Non-generalized premiums with a coefficient**

Premiums that are based on sum of insured sums with coefficients could be implemented in generic way.
However, this makes sense only if we have a significant premiums that works like that.
I did introduce coefficient-based premiums, but each one is responsible for calculating coefficient on its own.

This helps to avoid weird coupling in long term, when more sophisticated coefficient calculations will be introduced.

We could introduce something like "insured sum to coefficient" helper, if it will make sense.

## TODO

* [x] Setup project
* [x] Add test harness
* [x] Add base domain
* [x] Figure out nice way of summing up insured sums
* [x] Figure out nice way of matching risk type premium calculation & coefficient
    * [x] Think about do we need special uniform way of handling coefficient steps?
* [x] Externalize coefficients
* [x] Ensure tests on...
    * [x] Summing sub-objects
    * [x] Fire risks premium calculation
    * [x] Theft risks premium calculation
    * [x] Total premium calculation
* [x] Ensure design...
    * [x] We can easily add risk
    * [x] We can have more than 1 step for coefficient
    * [x] We can have more complicated coefficient calculations (not just sum greater than)
    * [x] We can easily add premium
    * [x] Support coefficient-based premiums
    * [x] Possibility to implement arbitrary premiums
* [x] Think about...
    * [x] What is relationship between premium and risk? One-to-one? One-to-many? Many to one? Many-to-Many?
    * [x] Do we want flexible way of summing up, or hardcode sub-object summing by type?
    * [x] Object hierarchy is kinda hardcoded now to explicit 3 levels. What if this is going to change?
    * [x] Do I want to convert RiskType to class?
    * [x] Getting coefficient can be generalized (including default one), but do we want to id?
    * [x] Should there be a way for a calculator to ask which premiums to use in calculation?
* [ ] Improvements
    * [ ] Add policy builder (to make creating test data more pleasant)
    * [ ] Actually introduce DI and get rid of manually constructing dependency graph and injecting configuration
