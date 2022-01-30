# Notrufzentrale
Personal Discord bot featuring [Kord](https://github.com/kordlib/kord)

## Local setup
`$ ./gradlew build`

`$ ./gradlew run --args=<token>`

## Docker Setup
`$ docker build -t notrufzentrale:1.0 .`

`$ docker run notrufzentrale:1.0 <token>`

## Register as Service
```
# /etc/systemd/system/notrufzentrale.service
[Unit]
Description=Notrufzentrale Discord Server
After=docker.service
Requires=docker.service

[Service]
Type=simple
Restart=always
User=root
Group=root

ExecStart=/usr/bin/docker run notrufzentrale:1.0 <token>

[Install]
WantedBy=multi-user.target
```

## Use as Service

`$ systemctl enable notrufzentrale.service`

`$ systemctl start notrufzentrale.service`