FROM openjdk:17-alpine

USER 1026

ENTRYPOINT ["/telegram_bot/bin/telegram_bot", "/telegram_bot/config.json"]

ADD ./build/distributions/telegram_bot.tar /
ADD ./config.json /telegram_bot/
