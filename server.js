const http = require("http");

const port = process.env.PORT || 5101;

http.createServer((req, res) => {
  res.end("OK");
}).listen(port, "0.0.0.0", () => {
  console.log("listening", port);
});
