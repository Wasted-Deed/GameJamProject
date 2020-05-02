package wasteed.project.gamejamproject.maintenance;

import android.content.res.Resources;
import android.graphics.Bitmap;

import java.util.HashMap;

public class ResourceLoader {
    private static HashMap<Image, Bitmap> resourceMap;

    enum Image {
    }

    public static void loadResources(Resources resources) {
        resourceMap = new HashMap<>();
    }
}
