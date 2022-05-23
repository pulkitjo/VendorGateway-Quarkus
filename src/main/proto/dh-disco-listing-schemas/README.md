# Protobuf Schemas for Discovery messages and events

This repository contains protobuf schemas for customer profiles, vendor profiles, tracking events and is generally a
store of schemas that need to be shared across multiple projects.

### Contributor guide

To contribute a new schema, please keep the following in mind:

  * Every schema object (field/object) must have a comment explaining at least what the field is
  * The protobuf schema is the single source of truth
  * When removing fields, reserve their field numbers instead of removing entirely
  
