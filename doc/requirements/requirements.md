# Requirements

**Overall goal**: Spaceholders can volunteer at events and receive tokens ("token economy") for that.
These can be used to exchange as an entrance ticket to other events.

**Overall description**: Spaceholders are like regular user. They are grouped into teams, and each team
has a team leader, a so-called "Spacepirate". Only a spacepirate can assign spaceholders to events (receive tokens).
A third group of users, admins, have the most privileges, can manage users/teams/events and freely manipulate tokens.

**Additional feature**: In order to plan enough presence of spaceholders during an event, each spaceholder can assign
himself on a list of "I will be there". This list will be then afterwards used by the spacepirate (with some additional
fine-tuning) to come up with the final "payout list".

## Use Cases

* Scenario: Check token
    * Given I'm logged in as a spaceholder
    * When I go to the main screen
    * Then I see my total balance of tokens
    * And I see a history of tokens received/spent
    * And I see events along with their token price

* Scenario: Register planned attendance
    * Given I'm logged in as a spaceholder
    * When I select the next available event
    * Then I can register to be present

* Scenario: Token payout
    * Given I'm logged in as a spacepirate
    * When I go to the most recent passed event
    * Then I can change and confirm attendance of spaceholders
    * And each user's token balance and history has been changed

* An admin can manage (CRUD/link) users, teams, events; and do manual token modifications.

![Sketch 1](sketch1.jpg "A first sketch")
