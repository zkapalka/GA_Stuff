git config --global user.email "you@example.com"
git config --global user.name "cameronmcnz"

## SSH 
ssh-keygen -t ed25519 -C "your_email@example.com"

Note: If you are using a legacy system that doesn't support the Ed25519 algorithm, use:

 ssh-keygen -t rsa -b 4096 -C "your_email@example.com"

 go to ~/.ssh

 - pbcopy < ~/.ssh/id_rsa.pub