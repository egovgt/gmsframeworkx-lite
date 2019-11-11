package lib.gmsframeworkx.utils;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * Created by root on 3/3/18.
 */

public class BottomNavDisable {

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);

        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {

                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
//                item.setShiftingMode(false);
                //noinspection RestrictedApi
//                item.setChecked(item.getItemData().isChecked());


            }
        } catch (NoSuchFieldException ex) {
//            Log.ERROR("BottomNavForceTitle", "Unable to get shift mode field", ex);
        } catch (IllegalAccessException e) {
//            Log.ERROR("BottomNavForceTitle", "Unable to change value of shift mode", e);
        }
    }
}
