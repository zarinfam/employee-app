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

# Install kind For Intel Macs
[ $(uname -m) = x86_64 ] && curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.25.0/kind-linux-amd64
chmod +x ./kind
sudo mv ./kind /usr/local/bin/kind

kind delete cluster --name employee 
kind create cluster --name employee

echo "[END] Install dev tools"