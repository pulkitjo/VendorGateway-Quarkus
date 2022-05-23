# logistics-customer-protorepo
Protobuf definitions

The schema definitions are also available [here](https://fantastic-spoon-2f61d748.pages.github.io/) in a slightly more human-readable format.

### **Prototool:**

In our repository, we are using `prototool lint`, `prototool format`, `prototool break check` for now.

**`prototool lint`**: 

Lints our Protobuf files. Lint rules can be set using the configuration file.There are three pre-configured groups of rules: `uber2`, `uber1`, `google`.

We are using `uber2` in our repository. This lint group follows the V2 Uber Style Guide, and makes some modifications to more closely follow the Google Cloud APIs file structure, as well as adding even more rules to enforce more consistent development patterns.

You can check every available rules from this list: https://github.com/uber/prototool/blob/dev/docs/lint.md 

---

**`prototool format`**: 

Format a Protobuf file and print the formatted file to stdout. There are flags to perform different actions:

* -d Write a diff instead. We are using this one, to suggest developers expected changes.
---

**`prototool break check`**: 

This will check our current Protobuf definitions against a past version of your Protobuf definitions to see if there are any source or wire incompatible changes

Being able to use `prototool break check` inside DroneCI itself, we added 2 more lines to our configurations like below:
```
git fetch origin main
git branch --track main origin/main || true
```

### **HOW TO:**

If you want to run prototool commands on your local machine, just run below commands:

**Linting, formatting && break check**

```
make lint
make format
make break
```

--- 
For all kind of prototool commands detailed explanation, please see:

https://github.com/uber/prototool


## Additional Tools

- [kroto](kroto/README.md)
