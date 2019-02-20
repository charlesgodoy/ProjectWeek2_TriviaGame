package com.yodo.caz.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Utils {
    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }

    public static String GetRespFromHttpUrl(URL url) throws IOException {
        /*General method to get HttpResponse from a url */
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try{
            InputStream is = con.getInputStream();

            Scanner scan = new Scanner(is);
            scan.useDelimiter("\\A");

            boolean hasinput = scan.hasNext();
            if(hasinput){
                return scan.next();
            }else{
                return null;
            }

        }catch (FileNotFoundException fne){
            fne.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            con.disconnect();
        }
        return null;
    }
}
