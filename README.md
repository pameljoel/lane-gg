# lol-app

This project is generated with [yo angular generator](https://github.com/yeoman/generator-angular)
version 0.15.1.

## concept

Lolgame.gg is a web application which aims to provide useful information about a summoner's teammates and adversaries.

It allows players to check their odds of winning their next match based on the public information available to players.

It checks the level of competency of each player based on the information available, such as mastery level with that champion, best grade on that champion and total champion mastery score. Also you can see if the currently playing champion is one of the player's 3 most played champions.

This web application is meant as a mean to display champion mastery data in a meaningful and easy to read way.

The aim of this web application is not to give the true result of a match, but to let the user know what are the starting odds of winning, who are the greatest threats and the weakest links in match, so they can adjust their gameplay accordingly without giving them unfair advantages. 

## Build & development

Run `grunt` for building and `grunt serve` for preview.

## Testing

Running `grunt test` will run the unit tests with karma.


## Backend technical Todo list

* [x] Introduce rate limiting
* [ ] Improve rate limiting
* [x] Use spring DeferredResult in controllers
* [ ] Serve the frontend app
* [ ] Better error handling
* [ ] Introduce caching
* [ ] Make jackson work with kotlin data classes
* [ ] Use an async http client
*
