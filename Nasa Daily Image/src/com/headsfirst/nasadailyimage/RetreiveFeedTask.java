package com.headsfirst.nasadailyimage;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class RetreiveFeedTask extends AsyncTask<String, Void, IotdHandler> {

    public IotdHandler iotdHandler = new IotdHandler();
    public ProgressDialog progressBar;
    public Context taskCon;
    
    public RetreiveFeedTask(Context con) {
    	this.taskCon = con;
    }
    
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        progressBar = ProgressDialog.show(
				taskCon,
				"Loading",
				"Loading the image of the Day");
    }
    @Override
    protected void onPostExecute(IotdHandler iotdHandler)
    {
        super.onPostExecute(iotdHandler);
        progressBar.dismiss();
    }
    
	@Override
	  protected IotdHandler doInBackground(String... urls) {
        try {
            URL url= new URL(urls[0]);
            SAXParserFactory factory =SAXParserFactory.newInstance();
            SAXParser parser=factory.newSAXParser();
            XMLReader xmlreader=parser.getXMLReader();
            xmlreader.setContentHandler(iotdHandler);
            InputSource is=new InputSource(url.openStream());
            xmlreader.parse(is);
            
            
            return iotdHandler;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }
}
