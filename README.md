# FlashBot

Bot written in [Groovy](https://groovy-lang.org/) thanks to
help from [Github Copilot](https://github.com/features/copilot)
and [ChatGPT](https://chat.openai.com/chat), with logo generated
by [Midjourney AI](https://midjourney.com/) and modified by
[Dall-E](https://openai.com/dall-e-2/).

![FlashBot banner](./logo/banner-readme.png)

## Setup

This bot can be run either in Dockerized, or standalone version.
Each version has its of prerequisites

### Setting up environment

The first step to run this project is to obtain a token for a bot.
There are multiple guides for it, you can for example use
[this reference token guide](https://www.writebots.com/discord-bot-token/).

After token is obtained you can copy `.env.example` to `.env`:

```sh
cp .env.example .env
```

And fill .env file with your token:

```sh
DISCORD_BOT_TOKEN=<your token>
```

### Setting up Dockerized version

#### Dockerized version prerequisites

- [Docker](https://www.docker.com/)
- [docker-compose](https://docs.docker.com/compose/)

#### Running dockerized version

Running bot in Docker can be simplified to a single command

```sh
docker-compose -f docker-compose.prod.yml up -d
```
