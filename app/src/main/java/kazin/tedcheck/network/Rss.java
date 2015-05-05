package kazin.tedcheck.network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import kazin.tedcheck.MainActivity;


/**
 * Created by Alexey on 20.04.2015.
 */
public class Rss {


    public void get(final String urlFeed, final MainActivity.CallbackRss callback){
        AsyncTask getAsync = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                List<RssItem> articles = httpRequest(urlFeed);
                callback.OnLoaded(articles);
                return null;
            }
        };
        getAsync.execute();
    }

    private List<RssItem> httpRequest(String urlFeed){

        List<RssItem> rssItems = new ArrayList<>();
        try {
            URL url = new URL(urlFeed);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream is = conn.getInputStream();

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document document = db.parse(is);
                Element element = document.getDocumentElement();

                NodeList nodeList = element.getElementsByTagName("item");

                rssItems = parseNodeList(nodeList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return rssItems;
    }

    private List<RssItem> parseNodeList(NodeList nodeList) {
        ArrayList<RssItem> rssItems = new ArrayList<RssItem>();
        if (nodeList.getLength()>0){
            for (int i = 0;i< nodeList.getLength();i++){
                Element entry = (Element) nodeList.item(i);
                Element title = (Element)entry.getElementsByTagName("title").item(0);
                Element description = (Element) entry.getElementsByTagName("description").item(0);
                Element enclosure = (Element) entry.getElementsByTagName("enclosure").item(0);

                String titleString = title.getFirstChild().getNodeValue();
                String descriptionString = description.getFirstChild().getNodeValue();
                String urlString = enclosure.getAttribute("url");

                RssItem rssItem = new RssItem(titleString, descriptionString, Uri.parse(urlString));

                rssItems.add(rssItem);
            }
        }
        return rssItems;
    }
}
