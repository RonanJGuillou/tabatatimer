package roomdemo.wiseass.com.roomdemo.create;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import roomdemo.wiseass.com.roomdemo.R;
import roomdemo.wiseass.com.roomdemo.RoomDemoApplication;
import roomdemo.wiseass.com.roomdemo.data.ListItem;
import roomdemo.wiseass.com.roomdemo.list.ListActivity;
import roomdemo.wiseass.com.roomdemo.viewmodel.NewListItemViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {

    private ViewPager drawablePager;
    private CirclePageIndicator pageIndicator;
    private ImageButton back;
    private ImageButton done;
    private EditText messageInput;
    private EditText preparationInput;
    private EditText travailInput;
    private EditText reposcourtInput;
    private EditText nbrCyclesInput;
    private EditText nbrSeriesInput;
    private EditText reposLongInput;
    private PagerAdapter pagerAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private NewListItemViewModel newListItemViewModel;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        ((RoomDemoApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }

    public static CreateFragment newInstance() {
        return new CreateFragment();
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set up and subscribe (observe) to the ViewModel
        newListItemViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewListItemViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);

        back = v.findViewById(R.id.imb_create_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListActivity();
            }
        });

        done = v.findViewById(R.id.imb_create_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListItem listItem = new ListItem(
                        getDate(),
                        messageInput.getText().toString(),
                        Long.parseLong(preparationInput.getText().toString()),
                        Long.parseLong(travailInput.getText().toString()),
                        Long.parseLong(reposcourtInput.getText().toString()),
                        Long.parseLong(nbrCyclesInput.getText().toString()),
                        Long.parseLong(nbrSeriesInput.getText().toString()),
                        Long.parseLong(reposLongInput.getText().toString()),
                        getDrawableResource(drawablePager.getCurrentItem())
                );
                newListItemViewModel.addNewItemToDatabase(listItem);

                startListActivity();
            }
        });


        messageInput = (EditText) v.findViewById(R.id.edt_create_message);
        preparationInput = (EditText) v.findViewById(R.id.edt_create_preparation);
        travailInput = (EditText) v.findViewById(R.id.edt_create_travail);
        reposcourtInput = (EditText) v.findViewById(R.id.edt_create_reposcourt);
        nbrCyclesInput = (EditText) v.findViewById(R.id.edt_create_nbrCycles);
        nbrSeriesInput = (EditText) v.findViewById(R.id.edt_create_nbrSeries);
        reposLongInput = (EditText) v.findViewById(R.id.edt_create_reposLong);
        drawablePager = (ViewPager) v.findViewById(R.id.vp_create_drawable);


        pagerAdapter = new DrawablePagerAdapter();
        drawablePager.setAdapter(pagerAdapter);

        pageIndicator = v.findViewById(R.id.vpi_create_drawable);
        pageIndicator.setViewPager(drawablePager);

        return v;
    }

    public int getDrawableResource (int pagerItemPosition){
        switch (pagerItemPosition){
            case 0:
                return R.drawable.red_drawable;
            case 1:
                return R.drawable.blue_drawable;
            case 2:
                return R.drawable.green_drawable;
            case 3:
                return R.drawable.yellow_drawable;
            default:
                return 0;
        }
    }

    private void startListActivity() {
        startActivity(new Intent(getActivity(), ListActivity.class));
    }

    private class DrawablePagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ImageView pagerItem = (ImageView) inflater.inflate(R.layout.item_drawable_pager,
                    container,
                    false);

            switch (position) {
                case 0:
                    pagerItem.setImageResource(R.drawable.red_drawable);
                    break;
                case 1:
                    pagerItem.setImageResource(R.drawable.blue_drawable);
                    break;
                case 2:
                    pagerItem.setImageResource(R.drawable.green_drawable);
                    break;
                case 3:
                    pagerItem.setImageResource(R.drawable.yellow_drawable);
                    break;
            }

            container.addView(pagerItem);
            return pagerItem;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    public String getDate() {

        Date currentDate = Calendar.getInstance().getTime();

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd/kk:mm:ss");

        return format.format(currentDate);
    }
}
