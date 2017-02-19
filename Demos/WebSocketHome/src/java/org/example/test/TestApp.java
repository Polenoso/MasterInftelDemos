/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.test;

import java.net.URI;
import java.net.URISyntaxException;
import org.example.javaclient.WebSocketClientEndpoint;

public class TestApp {

    public static void main(String[] args) {
        try {
            // open websocket
            final WebSocketClientEndpoint clientEndPoint = new WebSocketClientEndpoint(new URI("ws://localhost:8080/WebSocketHome/actions"));

            // add listener
            clientEndPoint.addMessageHandler(new WebSocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    //System.out.println(message);
                }
            });

            // send message to websocket
            clientEndPoint.sendMessage("Hola he enviado un mensaje desde el TestApp");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
