#!/bin/bash

echo "[START] Install dev tools"

sudo apt update

sudo apt install -y nano curl zip unzip git zsh bat httpie

git clone --depth=1 https://github.com/romkatv/powerlevel10k.git ${ZSH_CUSTOM:-$HOME/.oh-my-zsh/custom}/themes/powerlevel10k
# Change the oh my zsh default theme.
sed -i 's/ZSH_THEME=[^ ]*/ZSH_THEME="powerlevel10k\/powerlevel10k"/g' ~/.zshrc
echo "POWERLEVEL9K_DISABLE_CONFIGURATION_WIZARD=true" >> ~/.zshrc

cp  scripts/.aliases ~/.aliases
echo "source ~/.aliases" >> ~/.zshrc

kind delete cluster --name employee 
kind create cluster --name employee

kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.13.3/cert-manager.yaml
kubectl apply -f https://github.com/redhat-developer/service-binding-operator/releases/latest/download/release.yaml

echo "[END] Install dev tools"