# https://hub.docker.com/r/swaggerapi/swagger-ui
FROM swaggerapi/swagger-ui

# Настройки контейнера
ENV URLS_PRIMARY_NAME="Documentation"
ENV URLS_PRIMARY_URL="/docs"
ENV URLS_PRIMARY_PATH="/docs"

# Список файлов с документацией, для выпадающей менюшки
ENV URLS="[{url:'/docs/poems/index.yaml',name:'Poem'}, \
         ]"

# Копирование файлов в контейнер
RUN mkdir -p /usr/share/nginx/html/docs
COPY ./docs /usr/share/nginx/html/docs

# Открытие порта в контейнере (8080- стандрат для этого типа контейнера)
EXPOSE 8080