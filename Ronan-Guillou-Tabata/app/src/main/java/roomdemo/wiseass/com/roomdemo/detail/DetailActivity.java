package roomdemo.wiseass.com.roomdemo.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import roomdemo.wiseass.com.roomdemo.R;
import roomdemo.wiseass.com.roomdemo.util.BaseActivity;

public class DetailActivity extends BaseActivity {

    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";
    private static final String DETAIL_FRAG = "DETAIL_FRAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent i = getIntent();

        if (i.hasExtra(EXTRA_ITEM_ID)) {
            String itemId = i.getStringExtra(EXTRA_ITEM_ID);

            FragmentManager manager = getSupportFragmentManager();

            DetailFragment fragment = (DetailFragment) manager.findFragmentByTag(DETAIL_FRAG);

            if (fragment == null) {
                fragment = DetailFragment.newInstance(itemId);
            }

            addFragmentToActivity(manager,
                    fragment,
                    R.id.root_activity_detail,
                    DETAIL_FRAG
            );

        } else {
            Toast.makeText(this, R.string.error_no_extra_found, Toast.LENGTH_LONG).show();
        }

    }
}
