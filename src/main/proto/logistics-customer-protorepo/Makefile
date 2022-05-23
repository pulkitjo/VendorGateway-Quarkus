.PHONY: lint format break all

lint:
	docker container run -v ${PWD}/:/work --rm -it uber/prototool:1.10.0 prototool lint

format:
	docker container run -v ${PWD}/:/work --rm -it uber/prototool:1.10.0 prototool format --diff

break:
	docker container run -v ${PWD}/:/work --rm -it uber/prototool:1.10.0 prototool break check protos --git-branch main

all: lint format break
