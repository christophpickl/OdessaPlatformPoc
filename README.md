# OdessaPlatformPoc

Proof-of-Concept for the Odessa platform idea.

## Use Cases

* I log in as a space-pirate, space-holder or as an admin.
* As a an admin ...
  * I can manage events.
  * I can manage users.
* As a spacepirate ...
  * I can assign tokens to space-holders for a specific event.
* As a space-holder ...
  * I can check how many tokens I have and from which event.
  * I can check how many tokens an event costs to participate.

## Todos

Christoph:
* N/A

Yana:
* use includes in templates

## How to write templates

* See FreeMarker templates: `src/main/resources/templates`
* See code interface in: `src/main/kotlin/odessa/templates`
* Execute `./run.sh` to start the server

## Tech Stack

* Programming Language: Kotlin
* Webserver: Ktor
* Templating engine: [Freemarker](https://freemarker.apache.org/)
** Alternatives: Velocity, Thymeleaf
