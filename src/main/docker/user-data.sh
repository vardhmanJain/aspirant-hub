#!/bin/bash
# Update system
dnf update -y

# Install required packages
dnf install -y yum-utils device-mapper-persistent-data lvm2 git nginx

# Add Docker CE repo
sudo dnf remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine \
                  podman \
                  runc

sudo dnf -y install dnf-plugins-core
sudo dnf config-manager --add-repo https://download.docker.com/linux/rhel/docker-ce.repo
sudo dnf install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
# Install Docker
dnf install -y docker-ce docker-ce-cli containerd.io

# Start and enable Docker
systemctl start docker
systemctl enable docker

# Add ec2-user to docker group
usermod -aG docker ec2-user

# clone project repo
git clone https://github.com/vardhmanJain/aspirant-hub.git

# change directory
cd /home/ec2-user/aspirant-hub/src/main/docker

# docker compose up
docker compose up


