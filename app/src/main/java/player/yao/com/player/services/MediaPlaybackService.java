package player.yao.com.player.services;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import player.yao.com.player.R;

/**
 * Created by yaoshining on 2017/3/6.
 */

public class MediaPlaybackService extends MediaBrowserService {

    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;
    private final String LOG_TAG = "TAG";
    private final String MY_MEDIA_ROOT_ID = getString(R.string.app_name);

    @Override
    public void onCreate() {
        super.onCreate();

        mediaSession = new MediaSessionCompat(getApplicationContext(), LOG_TAG);
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                              MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        stateBuilder = new PlaybackStateCompat.Builder();
        stateBuilder.setActions(PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE);
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        if(TextUtils.equals(clientPackageName, getPackageName())) {
            return new BrowserRoot(MY_MEDIA_ROOT_ID, null);
        }
        return null;
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaItem>> result) {
        if (TextUtils.isEmpty(parentId)) {
            result.sendResult(null);
            return;
        }

        List<MediaItem> mediaItems = new ArrayList<>();

        if (MY_MEDIA_ROOT_ID.equals(parentId)) {

            // build the MediaItem objects for the top level,
            // and put them in the mediaItems list
        } else {

            // examine the passed parentMediaId to see which submenu we're at,
            // and put the children of that menu in the mediaItems list
        }
        result.sendResult(mediaItems);
    }
}
