package com.example.laptopshop;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class ServerClient extends AsyncTask<String,Void,String>
{
    protected static String answer;

    @Override
    protected String doInBackground(String... strings) {
        try
        {
            sendToServer(strings[0]);
            Log.e("Message_TAG",strings[0]);
        }
        catch(Exception ex){
            Log.d("Exception_TAG",ex.getMessage());
        }
        return answer;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        Log.e("Post-execute answer from server : ", answer);
    }

    public static void sendToServer(String query)
    {
        Log.e("Query_TAG",query);
        int message_size = query.length();
        ByteBuffer request = ByteBuffer.allocate(message_size);
        request.put(query.getBytes(),0,message_size);

        try
        {
            connectToServer();
            transmitToServer(request);
            readFromServer();
            disconnectFromServer();
        }
        catch (Exception e)
        {
            Log.i("TAG","sendToServer : "+e.getClass().getSimpleName()+" "+e.getMessage());
            e.printStackTrace();
        }
    }

    protected static InputStream inp;
    protected static Socket client;

    public static void connectToServer() throws IOException {
        client = new Socket();
        SocketAddress adr = new InetSocketAddress("192.168.1.9", 5357);
        client.connect(adr);
    }

    public static void transmitToServer(ByteBuffer request) throws IOException {
        OutputStream out = client.getOutputStream();
        out.write(request.array());
        out.flush();
    }

    public static void readFromServer() throws IOException {
        inp = client.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
        answer = reader.readLine();
    }

    public static void disconnectFromServer() throws IOException {
        inp.close();
        client.close();
    }
}
