FROM elixir:1.14

# Installer les paquets requis
RUN apt-get update && apt-get install -y \
    inotify-tools \
    postgresql-client

# Installer Hex et Phoenix
RUN mix local.hex --force && \
    mix local.rebar --force && \
    mix archive.install hex phx_new 1.6.2 --force

# Créer un répertoire pour votre application et définissez-le comme le répertoire de travail
COPY . ./app
WORKDIR /app

# Copiez les fichiers de configuration et installez les dépendances Elixir
COPY mix.exs mix.lock ./
RUN mix deps.clean --all
RUN mix deps.get
RUN mix deps.update --all
RUN mix compile

# Copiez tous les autres fichiers de votre application
EXPOSE 4000

# Commande pour démarrer votre application Phoenix
# CMD ["mix", "phx.server"]
RUN chmod +x /app/server_entry_point.sh
ENTRYPOINT ["/app/server_entry_point.sh"]