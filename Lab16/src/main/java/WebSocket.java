import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;

@ServerEndpoint("/socket")
public class WebSocket extends Endpoint {
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig){
        System.out.println("open");
        try {
            RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
            while (true) {
                remoteEndpointBasic.sendText(format.format(new Date()));
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("close");

        super.onClose(session, closeReason);
    }


}
