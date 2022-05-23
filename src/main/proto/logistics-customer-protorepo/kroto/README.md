# kroto

A utility Docker image to simplify working with Kafka within the cluster VPC

## Why do we need this?

Our Kafka brokers and K8s pods are only reachable from within the EKS VPC and not directly via the GlobalProtect VPN.
This means that you cannot use `kcat` locally and need to spin up a pod in EKS to do this as below:
```shell
k run <temp-pod-name> -it --rm --image edenhill/kcat:1.7.0 -- -b b-1.log-customer-staging.b5ki79.c6.kafka.eu-west-1.amazonaws.com:9092 -L
```
The same is true for using other tools like `curl` or `grpcurl`.

In addition, some of these Docker images set the entrypoint to be the tool so you have to remember to override it if you want a shell.
They are also based on `alpine` so are generally missing common debugging tools which need to be installed everytime

Records in our Kafka cluster are encoded in Protobuf which makes them difficult to work with on the CLI.

## Goal

The goal of this image is to have a one-stop container that contains common debugging tools as well as the following:
- grpcurl
- kcat
- kroto (CLI tool that can auto deserialize Protobuf messages)

## Usage
Follow the [general usage steps](https://github.com/deliveryhero/logistics-demand-tooling#usage) to make the script available on your path.

Run the following command to be dropped into a Bash shell in a K8s pod:
```
kroto
```

## Development

Merging a PR builds a new Docker image with the latest Protobuf definitions and pushes it to our ECR registry.
