# COMMANDS

- docker run \<imagem-name\>
- docker run -d \<image-name\>
- docker pull \<image-name\>
- docker kill \<container-name\>
- docker stop \<container-name\>
- docker ps
- docker ps -a
- docker container ls
- docker container ls -a
- docker images
- docker image list
- docker exec -it \<container-name\> \<command\>
- docker run -it \<container-name\> \<command\>
- docker rm \<container-name\>
- docker start \<container-name\>
- docker run -d \<image-name\>
- docker run -d -P \<image-name\>
- docker port \<container-name\>
- docker run -d -p \<host-por\>:\<container-port\> \<image-name\>
- docker history \<image-name> (para ver as camadas das imagens)
- docker inspect \<image-name> (para ver as informações da imagem)
- docker build -t (tag) nomeexemplo/dir:versaotag (para criar a bulid a partir do docker file)
- docker stop $(docker container ls -q) [para parar todos em execução]
- docker ps -s (para ver o tamanho do container)
- docker run -it -v \<local-path\>:\<container-path\> \<image\> \<command\>
- docker run -it --mount type=bind,source=\<local-path\>,target=\<container-path\> \<image\> \<command\>
- docker run -it -v \<nome-volume\>:\<container-path\> \<image\> \<command\>
- docker run -it --mount source=\<volume-name\>,target=\<container-path\> \<image\> \<command\>
- docker network list
- docker network create --driver bridge \<network-name>
- docker-compose up
- docker-compose up -d
- docker-compose ps
- docker-compose down (remove os serviços criados e as dependências)

## info

O comando run vai criar um container diferente a partir da imagem toda vez que for executado. Assim, as alterações realizadas em um container da mesma imagem não serão preservadas para o novo container.

As portas que aparecem em docker ps são de uso interno do container e não são acessíveis a partir da máquina host. É preciso mapear a porta do container com alguma porta disponível no host.

As camadas de uma imagem são read-only, imutáveis. Assim que um container é criado a partir de uma imagem, outra camada para escrita e leitura de dados é adicionada a pilha de camadas.

## dockerfile

ARG é utilizado durante a build da imagem no docker.
ENV é utilizado dentro do container como uma variável interna.

```dockerfile
FROM node
WORKDIR /app-node
ARG PORT=6000
ENV PORT=$PORT
EXPOSE $PORT
COPY . .
RUN npm install
ENTRYPOINT npm start
```

## bind e mount

É possível persistir os dados de um container armazenando conteúdos em um diretório no computador local.
Por meio dos parâmetros `-v` e `--mount` dá para criar um diretório local que espelhará os eventos ocorridos em um diretório dentro do container. Dessa forma, os dados serão preservados mesmo que o container seja excluído.
Para recuperar os dados, basta informar o diretório do computador criado anteriormente que os dados serão atribuídos ao novo container.

## volumes

Com bind mounts os dados são persistidos em um associação entre o container e os diretórios do host. Um volume é uma área gerenciado pelo próprio docker e serve como um diretório entre o host e o container, assim, não é necessário se preocupar com o sistema de arquivos do host. É possível gerenciar o volume por meio de comandos cli ou por meio da interface gráfica. Os volumes criados são armazenados dentro do diretório `"volumes"` do docker.

## tmpfs

Outro tipo de armazenamento de dados em containers. Esta flag só funciona em host Linux e o diretório criado será temporário. As informações armazenadas são efêmeras e registradas apenas na memória volátil. Assim que o container parar de executar, os dados serão perdidos.

- docker run -it --tmpfs=\<container-path> \<image> \<command>

## container em rede

É possível criar uma rede interna para comunicação entre containers. Por padrão, o docker cria três tipos de redes: null, host e bridge. Esta última é criada quando um container é iniciado sem a especificação de uma rede própria. O IP atribuído ao container também é dinâmico caso não seja especificado. As redes brigde definidas pelo usuário fornecem resolução automática de DNS.

Quando é especificado o driver `none` na criação do container, isso indica ao docker que o container não terá qualquer tipo de interface de rede.

Quando é especificado o driver `host` na criação do container, qualquer camada isolante entre a interface de rede do container e do host é removida. Assim, o container faz uso das mesmas portas/IP da máquina host.
