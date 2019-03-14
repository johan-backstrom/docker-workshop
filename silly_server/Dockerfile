FROM debian:stretch-slim
ADD silly.sh /
RUN apt-get update && apt-get install -y cowsay
RUN ln -s /usr/games/cowsay /usr/bin
CMD /silly.sh
