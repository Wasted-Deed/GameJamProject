package wasteed.project.gamejamproject.maintenance;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

import wasteed.project.gamejamproject.R;

public class ResourceLoader {
    private static HashMap<Image, Bitmap> resourceMap;

    enum Image {
        MONEY, ARMY, PEOPLE
    }

    public static void loadResources(Resources resources) {
        resourceMap = new HashMap<>();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        resourceMap.put(Image.MONEY, BitmapFactory.decodeResource(resources,
                R.drawable.kazna, options));
        resourceMap.put(Image.ARMY, BitmapFactory.decodeResource(resources,
                R.drawable.mech, options));
        resourceMap.put(Image.PEOPLE, BitmapFactory.decodeResource(resources,
                R.drawable.narod, options));
    }

    public static Bitmap getBitmap(Image image) {
        return resourceMap.get(image);
    }
}
