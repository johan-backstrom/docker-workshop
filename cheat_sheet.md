# Docker cheat sheet

För djupare info om ett kommando:

    docker help
    docker help <command>
    
Exempel:

    docker help ps

## Hantera images

### Ladda ner en image

    docker pull [<registry>/][<repo>/]<image>[:<tag>]
    
- Om registry inte är satt defaultas till docker hub
- Om repo inte är satt defaultas till "library" (oficiella images)
- Om tag inte är satt defaultas till "latest"

Exempel:

    docker pull nginx
    docker pull jwilder/nginx-proxy

### Lista images

    docker images

### Ta bort en image

    docker rmi <image>
    
Där \<image\> är ett imagenamn eller en checksumma 
    
## Hantera containrar

### Starta en container

    docker run [options] <image>
    
Startar en container (laddar ner den först ifall den inte finns lokalt)

Vanliga options är:

- -d --> startar som bakgrundsprocess
- -p \[host port\]:\[container port] --> exponerar containerns port till hosten
- -e \[env variable name\]=\[value\] --> sätter miljövariabel i containern
- -v \[host folder\]:\[container folder\] --> mappar in en folder från hosten i containern
- --name \[containernamn\] --> sätter ett namn på containern

Exempel:

    docker run -d --name min_proxy nginx
    docker run -d -p 8081:80 -e SOME_VARIABLE=apa nginx

### Lista körande containrar

    docker ps
    docker ps -a

Flaggan -a listar även stoppade containrar
    
### Stoppa en container

    docker stop <container name or id>

Exempel:

    docker stop min_proxy
    docker stop da415074ae9e
    
### Ta bort en container

    docker rm [-f] <container name or id>
    
Exempel:

    docker rm -f min_proxy

### Få detaljerad info om containern

    docker inspect <container name or id>
    
### Exekvera kommandon inne i containern

    docker exec <container name or id> <command>

Exempel:

    docker exec min_proxy kill 1

Ett mycket användbart kommando (istället för ssh):

    docker exec -it min_proxy bash

## Docker build

    docker build [options] <folder>

Förväntar sig att det finns en Dockerfile i "folder". Från dockerfilen kan man bara referera till filer
som ligger under "folder" i filträdet.

Det absolut vanligaste kommandot  som bygger en image med namnet my_image är:

    docker build -t my_image .