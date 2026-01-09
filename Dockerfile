FROM node:20-alpine

WORKDIR /app

COPY server.js ./server.js

ENV NODE_ENV=production
EXPOSE 5101

CMD ["node", "server.js"]
