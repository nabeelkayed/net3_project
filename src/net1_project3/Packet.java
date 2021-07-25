package net1_project3;

import java.nio.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 
 */
public class Packet {

    static final int MAX_DATA_LENGTH = 1024;

    private int dataLength = 0;
    private int Checksum = 0;
    private int type = 0;
    private int sequence_number = 0;

    private byte[] data = new byte[MAX_DATA_LENGTH];

    public ByteBuffer buffer = null;

    static final int HEADER_LENGTH = (int) (4 + 4 + 4 + 4);

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public int getChecksum() {
        return Checksum;
    }

    public void setChecksum(int Checksum) {
        this.Checksum = Checksum;
    }

    public int gettype() {
        return type;
    }

    public void settype(int type) {
        this.type = type;
    }

    public int getsequence_number() {
        return sequence_number;
    }

    public void setsequence_number(int sequence_number) {
        this.sequence_number = sequence_number;
    }

    public Packet() {
        init('A', 0, 0);
    }

    public Packet(int type, int Checksum, int sequence_number) {
        this.init(type, Checksum, sequence_number);
    }

    private void init(int type, int Checksum, int sequence_number) {
        this.type = type;
        this.Checksum = Checksum;
        this.sequence_number = sequence_number;
        dataLength = 0;
        buffer = null;

    }

    public boolean addData(byte[] data, int n) {
        dataLength = n;
        if (n > MAX_DATA_LENGTH) {
            System.out.println("Data too big");
            return false;
        }
        System.arraycopy(data, 0, this.data, 0, n);
        return true;
    }

    public ByteBuffer toByteBuffer() {
        buffer = ByteBuffer.allocate(HEADER_LENGTH + MAX_DATA_LENGTH);
        buffer.clear();
        buffer.putInt(Checksum);
        buffer.putInt(type);
        buffer.putInt(sequence_number);
        buffer.putInt(dataLength);
        buffer.put(data, 0, dataLength);
        return buffer;

    }

    public String printPacket() {
        String S = "";
        S += ("Packet Cotents= { ");
        S += ("\n\ttype: " + this.type);
        S += ("\n\tsequence_number: " + this.sequence_number);
        S += ("\n\tChecksum: " + this.Checksum);
        S += ("\n\tdataLength: " + this.dataLength);

        S += "\n\tdata =[\n\t";
        String nnString = new String(data);
        S += nnString;
        S += "]";
        S += "\n}\n";
        return S;

    }

    public int getPacketLength() {
        return HEADER_LENGTH + dataLength;
    }

    public void extractPacketfromByteBuffer(ByteBuffer buf) {
        try {
            int Checksum1 = buf.getInt(0);
            int type1 = buf.getInt(0 + 4);
            int sequence_number1 = buf.getInt(0 + 4 + 4);

            int dataLen = buf.getInt(0 + 4 + 4 + 4);
            byte[] ba = new byte[dataLen];
            for (int i = 0; i < dataLen; i++) {
                byte bt = (byte) buf.get(0 + 4 + 4 + 4 + 4 + i);
                ba[i] = bt;
            }
            this.type = type1;
            this.data = ba;
            this.dataLength = dataLen;
            this.sequence_number = sequence_number1;
            this.Checksum = Checksum1;

        } catch (Exception e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);

        }
    }
}
