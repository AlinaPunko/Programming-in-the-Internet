var ta; //texarea
var ws=null; //WebSocket
var bstart, bstop;

window.onload=function () {
    if(Modernizr.websockets){
        WriteMessage('support', 'да');
        ta = document.getElementById('ta');
        bstart=document.getElementById('ta');
        bstop=document.getElementById('bstop');
        bstart.disabled = false;
        bstop.disabled = true;
    }
}

function  WriteMessage(idspan, txt) {
    var span=document.getElementById(idspan);
    span.innerHTML=txt;
}

function exe_start() {
    if(ws==null){
        ws=new WebSocket('ws://localhost:8080/Lab16_war/socket');
        ws.onopen=function () {
            ta.innerHTML+='\n'+'Connection';
            ws.send("hello");
        }
        ws.onclose=function (s) {
            console.log('onclose',s);
        }
        ws.onmessage=function (evt) {
            ta.innerHTML+='\n'+evt.data;
        }
        bstart.disabled=true;
        bstop.disabled=false;
    }
}

function exe_stop() {
    ws.close(3001,' stopapplication');
    ws=null;
    bstart.disabled=false;
    bstop.disabled=true;
}