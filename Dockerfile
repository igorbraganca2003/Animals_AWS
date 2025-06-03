FROM ubuntu:latest
LABEL authors="idetoledo"

ENTRYPOINT ["top", "-b"]