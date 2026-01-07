FROM node:20-alpine

WORKDIR /app

RUN node -e "require('fs').writeFileSync('server.js', \
`const http=require('http'); \
http.createServer((req,res)=>{res.end('OK');}).listen(5101,'0.0.0.0'); \
console.log('listening 5101');` )"

EXPOSE 5101
CMD ["node", "server.js"]
