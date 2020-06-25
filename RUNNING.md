# Körning #

## Konfiguration ##

Ändra filerna under `config/`.

Det är inte nödvändigt att sätta upp en databas för applikationen
använder [H2](https://www.h2database.com/html/main.html) som standard,
som är en in-memory databas.

För produktion rekommenderas MySQL / MariaDB.

## Start ##

    $ java -jar dg-quiz-<version>.jar

För att använda GNU Screen:

    $ screen<RETURN>
    $ java -jar dg-quiz-<version>.jar
    Detach: CTRL+A+D

För att återuppta GNU Screen:

    $ screen -r

## Åtkomst ##

Som standard lyssnar Spring Boot-applikationen på port 6969 vilket är
möjligt att ändra under mappen `config/`. Så surfa in på adressen
`http://localhost:6969` för att använda applikationen.
