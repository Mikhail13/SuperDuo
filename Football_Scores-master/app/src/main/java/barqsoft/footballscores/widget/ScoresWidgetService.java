package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViewsService;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ScoresWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ScoresRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
