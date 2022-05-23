FROM openjdk:16-alpine

ENV RUN_DEPS lz4-libs zstd-libs ca-certificates busybox-extras curl bash bind-tools
ENV KAFKA_VERSION=2.8.0
ENV KAFKA_URL=https://archive.apache.org/dist/kafka/${KAFKA_VERSION}/kafka_2.13-${KAFKA_VERSION}.tgz
ENV KAFKA_TMP_NAME=kafka.tgz

RUN echo Installing ; \
  apk add --no-cache $RUN_DEPS && \
  rm -rf /var/cache/apk/*

RUN wget $KAFKA_URL -O ${KAFKA_TMP_NAME} && \
  tar -xvzf ${KAFKA_TMP_NAME} && \
  rm -rf ${KAFKA_TMP_NAME}

COPY --from=edenhill/kcat:1.7.0 /usr/bin/kcat /usr/bin/kcat

COPY --from=fullstorydev/grpcurl:v1.8.2 /etc/ssl/certs/ca-certificates.crt /etc/ssl/certs/ca-certificates.crt
COPY --from=fullstorydev/grpcurl:v1.8.2 /etc/passwd /etc/passwd
COPY --from=fullstorydev/grpcurl:v1.8.2 /bin/grpcurl /usr/bin/grpcurl

ENV PATH="/app/kroto/bin:${PATH}"

ENTRYPOINT ["/bin/bash"]

COPY app/build/install/kroto /app/kroto
