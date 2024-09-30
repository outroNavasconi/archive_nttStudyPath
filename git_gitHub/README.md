# Git e GitHub

## Como instalar e configurar o GIT no windows

- `git version`: exibe a versão do GIT
- `git config --global user.name {nome}`: altera o nome do usuário do GIT
  - Como, por exemplo, `git config --global user.name "xpto"`
  - Ao digitar o comando sem o input, é exibido no terminal o nome atual configurado
- `git config --global user.email {email}`: altera o email do usuário do GIT
  - Como, por exemplo, `git config --global user.email "xpto@email.com"`
  - Ao digitar o comando sem o input, é exibido no terminal o email atual configurado

## Comandos básicos (init, status, add, commit, log)

É no diretório `.git` que o GIT irá armazenar todas as informações relacionadas ao versionamento do seu projeto. Este diretório é criado no momento da criação do repositório. O diagrama abaixo mostra alguns comandos básicos do GIT para o gerenciamento do repositório.

```mermaid
sequenceDiagram
  participant W as Workspace
  participant S as Staging
  participant L as Local Repository
  participant R as Remote Repository

  W->>S: git add/mv/rm
  W->>L: git commit -a

  W<<->>S: git diff
  W<<->>L: git diff HEAD

  S->>L: git commit
  S->>W: git reset <file>

  L->>R: git push
  L->>W: git reset <commit>

  R->>L: git fetch
  R->>W: git clone/pull
```

Já o diagrama abaixo mostra como é o ciclo de vida dos arquivos em um repositório GIT. Todo arquivo novo adquire o status `UNTRACKED`. Após a adição a área de Stage, o status é alterado para `STAGED`. Qualquer edição no arquivo altera seu status para `MODIFIED`. Tanto um arquivo `STAGED` quanto `MODIFIED` passam para `COMMITED` (ou `UNMODIFIED`) após o uso da instrução `git commit`.

```mermaid
stateDiagram-v2
direction LR
  state "Untracked" as U
  state "Modified" as M
  state "Staged" as S
  state "Commited or Unmodified" as C

  U-->S: "git add [file]"
  
  M-->S: "git add [file]"
  M-->C: "git commit -a" or "git restore"

  S-->C: "git commit"
  S-->M: edit file

  C-->M: edit file
  C-->U: "git rm [file]"
```

O comando `git log` exibe o histórico de commits dentro do repositório.
