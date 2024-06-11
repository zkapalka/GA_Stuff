# Summary

<br>

[Official Git Cheatsheet](https://github.github.com/training-kit/downloads/github-git-cheat-sheet/)

<br>

## Local Repo Actions

### Create a repo

- `git init` - This tells the version control software to start tracking changes in the repo. It creates a hidden `.git` folder in your repo.

### Making Changes

- modify / save files (make changes)
- `git add <file1> <file2>` ...
- `git commit -m "descriptive message"`

<br>

## Remote Actions

### Cloning

- `git clone <url>` - download a repo from the URL, including all it's history,
  auto setup as remote

### Putting an existing repo on github

- create on github
- locally:
  - `git remote add origin <github_url>`
  - `git push -u origin master` - this tells git to push to origin master by
    default

### Syncing changes

- `git pull [remote_name] [branch_name]` - fetch changes and merge them in
- `git push [remote_name] [branch_name]` - push changes to a remote repo

- Best practice is:
  1. `git pull`
  2. make changes until you reach a savepoint
  3. `git pull`
  4. `git add`, `git commit`
  5. `git push`

### Fork & PR Model

Forking and Pull Requests are features of Github (not git).

1. Go to the repo you want to fork, and click the 'fork' button.
2. Clone your fork using `git clone fork_repo_url`
3. Make changes, add, commit.
4. Push changes to your fork on GH: `git push origin master`
5. Open a pull request by clicking the Pull Request link on your fork page.

### Three Commands to Remember (you will use them all the time!)

- `git add <file1> <file2>` ...
- `git commit -m "descriptive message"`
  - `git push -u origin master`


<br>

## Homework Fork and Clone Process

1. Goto the `jdr-0622` homework or lab repo and click the Fork button in the top right corner of the page.

  ![](https://i.imgur.com/OAZnlIe.png)
 
2. A fork is a fancy word for making your own **copy** of a repo. Choose your account to fork to.

  ![](https://i.imgur.com/Zobxfj1.png)
  
3. It'll fork.

  ![](https://i.imgur.com/RspbgII.png)

4. Notice in the top left of the screen you have a copy of the original `jdr-0622` repo. 

  ![](https://i.imgur.com/0JrFyfW.png)
  
5. Next, you'll want to **clone** down the repo to your local machine. Clone is a fancy word for copying a repo from GitHub to your local machine. Click on the green "Clone or download" button on the right.

  ![](https://i.imgur.com/SKit5Vz.png)
  
  - Make sure you select "Clone with SSH". SSH is a more secure, encrypted way to clone. We set up the SSH keys on your machine on day 2. SSH keys are like a fingerprint on your machine.
  - Click on the clipboard to copy the SSH url link.

6. Open your Terminal and change into the correct sei folder for labs, homeworks, etc. 

  - For labs: `cd ~/sei/labs`
  - For homework: `cd ~/sei/homeworks`

  ![](https://i.imgur.com/7gpRplZ.png)
  
7. From your Terminal, run `git clone <GITHUB-REPO-URL-COPIED-TO-YOUR-CLIPBOARD>`. You can run `ls` or `ls -la` to confirm the repo copied down. **NOTE** - you do not need to create a folder first. When you clone down the folder is provided for you.

  ![](https://i.imgur.com/Q5Sk4D2.png)
  
8. In the Terminal, `cd` into the directory and run `code .` to open the entire folder in VS Code.

  ![](https://i.imgur.com/5a5s85D.png)


<br>

## Homework Submission Process Via Pull Request

1. Make changes to the appropriate files and save them. Go to the Terminal and run `git status`. This will give you the status of the repo. In this screenshot, I modified one file.

  ![](https://i.imgur.com/z2BTMvR.png)
  
2. Run `git add .` to move any changed files to staging. Run `git status` to check things out.

  ![](https://i.imgur.com/HFpCILA.png)
  
3. Run `git commit -m "<YOUR-COMMIT-MESSAGE>"` to take a snapshot/commit of your work. Git commit requires a message.

  ![](https://i.imgur.com/BjolR7U.png)
  
4. Next, you can run a `git push origin master` to push your changes to **your** forked repo. **origin** refers to your GitHub url. **master** is the branch you're pushing. We'll talk more about creating branches in the future.

  ![](https://i.imgur.com/vH2fKhP.png)
  
5. Goto your **forked** repo and refresh the page. You should see the commit message and timestamp on the files you changed. Note, I only changed the `challenge.js` file in the `lib` folder so I only see the message there.

  ![](https://i.imgur.com/zR0EPtN.png)
  
6. Next, click on "New pull request".

  ![](https://i.imgur.com/wg7i07l.png)

7. The next screen will show the changes. Click on the green "Create pull request" button. Put your name in the title section and click on "Create pull request".

  ![](https://i.imgur.com/v1JpEIs.png)
  
<br>

## Push a Locally Created Repo to GitHub

1. Create a new project locally. For this example, I'll create a new folder `test_project` and create two files.

  ![](https://i.imgur.com/R3vvkKZ.png)
  
2. Run `git init` to "turn on" version control in your folder. Then, git add and git commit.

  ![](https://i.imgur.com/ZKiIlTn.png)
  
3. Next, go to GitHub and create an empty repo to push to. From your profile page, click on the `+` in the top right corner of the screen and select "New Repository".

  ![](https://i.imgur.com/0Uc92SV.png)
  
4. On the next page, give your repo a name. And click "Create Repository". **DO NOT** click the "Initialize this repository with a README" box. This will avoid conflicts for now. You can always create a README file later on.

  ![](https://i.imgur.com/1ciHcn6.png)
  
5. You'll see instructions like the screenshot below. Since you've already created a repo locally, you're interested in the `…or push an existing repository from the command line` section. 

  ![](https://i.imgur.com/zzVpnyA.png)
  
6. In the Terminal, in your project folder, copy and paste the two commands from the `…or push an existing repository from the command line` section.

  ![](https://i.imgur.com/gI9Amf8.png)

7. Refresh your GitHub page and you should see your repo.

  ![](https://i.imgur.com/7eG1IVN.png)
  
Your local repo is now connected to GitHub!












  
  
