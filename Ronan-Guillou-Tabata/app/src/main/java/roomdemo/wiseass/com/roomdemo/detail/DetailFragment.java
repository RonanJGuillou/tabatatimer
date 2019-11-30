package roomdemo.wiseass.com.roomdemo.detail;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import roomdemo.wiseass.com.roomdemo.R;
import roomdemo.wiseass.com.roomdemo.RoomDemoApplication;
import roomdemo.wiseass.com.roomdemo.data.ListItem;
import roomdemo.wiseass.com.roomdemo.list.ListActivity;
import roomdemo.wiseass.com.roomdemo.tabata.TabataActivity;
import roomdemo.wiseass.com.roomdemo.viewmodel.ListItemViewModel;


public class DetailFragment extends Fragment {

    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";

    private ImageButton back;
    private ImageButton done;

    private TextView dateAndTime;
    private TextView message;
    private TextView preparation;
    private TextView travail;
    private TextView reposcourt;
    private TextView nbrCycles;
    private TextView nbrSeries;
    private TextView reposLong;
    private ImageView coloredBackground;

    private String itemId;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ListItemViewModel listItemViewModel;

    public DetailFragment() {
    }


    public static DetailFragment newInstance(String itemId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_ITEM_ID, itemId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((RoomDemoApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);

        Bundle args = getArguments();

        this.itemId = args.getString(EXTRA_ITEM_ID);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set up and subscribe (observe) to the ViewModel
        listItemViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ListItemViewModel.class);

        listItemViewModel.getListItemById(itemId).observe(this, new Observer<ListItem>() {
            @Override
            public void onChanged(@Nullable ListItem listItem) {
                if (listItem != null) {
                    dateAndTime.setText(listItem.getItemId());
                    preparation.setText("Préparation : " + listItem.getPreparation());
                    travail.setText("Travail : " + listItem.getTravail());
                    reposcourt.setText("Repos : " + listItem.getReposcourt());
                    nbrCycles.setText("Nombre de Cycles : " + listItem.getNbrCycles());
                    nbrSeries.setText("Nombre de Séries : " + listItem.getNbrSeries());
                    reposLong.setText("Repos entre séries : " + listItem.getReposLong());
                    message.setText("Nom : " + listItem.getMessage());
                    coloredBackground.setImageResource(listItem.getColorResource());
                }

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

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
                startTabataActivity(itemId, v);
            }
        });

        dateAndTime = v.findViewById(R.id.lbl_date_and_time_header);
        message = v.findViewById(R.id.lbl_message_body);
        preparation = v.findViewById(R.id.lbl_preparation_body);
        travail = v.findViewById(R.id.lbl_travail_body);
        reposcourt = v.findViewById(R.id.lbl_reposcourt_body);
        nbrCycles = v.findViewById(R.id.lbl_nbr_cycles_body);
        nbrSeries = v.findViewById(R.id.lbl_nbr_series_body);
        reposLong = v.findViewById(R.id.lbl_reposlong_body);
        coloredBackground = v.findViewById(R.id.imv_colored_background);

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void startListActivity() {
        startActivity(new Intent(getActivity(), ListActivity.class));
    }

    public void startTabataActivity(String itemId, View viewRoot) {
        Activity container = getActivity();
        Intent i = new Intent(container, TabataActivity.class);
        i.putExtra(EXTRA_ITEM_ID, itemId);
        startActivity(i);
    }

}



















