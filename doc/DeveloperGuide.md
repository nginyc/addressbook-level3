# Developer Guide

* [Setting Up](#setting-up)
* [Design](#design)
* [Testing](#testing)
* [Appendix A: User Stories](#appendix-a--user-stories)
* [Appendix B: Use Cases](#appendix-b--use-cases)
* [Appendix C: Non Functional Requirements](#appendix-c--non-functional-requirements)
* [Appendix D: Gloassary](#appendix-d--glossary)

## Setting up

#### Prerequisites

1. **JDK 8** or later
2. **Eclipse** IDE
3. **e(fx)clipse** plugin for Eclipse (Do the steps 2 onwards given in
   [this page](http://www.eclipse.org/efxclipse/install.html#for-the-ambitious))


#### Importing the project into Eclipse

0. Fork this repo, and clone the fork to your computer
1. Open Eclipse (Note: Ensure you have installed the **e(fx)clipse plugin** as given in the prerequisites above)
2. Click `File` > `Import`
3. Click `General` > `Existing Projects into Workspace` > `Next`
4. Click `Browse`, then locate the project's directory
5. Click `Finish`

## Design
<img src="images/mainClassDiagram.png"/>

## Testing

* In Eclipse, right-click on the `test/java` folder and choose `Run as` > `JUnit Test`

## Appendix A : User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have)  - `* *`,  Low (unlikely to have) - `*`


Priority | As a ... | I want to ... | So that I can...
-------- | :-------- | :--------- | :-----------
`* * *` | new user | see usage instructions | refer to instructions when I forget how to use the App
`* * *` | user | add a new person |
`* * *` | user | delete a person | remove entries that I no longer need
`* * *` | user | find a person by name | locate details of persons without having to go through the entire list
`* * *` | user | edit an existing person's contact details | update a person's contact details when it is outdated or keyed in wrongly
`* *` | user | find a person by any contact detail | locate a person by his phone, address, or any contact detail
`* *` | user | hide [private contact details](#private-contact-detail) by default | minimize chance of someone else seeing them by accident
`* *` | seasoned user | add multiple email address and phone numbers to the same person | add more contact details to a person, especially when it is likely that a person has multiple email address and phone numbers
`*` | user with many persons in the address book | associate a person with multiple tags | locate a person more easily with tags
`*` | user with many persons in the address book | sort persons by name | locate a person easily

## Appendix B : Use Cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Delete person

**MSS**

1. User requests to list persons
2. AddressBook shows a list of persons
3. User requests to delete a specific person in the list
4. AddressBook deletes the person <br>
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

3a. The given index is invalid

> 3a1. AddressBook shows an error message <br>
  Use case resumes at step 2
  
#### Use case: Rename tag

**MSS**

1. User requests to rename a tag, specifying the name of the tag
2. AddressBook verifies that the tag exists
3. AddressBook prompts for new name of tag
4. User specifies new name of tag
5. AddressBook verifies that new name of tag is valid
6. AddressBook prompts for confirmation
7. User specifies confirmation
8. AddressBook renames tag in all contact details
<br>
Use case ends.

**Extensions**

2a. Tag doesn't exist

> 2a1. AddressBook informs user that tag doesn't exist
<br>
Use case ends 

5a. New name of tag is invalid

> 5a1. AddressBook informs user that new tag name is invalid 
<br>
Use case resumes at step 3
  

## Appendix C : Non Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java 8 or higher installed.
2. Should be able to hold up to 1000 persons.
3. Should come with automated unit tests and open source code.
4. Should favor DOS style commands over Unix-style commands.
5. All commands should finish executing in at most 1 second.
6. Should start-up in at most 2 seconds.
7. All command keywords should be at most 15 characters long.
8. Program should not exceed 25MB in size.
9. Program should not be racist or sexist in any way.

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

##### Private contact detail

> A contact detail that is not meant to be shared with others
