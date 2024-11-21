#!/bin/bash

echo "[START] Install dev tools"

sudo apt update

sudo apt install -y nano curl zip unzip git zsh bat httpie

sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

git clone --depth=1 https://github.com/romkatv/powerlevel10k.git ${ZSH_CUSTOM:-$HOME/.oh-my-zsh/custom}/themes/powerlevel10k
# Change the oh my zsh default theme.
sed -i 's/ZSH_THEME=[^ ]*/ZSH_THEME="powerlevel10k\/powerlevel10k"/g' ~/.zshrc
echo "POWERLEVEL9K_DISABLE_CONFIGURATION_WIZARD=true" >> ~/.zshrc

cp  scripts/.aliases ~/.aliases
echo "source ~/.aliases" >> ~/.zshrc

git config --global user.name "Saeed Zarinfam"
git config --global user.email zarinfam.s@gmail.com

minikube start -p employee 

echo "[END] Install dev tools"