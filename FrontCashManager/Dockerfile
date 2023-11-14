FROM node:20 AS build

WORKDIR /app

COPY package*.json ./

RUN npm install
RUN npm install -D tailwindcss
RUN npm install vue-chartjs chart.js

EXPOSE 8081

CMD ["npm", "run", "dev"]
