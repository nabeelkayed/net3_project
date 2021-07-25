/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net1_project3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class Timer1 {

    Client c;
    Timer timer;

    Timer1(Client aThis) {
        c = aThis;
    }

    public void Start() {
        timer = new Timer();
        timer.schedule(new RemindTask(), 500);
    }

    public void stop() {
        timer.cancel();
    }

    class RemindTask extends TimerTask {

        public void run() {
            try {
                String localIp1 = c.localIp;
                int localPort1 = c.localPort;
                InetAddress remot_IPAddress1 = InetAddress.getByName(localIp1);
                String msg = "timeout";
                byte[] S_buffer = msg.getBytes();
                DatagramPacket sendpacket1 = new DatagramPacket(S_buffer, S_buffer.length, remot_IPAddress1, localPort1);
                c.socket.send(sendpacket1);
                timer.cancel();
            } catch (UnknownHostException ex) {
                Logger.getLogger(Timer1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Timer1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
