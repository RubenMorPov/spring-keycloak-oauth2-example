# This prebuild is for installing curl to be able to copy it to Keycloak's image.
FROM registry.access.redhat.com/ubi9 AS ubi-micro-build
RUN mkdir -p /mnt/rootfs
RUN dnf install --installroot /mnt/rootfs curl --releasever 9 --setopt install_weak_deps=false --nodocs -y && \
    dnf --installroot /mnt/rootfs clean all && \
    rpm --root /mnt/rootfs -e --nodeps setup

# Main Keycloak image
FROM quay.io/keycloak/keycloak:22.0.3 as keycloak-healthcheck
LABEL authors="ruben.morchon"
# Importing CURL to be able to make easy health checks.
COPY --from=ubi-micro-build /mnt/rootfs /
# Importing a realm example
COPY realm-export.json /opt/keycloak/data/import/
# Exposing default port.
EXPOSE 8080
# Launching keycloak. This SH will retrieve the params of CMD.
ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]