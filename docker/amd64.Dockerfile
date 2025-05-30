FROM openjdk:8-jdk-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
RUN apk add curl fontconfig --update ttf-dejavu && fc-cache --force
RUN mkdir -p /home/workspace && rm -f /etc/localtime && ln -sv /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone
COPY output  /home/workspace
ENTRYPOINT ["sh","/home/workspace/bootstarp.sh"]