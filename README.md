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
    docker pull node:7
    docker pull openjdk:8-jre
    
Verifiera sedan att dessa images finns lokalt med följande kommando:

    docker images
    
### Klona eller ladda ner detta repo

Detta repo kommer innehålla de exempel som vi abetar med under själva worksopen.

Har du git installerat så klonar du detta repo, annars finns det en länk på github för att ladda ner repot
som en zipfil och packa upp det på din dator.

OBS! Mer innehåll kommer läggas till i detta repo, så ifall du vill ladda ner som zip, vänta med det tills vi närmar
oss själva workshoppen.