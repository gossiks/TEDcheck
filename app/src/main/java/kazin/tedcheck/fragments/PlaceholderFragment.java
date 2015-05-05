package kazin.tedcheck.fragments;

import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import kazin.tedcheck.R;
import kazin.tedcheck.network.RssItem;

/**
 * Created by Alexey on 20.04.2015.
 */
    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private RssItem mItem;

        public static PlaceholderFragment newInstance(int sectionNumber, RssItem item) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            fragment.setItem(item);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        private void setItem(RssItem item){
            mItem = item;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView title = (TextView) rootView.findViewById(R.id.title);
            TextView description = (TextView) rootView.findViewById(R.id.description);
            VideoView videoView = (VideoView) rootView.findViewById(R.id.videoView);


            Uri uriVideo = null;
            try {
                uriVideo = mItem.getUrl();
            } catch (Exception e) {
                e.printStackTrace();
            }
            videoView.setVideoURI(uriVideo);
            description.setText(mItem.getDescription());
            title.setText(mItem.getTitle());


            MediaController vidControl = new MediaController(getActivity());
            vidControl.setAnchorView(videoView);
            videoView.setMediaController(vidControl);

            return rootView;
        }

    }

