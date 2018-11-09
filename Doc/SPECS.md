# TodoList

## Our Team
Our team is composed of 3 people :
- Melvin Campos Casares (_**@melvinmajor**_)
- Maxime Liber (_**@LiberTMx**_)
- Hubert Van De Walle (_**@HE201496**_)

## GitHub

Link to our repository : https://github.com/melvinmajor/TodoList
Link to our Wiki : https://github.com/melvinmajor/TodoList/wiki

## What's our project ?

Our project is a kind of todo list.
The interest of making it is that we could use it for our everyday tasks in various domains : lessons, personal and professional projects, etc.
That's why we thought about implementing categories, which will be totally freely specified by users.

As it has been asked, this program will run under the MVC model. We will have a CLI and a GUI version which will naturally be user-friendly.
We will also work under an aspect of client-server-socket.
The GUI version will be made on JavaFX or Swing.

Our constraint are that we will work on Java 10 in stead of Java 8 (the one we use in the practice lesson) and that we will create JUnit test cases.

### What our project must do:

The first thing it has to do is to add new tasks, complete and delete them. In other words, it has to manage all the different tasks entered and register them locally.

### What our project will be able to do:

- Part of the registered tasks could be modified afterwards by the user (everything except the date of creation),
- Sort by importance, by date, etc.
- Filter by user, category or specific period of time.

### Some ideas of implementation:
Because we want to have something really complete, we could implement some of those ideas :

- A new variable which will store if it's a group work or not,
- Other options to sort and/or filter the different tasks,
- A progress bar for specific fields like importance or due date,
- JSON API to integrate the program in something else afterwards.