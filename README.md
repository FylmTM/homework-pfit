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

**Risk <-> Premium relationship is one-to-one**

Current implementation assumes that relationship is one-to-one.
If that is not the case, we need to sit down with an analyst and discuss how we can match
risk type(s) with appropriate premium calculation logic.

**Ignore policy status**

We are implementing premium calculator logic, not "do we need to calculate premium?" logic.
Because there is no extra-logic specified for status handling, we are ignoring this.
However, we should ask analyst about this point, just to be extra sure there is no
specific behaviour (e.g. if status is registered - return zero or throw exception) needed.

**No runnable application**

We are implementing business logic. Please, take look at tests to see behaviour.

## Design decisions

**Non-generalized premiums with a coefficient**

Premiums that are based on sum of insured sums with coefficients could be implemented in generic way.
However, this makes sense only if we have a significant premiums that works like that.
I did introduce coefficient-based premiums, but each one is responsible for calculating coefficient on its own.

This helps to avoid weird coupling in long term, when more sophisticated coefficient calculations will be introduced.

We could introduce something like "insured sum to coefficient" helper, if it will make sense.

**All amounts are EUR-based**

There is no currency handling. To introduce one we would need to have exchange rates available to normalize
amounts before comparing to limits.

## TODO

* [x] Setup project
* [x] Add test harness
* [x] Add base domain
* [ ] Figure out nice way of summing up insured sums
* [ ] Figure out nice way of matching risk type premium calculation & coefficient
    * [ ] Think about do we need special uniform way of handling coefficient steps?
* [ ] Externalize coefficients
* [ ] Ensure tests on...
    * [ ] Summing sub-objects
    * [ ] Fire risks premium calculation
    * [ ] Theft risks premium calculation
    * [ ] Total premium calculation
* [ ] Ensure design...
    * [ ] We can easily add risk
    * [ ] We can have more than 1 step for coefficient
    * [ ] We can have more complicated coefficient calculations (not just sum greater than)
    * [ ] We can easily add premium
    * [ ] Premium dictates summing logic
* [ ] Think about...
    * [ ] What is relationship between premium and risk? One-to-one? One-to-many? Many to one? Many-to-Many?
    * [ ] Do we want flexible way of summing up, or hardcode sub-object summing by type?
    * [ ] Object hierarchy is kinda hardcoded now to explicit 3 levels. What if this is going to change?
    * [ ] Do I want to convert RiskType to class?
    * [ ] Getting coefficient can be generalized (including default one), but do we want to? Depends on premiums.
