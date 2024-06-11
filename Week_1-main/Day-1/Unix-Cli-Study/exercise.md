# UNIX CLI Study

Use your favorite search engine and the provided reading to research and
respond to the following questions.

You won't submit your responses for this study, because we'll use Git to submit
responses and we haven't discussed Git yet. In the future, you will submit these
responses to us. For now, answer them thoughtfully to ensure that you understood
what you read.

Before you start reading, open a terminal so that you can try some of the
commands you'll read about. On macOS, press `CMD + space` to open Spotlight,
type "terminal" and press `return`. On Linux (Ubuntu), press `CTRL + ALT + T`.

## Required Reading

Most people use computers by interacting with a graphical user interface, e.g.
by pointing the mouse at buttons and icons and clicking on them. This is
intuitive and convenient, but not very flexible -- you can only click on the
buttons that the GUI chooses to expose, which is a small subset of the
functionality that your computer offers.

As developers, we'll need to use more of that functionality. To do that, we
interact with our computers mostly via text, through a command line interface
(CLI). The operating systems used in SEI, macOS and Linux, are both members of
a family of computer systems that can be broadly described as UNIX-like. The
first thing we'll learn in SEI is how to interact with UNIX computer systems via
the command line interface.

### UNIX Basics

There are three basic types of "things" that make up a UNIX operating system:
files, directories, and processes. Files include all the things we typically
think of as files on a computer: text, audio, images, PDFs, code, etc. In UNIX,
some files are "executable", which means they can run as code that will create a
process to do useful things for the user. Most things that we think of as
programs, and some that we don't, are executable files in UNIX. Some examples:
web browsers, email clients, text editors, all the terminal commands you'll read
about here, and even the terminal itself!

So far we've got files and procesess. Some files (executables) can kick off
processes. Processes do useful things, like modify files, communicate over the
internet, and read input from devices like mice and keyboards. Where do
directories fit in?

###  The UNIX Filesystem

All the data, code, and executables that power your computer live in the
filesystem, which is made up of directories and files. Directories are simply
files that contain other files. You've probably heard them called "folders".
The filesystem is a hierarchical "tree" structure, which means that each file
lives in some directory, and each of those directories in turn lives in another
directory. You can keep going "up" the tree until you get to the "root" of the
filesystem, which is a directory called `/`. Somewhat confusingly, the tree is
usually represented as having its "root" at the top. Here's a partial tree
representation of a typical UNIX filesystem, starting at the root:

![image](https://media.git.generalassemb.ly/user/6926/files/d2f1328a-7560-11e8-92ea-396e051906cb)

Notice that the directory `caleb-pearce` that contains all the "folders" we're
most familiar with, e.g. Documents, is actually two steps removed from the root
of the filesystem. There's so many directories and files in our computers that
we never really interact with! We don't need to know all about those internals
to be effective web developers, but a little knowledge of that domain can be
very useful to us.

Those folders in the root directory are present on every UNIX system, usually
with the same purpose. Here's a brief summary of the role of each of these
directories:

| Directory Name | Purpose                                                                                                                                                       |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `bin`          | Stores executable binary (hence "bin") files. Most terminal commands and programs are binary files, and many are stored here.                                 |
| `lib`          | Stores library files, which hold code used by multiple applications, including the ones in `bin`.                                                             |
| `etc`          | Stores configuration files, both for programs we install and for underlying system functionality.                                                         |
| `usr`          | Holds executable files that are installed by users, as opposed to the ones in `bin`, which are often bundled with the OS.                                     |
| `proc`         | Contains files that represent the processes running on your computer.                                                                                         |
| `tmp`          | Stores temporary files created by various programs. Usually erased on reboot.                                                                                 |
| `dev`          | Short for "device". Contains files that represent physical devices like keyboards, network cards, etc.                                                        |
| `home`         | Contains a home directory for each user, such as the `caleb-pearce` directory above. All of our documents, images, etc. are stored in these home directories. On macOS, this is called `User`. |

If you type `ls /` in your terminal window and press
enter, you should see many of the same directories!

### Paths

Every file or directory has a unique address, called a path, that can be used
to describe its location on the filesystem. There are two types of paths:
absolute and relative.

#### Absolute Paths

Absolute paths describe how to get to a file or diretory from the root of the
filesystem. Remember, the root directory is always called `/`. Paths consist of
directory names seperated by slashes, like this:
```
/home/caleb-pearce/Code/my-website.html
```
That's the absolute path to the file called `my-website.html` in the diagram
above. Notice that it begins with a `/`. All absolute paths begin with the root
directory. Every file or directory has one and only one absolute path.

#### Relative Paths

To understand relative paths, we need to know about the concept of a "current
working directory", which is the directory that our terminal is currently "in".
In your terminal, type `pwd` and hit enter. You should see something like
`/User/myname` or `/home/myname`, depending on whether you're on Linux or macOS.
This is an absolute path to the current working directory. If you type `ls` and
hit enter, you should see all the files and directories in your current working
directory.

You can reference any file in the current directory or in a
directory by a _relative_ path. For instance, in the tree diagram above,
if my current working directory is `/home/caleb-pearce`, then the relative
path to `my-website.html` is `Code/my-website.html`, and the relative path
to the "Music" directory is just `Music`. Notice that neither of these
begin with a `/`! This is how we know they're relative paths.

### UNIX Command Syntax

We've already learned the `ls` and `pwd` commands, which show the contents of
the current directory and its absolute path, respectively. To move from one
directory to the next, we'll use the `cd` command. Unlike `ls` and `pwd` though,
the `cd` command won't be very useful on it's own -- we need to tell it where we
want to go! To do this, we'll pass it an _argument_. Arguments are extra data
passed to terminal commands. To pass an argument to a command, write the
command, followed by a space, followed by the argument. Like this:

```
command argument1 argument2
```
We can also tweak the behavior of commands with _flags_. Flags are a way to
specify certain predefined options that the command provides. They usually
have a longhand and shorthand variety. The short variety is prefixed with `-`
and the longhand variety is prefixed with `--`. Here's an example of passing the
 `ls` command one flag in the shorthand variety:
```sh
ls -l
```

And here's an example of passing another terminal command `man`, the `version`
flag using the longhand variety. We'll learn more about `man` shortly.
```sh
man --version
```

Try it! Notice that the output of `ls` provides much more information now.

### Navigating the Filesystem

Let's use the `cd` command to move around the filesystem. First, we'll run:
```
cd ~
```
The `~` is a shortcut for our home directory, and passing it to `cd` as an
argument means "go to home directory". Then, let's run `ls`, to see the contents
of our home directory. Say you have a directory there called `Photos` -- you can
type `cd Photos` to switch to the Photos directory. Run `ls` again. Do the files
here look familiar? They should, because they're the same ones that you can
access with the graphical file browser you're used to using!

If we're in `/home/caleb-pearce/Music` and we want to get back to
`/home/caleb-pearce`, we can type `cd ..` to take us "up" the tree one level.
`..` is special relative path that means "the directory that contains" the
current working directory". We can use it as a kind of "back button" when we're
using the terminal to navigate.

We can provide `cd` with any path, relative or absolute, to change the current
directory. Try `cd /` to get to the root directory. Poke around a bit, but don't
run any commands here besides `ls`, `cd`, and `pwd`. Deleting or changing things
in root can cause problems.

### Terminal Command Reference

If we want to know more about a certain terminal command, we have a few options.
We can Google it, or we can ask a friend or coworker. There's a better way,
though! There's a terminal command to provide more information about terminal
commands: the `man` command. It's short for "manual". It provides comprehensive
info about all the options and arguments we can pass to a command! It can be a
bit dense and hard to read, but it's got some advantages:

  - It's built in to our systems, so it's available offline and loads lightning
  fast.
  - It's written by the people who created the utility, so it's more likely to
  be correct than what you'd find on Google.
  - It shows _all_ the options, so you'll often discover nifty features that you
  wouldn't have found otherwise.

Try `man ls` and `man pwd` to get a sense of how it works. Don't read them all,
just get a feel for the structure. You can exit the man page and get back to
your command prompt by pressing `q`.

## Response Questions

Answer these as best you can to ensure you absorbed the info above. Don't
hesitate to refer back to the reading, to Google, or to a `man` page.

### Absolute vs. Relative Paths

In your own words, describe the difference between an absolute and a relative path.

```md
<!-- your answer here -->
```

### Navigation Practice

Navigate to the directory called `bin` in the root directory. What are some
files that are in this directory? Do you recognize any of them?

```md
<!-- your answer here -->
```

### Determining Paths

In the tree diagram of a filesystem above, imagine there's a file called
`app.js` inside the `Code` directory. What would its absolute path be?

```md
<!-- your answer here -->
```

### Command Flags

In the command `ls -l`, what does the `-l` flag do?

```md
<!-- your answer here -->
```
