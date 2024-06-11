
![GA logo](https://camo.githubusercontent.com/6ce15b81c1f06d716d753a61f5db22375fa684da/68747470733a2f2f67612d646173682e73332e616d617a6f6e6177732e636f6d2f70726f64756374696f6e2f6173736574732f6c6f676f2d39663838616536633963333837313639306533333238306663663535376633332e706e67)

#  Homework: Unix CLI Practice

## Episode X: A New Terminal

A long time ago in a Unix environment far, far away, young Jedi padawans who
knew only of desktop software were seduced by the dark side of the Force to
enter… The Terminal.

Follow the instructions below using all the console commands introduced in
Fundamentals, class, or that you find on your own.

## Setup

* Fork and clone this repo into your **`homework`** folder

* Make a directory inside the folder created by this clone called **`star_wars`**.  

* Create a file called **`commands.txt`**.

* Paste the answer to each numbered question (i.e. the command(s) that accomplished the task) in **`commands.txt`** once you get it to work.

* Remember, you can learn about any Unix command by typing **`man`** and then the command name.  E.g., **`man ls`**.  Type **`Q`** to get out of the Manual page ("man page") viewer.


## Part I: Set the Scene

Complete all work inside the **`star_wars`** folder.

1. Create a directory called **`death_star`**, and make the following files inside of it: **`darth_vader.txt`**, **`princess_leia.txt`**, **`storm_trooper.txt`**

2. Create a directory called **`galaxy_far_far_away`**, and inside of it make a directory named **`tatooine`** and create the following files in it: **`luke.txt`**, **`ben_kenobi.txt`**.

3. Inside of **`tatooine`** make a directory called **`millenium_falcon`**, and in it create: **`han_solo.txt`**, **`chewbaca.txt`**

<br>

## Part II: **`mv`** - rename

* You can rename a file using the **`mv`** command. 

4. Rename **`ben_kenobi.txt`** to **`obi_wan.txt`**.

<br>

## Part II: **`cp`** - copy

* You can copy a file from one location to another using the **`cp`** command. (**`man cp`** for more info)

- Directories can be sibling (parrell to each other) or can be parents (the folder that contains the folder you are in)

5. Copy **`storm_trooper.txt`** from **`death_star`** to **`tatooine`**.

<br>

## Part IV: **`mv`** - move

* You can use the **`mv`** command to move files from one location to another. **`mv`** can be used for renaming, moving, or both.  Run **`man mv`** to see the options—remember hit the **`Q`** key to get out of the manual page viewer.

6. Move **`luke.txt`** and **`obi_wan.txt`** to the **`millenium_falcon`**.

7. Move **`millenium_falcon`** out of **`tatooine`** and into **`galaxy_far_far_away`**.

8. Move **`millenium_falcon`** into **`death_star`**.

9. Move **`princess_leia.txt`** into the **`millenium_falcon`**.

<br>


## Part V: **`rm`** - remove

### ***BE CAREFUL WITH **`rm`!!! THERE IS NO "TRASH" IN THE UNIX CLI. WHEN YOU DELETE SOMETHING IT IS GONE FOREVER!!!***

You can use **`rm`** to delete a file.

10. Delete **`obi_wan.txt`**.

<br>

## Part VI: all together

11. In **`galaxy_far_far_away`**, make a directory called **`yavin_4`**.

12. Move the **`millenium_falcon`** out of the **`death_star`** and into **`yavin_4`**.

13. Make a directory in **`yavin_4`** called **`x_wing`**.

14. Move **`princess_leia.txt`** to **`yavin_4`** and **`luke.txt`** to **`x_wing`**.

15. Move the **`millenium_falcon`** and **`x_wing`** out of **`yavin_4`** and into **`galaxy_far_far_away`**.

16. In **`death_star`**, create directories for **`tie_fighter_1`**, **`tie_fighter_2`** and **`tie_fighter_3`**.

17. Move **`darth_vader.txt`** into **`tie_fighter_1`**.

18. Make a copy of **`storm_trooper.txt`** in both **`tie_fighter_2`** and **`tie_fighter_3`**.

19. Move all of the **`tie_fighters`** out of the **`death_star`** and into **`galaxy_far_far_away`**.

<br>

## Part VII: **`rm -r`: remove directories and everything they contain

***BE CAREFUL WITH **`rm`!!! THERE IS NO TRASH CAN IN THE UNIX CLI. WHEN YOU DELETE SOMETHING IT IS GONE FOREVER***

Before you hit enter, make sure are deleting the right thing, or you could accidentally delete the contents of your computer (it has happened).

This command will not typically ask you if you "really want to delete." It will just delete.


20. Remove **`tie_fighter_2`** and **`tie_fighter_3`**.


## Part VIII:

21. Create a file in **`x_wing`** called **`the_force.txt`**.

22. Destroy the **`death_star`** and anyone inside of it.

23. Return **`x_wing`** and the **`millenium_falcon`** to **`yavin_4`**.

<hr>

# You are done with homework :wink:, now lets submit it.

### Commit and push your updated code:

"Add" your changes (prepare them to be "committed"):
```bash
$ git add -A
```

"Commit" your changes—any time you make a commit, you can always restore the files in the repo to that point:
```bash
$ git commit -m "Completed homework assignment"
```

"Push" your commits to github:
```bash
$ git push origin master
```

# "Hungry for more?"

Want an extra challenge?  In many assignments or labs, you'll find a "Hungry for More" section which is designed for further learning. If they seem too overwhelming you can absolutely skip them. They won't factor into your homework score, and you will be totally fine for class as long as you complete and understand the main assignment up to that point.  What they _will_ do is encourage you to go deeper, explore, and push yourself. And that will pay off big time in the long run.  Below is the "hungry for more" section for this assignment.

## Try to complete the following, making a commit after each one.

...as long as you don't mess up the homework assignment you just did. They get more difficult/complex as you go (more or less).

* Create a directory called **`test-dir`** with a couple files in it.  Try to make a copy of that entire directory. (Hint: **`man cp`** for more info.)
* Find and use command line shortcuts. 
* Try applying one command to multiple files at once.
* Try applying one command to **all** files in a single directory (where necessery)
* Try applying one command to **all files that match a pattern**.
* Try using a mix of absolute and relative paths.

* If you're a glutton for punishment, read about the famous text editor "vi" online.  It's included with all Unix systems, and although it has a steep learning curve, it is an incredibly powerful text editor.  You can run a tutorial program that will teach you some **`vi`/`vim`** commands by typing **`vimtutor`** in your Terminal.  See if you can use it to create and edit a small ["Hello, World!"](https://en.wikipedia.org/wiki/%22Hello,_World!%22_program) JavaScript program!

<br>
<hr>
