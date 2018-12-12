# Docker-workshop

Välkomna till utbildning och workshop med verktyget docker.

## Förberedelser

För att man ska kunna hänga med i workshopen behöver man ha en dator med sig med Docker installerat samt
att man gått igenom nedanstående steg innan vi startar. Skulle någon ha problem med något av dessa steg
så kommer jag finnas på plats på kontoret minst en timma innan själva workshopen drar igång. Jag skulle 
uppskatta att förberedelserna tar ca 20-40 minuter, så spara inte detta till precis innan vi drar igång.

Det ni behöver förbereda är följande:

- Installera Docker
- Ladda ner de base-images vi kommer använda
- Klona eller kopiera detta git-repo

### Installera docker

Beroende på vilket OS och vilken version du kör så kommer det vara lite olika installation. Läs noga när
du väljer din installation så att det blir rätt!

Ett problem som många PC-användare, oberoende av OS, stöter på är att man måste aktivera någonting
som heter "Virtualization Technology" (eller liknande) i BIOS innan docker fungerar.

https://docs.docker.com/engine/installation/

### Ladda ner base-images

Tyvärr har jag vid flera tillfällen upplevt att internet på kontoret är lite långsamt, så för att underlätta
själva workshoppen så är det mycket bra om ni kan ladda ner de images vi kommer använda under workshoppen redan
vid ett tidigare tillfälle. För att göra det, kör följande kommandon i en terminal:

    docker pull nginx
    docker pull postgres:9.5
    docker pull openjdk:8-jre
    
Verifiera sedan att dessa images finns lokalt med följande kommando:

    docker images
    
### Klona eller ladda ner detta repo

Detta repo kommer innehålla de exempel som vi abetar med under själva worksopen.

Har du git installerat så klonar du detta repo, annars finns det en länk på github för att ladda ner repot
som en zipfil och packa upp det på din dator.

OBS! Mer innehåll kommer läggas till i detta repo, så ifall du vill ladda ner som zip, vänta med det tills vi närmar
oss själva workshoppen.

# Agenda för workshop-kvällen

## Välkomna och introduktion

- Välkommen och snabb presentationsrunda.
- Vad är docker, hur fungerar det och varför ska man hålla på med det?
- Kort genomgång av agenda och upplägg
  - Mix mellan presentation och labbar
  - Upplägget på labbarna är väldigt fritt och syftar till att man själv söker information snarare än följa ett
fördefinierat recept. Därför är det viktigt att man frågar ifall man kör fast!
  - Mitt förslag är att man hjälper varandra två och två men att alla utför labbarna på sin dator.

## Grundläggande hantering

- Hur funkar det?
  - Docker vs. virtualisering
  - Docker host och docker for win/mac
  - Docker cli
  - Inifrån containern ser det ut som an vanlig linux host
- docker help
- docker pull -> se [Docker hub](https://hub.docker.com/explore/)
- En image --> en eller flera containrar, jmf virtualisering, maven, npm etc
- docker images, run, ps, inspect, stop, exec, logs, rm
- Mappa portar
- Sätt miljövariabler
- Kolla på [cheat sheet](./cheat_sheet.md)

## Lab 1

Bli bekväma med att hantera containrar.

Tips: använd flaggan -d i nedanstående steg

- Kolla vilken nginx-image du har lokalt med "docker images" (name:tag)
- Starta upp en nginx-container med en port 80 mappad till din host.
  - öppna en browser och surfa till din mappade port
- Starta upp ytterligare en nginx med ett namn och 80 mappad till din host
  - vad händer om du mappar till samma port?
- lista dina docker-containrar
- Starta upp ytterligare en container med en miljövariabel satt
    - tips: ge den ett namn så kan du lättare referera till den senare.
- Använd docker inspect för att verifiera att miljövariabeln är satt
- Kör kommandon inne i dockerkontainern
  - Verifiera att de miljövariabler du satt finns inne i containern
  - Kör bash inne i containern och lista miljövariabler (kommandot printenv)
  - testa kommandot "kill 1" --> (där 1 är processid för nginx) vad tror du kommer hända?
- lista alla containrar (inkl stoppade)
- ta bort alla containrar du just skapat

Tips:

- "docker ps" är din bästa vän!
- Ifall docker börjar ladda ner containrar från docker hub, dubbelkolla att du skrivit rätt namn:tag
- Kör du docker toolbox (om du kör en äldre windowsinstallation) har din docker host troligvis följande IP: 192.168.99.100

## Dockerfilen

- Exempel på en dockerfil
- Images namnkonvention registry/repo/name:tag- checksummor och lager
- docker build, build context, tag, Dockerfile
- grundläggande kommandon i Dockerfile: FROM, RUN, ADD, EXPOSE, CMD, ENV
- [Dockerfile reference](https://docs.docker.com/engine/reference/builder/)
- Demo bygge av dockercontainer

## Lab 2

Bygg din egen docker-container:

- Bygg en egen container som kör skriptet i mappen silly_server

Tips: 
- Basera den på debian:stretch-slim, då du redan har den imagen lokalt. (hur kommer det sig?)
- Skapa inte dockerfilen i projektets rot-mapp utan i en underfolder (tillsammans med de övriga filer som behövs för att bygga den)
- för att installera cowsay används följande kommandon:

```
apt-get update && apt-get install -y cowsay
ln -s /usr/games/cowsay /usr/bin
```

## Lab 3

Bygg en egen docker-container (igen):

- Bygg din egen container m.h.a. en Dockerfile så att den exponerar en statisk websida i nginx (en färdig statisk html-sida finns i foldern static_web_server)
- Starta containern så att du kan surfa till sidan på http://localhost:80

Tips: 
  - nginx kan agera som en webserver för statiskt content
  - Kolla hur man exponerar statiska filer i nginx-imagen som vi laddat ner. Du kan läsa dokumentationen 
  för nginx-imagen på [https://hub.docker.com/_/nginx/](https://hub.docker.com/_/nginx/)

## Links

- Rekommendation: en service/process per container
- Containrar som pratar med varandra behöver ett bestående begrepp: link

## Lab 4

Nu blir det lite svårare: Flera docker containrar som pratar med varandra...

Denna gång har vi en java-applikation som pratar med en relationsdatabas (PostgreSQL). Javaappen finns under
foldern db_service i detta projekt. Har ni java installerat så är det enklast att bygga projektet själv med:

    ./gradlew build

Annars kan ni ladda ner filen med den länk som jag skickat ut.

### Om applikationen

Javaapplikationen behöver ha följande miljövariabler satta för att kunna kommunicera med databasen:

- DB_HOST
- DB_PASSWORD

Den förväntar sig också att det finns en användare med namn "postgres" (vilket är default i postgres-imagen)

För att starta applikationen kör man komandot "java -jar db-service.jar". Applikationen exponerar då ett rest-api
på <host>:8008/person/list. För att hämta info fån applikationen kan man göra en GET med  en browser eller annan http-klient som postman.

### Om databasen

PostgreSQL är en open source relationsdatabas. Java-applikationen förväntar sig att det finns en tabell i datbasen som heter "person". 
Ett skript som skapar tabellen och fyller den med lite data finns under mappen postgres.

### Instruktioner

Tips: Vilken av de images vi laddade ner tidigare kan vi använda för att köra en java-applikation och en postgres db?

- Bygg med hjälp av en dockerfil en image som kör jar-filen
  - Miljövariablerna "kan" sättas i dockerfilen men helst "runtime", alltså som -e parameter till docker run
- Bygg med hjälp av en dockerfil en image som innehåller en postgres-databas med tabellen och datat från .sql-filen
- Starta din postgres-image (det hjälper om du ger den ett namn med --name flaggan)
- Starta din java-image med en länk till din postgres image och exponera ut porten 8080 till din docker host
- Gör ett webanrop till http://\<docker host\>:\<mappad port\>/person/list
  - Får du en lista med personer?
- Testa att exekvera kommandot "cat /etc/hosts" inne i java-kontainern. Förstår du hur länkningen funkar?

Tips:

- Genom att lägga in sql-filen i en speciell mapp i imagen kommer postgres-containern exekvera sql-filen första gången startar. Läs dokumentationen på docker hub för postgres-images under "How to extend this image" om hur man gör.

Kommentar:

- Vi skulle lika gärna utgå från en blank debian eller centos image och själva installera java och postgres med hjälp
av till exempel apt-get eller yum, men det är mycket enklare om vi använder de officiella images som finns tillgängliga och utökar dem istället.

## Docker compose (i mån av tid)

Med compose kan vi dra igång många containrar samtidigt!

Det finns både en v1 och en v2.

- docker-compose.yml
  - "service"
    - image / build
    - environment
    - ports
    - links
- up, stop, rm, pull

## Lab 5

Dra igång java-applikationen och databasen från förra laben med docker compose.

- Definiera databasimagen i compose-filen
- Definiera java-appen med en länk till databasen
- starta systemet och testa att du kommer åt rest-endpointen
- Ifall du har tid över:
  - Stoppa in en nginx-container i compose-filen som du konfar så att du kan surfa till http://<docker-host>/ och få listan med
  personer från föregående steg.

Tips:

- Yaml måste indenteras konsekvent med "space" (två mellanslag är standard) och inte "tab".
- Yaml är case sensitive
- Compose är rikt på funktionalitet, se [referensdokumentationen](https://docs.docker.com/compose/compose-file/) för 
djupare info.

## Kort om ett workflow för en större organisation

## Avslutning

Tack för visat intresse!

