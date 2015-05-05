package kazin.tedcheck.network;

import android.net.Uri;
import android.os.Parcelable;

/**
 * Created by Alexey on 20.04.2015.
 */
public class RssItem {
        String mTitle;
        String mDescription;
        Uri mUrl;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }



    public Uri getUrl() {
        return mUrl;
    }

    public void setUrl(Uri mUrl) {
        this.mUrl = mUrl;
    }



    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public RssItem(String title, String description, Uri url) {
            mTitle = title;
            mDescription = description;
            mUrl = url;
        }

    @Override
    public String toString() {
        return "Title"+ mTitle+" Description: "+mDescription+"Url: "+mUrl;
    }
}
