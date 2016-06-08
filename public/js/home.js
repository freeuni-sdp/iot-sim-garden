
$(function () {

  var HOST = location.origin.replace(/^http/, 'ws')
  var ws = new WebSocket(HOST);

  ws.onmessage = function (event) {
    var data = JSON.parse(event.data);
    $("#sen1").html(data[0])
    $("#sen2").html(data[1])
    $("#sen3").html(data[2])
  };

});