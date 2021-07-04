<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8"/>
    <title>WebSockets</title>
    <script src="moderniz_ws.js"></script>
    <script src="WebSockets.js" type="text/javascript"></script>
  </head>
  <body>
    <header>
      <h1>Web Sockets</h1>
    </header>
    <section style="width: 800px; float:left">
      <div id="wsproperties">
        Web Sockets поддерживается?:
        <span id="support"></span>
        <br>
      </div>
    </section>
    <br>
    <section style="width: 215px; border: solid;">
      <div style="margin: 5px 5px 5px 5px">
        <input id="bstart" type="button" value="Старт" onclick="exe_start()" style="width: 100px"/>
        <input id="bstop" type="button" value="Стоп" onclick="exe_stop()" style="width: 100px"/>
        <textarea id="ta" rows="20" cols="25" style="text-align: center;" readonly>
        </textarea>
      </div>
    </section>
  </body>
</html>
