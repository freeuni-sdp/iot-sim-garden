var WebSocketServer = require('ws').Server
  , http = require('http')
  , express = require('express')
  , app = express();

var port = process.env.PORT || 5000;

app.set('view engine', 'pug');
app.use(express.static('public'));

app.get('/', function(req, res){
  res.render('home.pug', {title: 'garden simulator'} );
});

var server = http.createServer(app)
server.listen(port)

console.log("http server listening on %d", port)

var wss = new WebSocketServer({server: server})
console.log("websocket server created")

wss.on("connection", function(ws) {
  var id = setInterval(function() {
    var readings = [Math.random(),Math.random(),Math.random()]
    ws.send(JSON.stringify(readings), function() {  })
  }, 1000)

  console.log("websocket connection open")

  ws.on("close", function() {
    console.log("websocket connection close")
    clearInterval(id)
  })
})