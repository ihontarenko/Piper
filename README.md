```text
               __     __     __
.-----..-----.|  |--.|  |--.|  |.-----..----.
|  _  ||  _  ||  _  ||  _  ||  ||  -__||   _|
|___  ||_____||_____||_____||__||_____||__|
|_____|

```

## Java Command Flow Runner Tool


### .gobbler.yml

```yaml
version: '1.0'

gobbler:

  targets:
    docker-bin: "docker exec -it"
    gobbler: "java -jar ./bin/gobbler.jar do"
    bin: "C:\\Program Files\\Git\\git-cmd.exe"
    bash: "C:\\Program Files\\Git\\git-bash.exe"

  commands:
    
    cd-status:
      target: system
      strategy: consistent
      commands:
        - 'echo "[CURRENT DIRECTORY STATUS]"'
        - "ls -la"
        - "du -h ."

    start-mariadb:
      strategy: parallel
      commands:
        - "cd-status"
        - "docker-compose up -d dcp-mariadb"


    start-couchbase:
      strategy: consistent
      commands:
        - "cd-status"
        - "docker-compose up -d dcp-couchbase-server"

    start-databases:
      strategy:
        name: parallel
        counter: 5
      commands:
        - "start-mariadb"
        - "start-couchbase"

    start-consul:
      strategy: consistent
      commands:
        - "docker-compose up -d dcp-consul"

    start-main:
      strategy: consistent
      commands:
        - "start-consul"
        - "start-databases"
```