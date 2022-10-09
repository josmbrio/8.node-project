FROM node:18-alpine
RUN mkdir /app
WORKDIR /app
COPY app/package.json app/package-lock.json ./
RUN npm install
COPY ./app/ ./
CMD ["npm","start"]
