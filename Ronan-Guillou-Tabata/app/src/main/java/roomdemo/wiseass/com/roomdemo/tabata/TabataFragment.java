package roomdemo.wiseass.com.roomdemo.tabata;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import roomdemo.wiseass.com.roomdemo.R;
import roomdemo.wiseass.com.roomdemo.RoomDemoApplication;
import roomdemo.wiseass.com.roomdemo.data.ListItem;
import roomdemo.wiseass.com.roomdemo.list.ListActivity;
import roomdemo.wiseass.com.roomdemo.timer.OnUpdateListener;
import roomdemo.wiseass.com.roomdemo.timer.timer;
import roomdemo.wiseass.com.roomdemo.viewmodel.ListItemViewModel;

public class TabataFragment extends Fragment implements OnUpdateListener {


    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";

    @Override
    public Context getContext() {
        return super.getContext();
    }

    private ImageButton back;
    private ImageButton done;
    private Button play;
    private TextView dateAndTime;
    private TextView message;
    private ImageView coloredBackground;
    private TextView timerValue;
    private timer timer;
    private int colorressource;
    private long worktime;
    private long downtime;
    private long longdowntime;
    private long preptime;
    private long cycles;
    private long initialCycles;
    private long sequences;
    private String state;

    private String itemId;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ListItemViewModel listItemViewModel;
    private TextView whatrwedoing;

    public TabataFragment() {
    }

    public static TabataFragment newInstance(String itemId) {
        TabataFragment fragment = new TabataFragment();
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

        // pas d'instance sauvegardée -> on initialise le timer avec les valeurs de base
        if (savedInstanceState == null) {

            //Set up and subscribe (observe) to the ViewModel
            listItemViewModel = ViewModelProviders.of(this, viewModelFactory)
                    .get(ListItemViewModel.class);

            listItemViewModel.getListItemById(itemId).observe(this, new Observer<ListItem>() {
                @Override
                public void onChanged(@Nullable ListItem listItem) {
                    if (listItem != null) {
                        dateAndTime.setText(listItem.getItemId());
                        message.setText("Nom : " + listItem.getMessage());
                        colorressource = listItem.getColorResource();
                        coloredBackground.setImageResource(colorressource);
                        preptime = listItem.getPreparation();
                        downtime = listItem.getReposcourt();
                        longdowntime = listItem.getReposLong();
                        worktime = listItem.getTravail();
                        cycles = listItem.getNbrCycles();
                        initialCycles = cycles;
                        sequences = listItem.getNbrSeries();
                        state = "initial";

                        StartTimer();

                    }

                }
            });
        } else {
            // une instance sauvegardée -> on récupère les datas, et on passe dans preptime la valeur actuelle du timer
            // si c'est une préparation, tout est bon, sinon le type dira à la fonction timer ce à quoi preptime se réfère.

            dateAndTime.setText(savedInstanceState.getString("dateAndTime"));
            message.setText(savedInstanceState.getString("message"));
            colorressource = savedInstanceState.getInt("colorressource");
            coloredBackground.setImageResource(colorressource);
            preptime = savedInstanceState.getLong("preptime");
            downtime = savedInstanceState.getLong("downtime");
            longdowntime = savedInstanceState.getLong("longdowntime");
            worktime = savedInstanceState.getLong("worktime");
            cycles = savedInstanceState.getLong("cycles");
            sequences = savedInstanceState.getLong("sequences");
            state = savedInstanceState.getString("state");
            initialCycles = savedInstanceState.getLong("initialCycles");

            StartTimer();
            timer.start();

        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabata, container, false);

        // Récupérer la view
        timerValue = (TextView) v.findViewById(R.id.timerValue);

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
                timer.reset();
            }
        });

        play = v.findViewById(R.id.playButton);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        dateAndTime = v.findViewById(R.id.lbl_date_and_time_header);
        message = v.findViewById(R.id.lbl_message_body);
        coloredBackground = v.findViewById(R.id.imv_colored_background);
        whatrwedoing = v.findViewById(R.id.lbl_whatarewedoing);

        return v;
    }

    private void StartTimer() {
        // Initialiser l'objet Compteur
        // constructeur très modifié depuis le compteur de base :
        // getContext sert à donner à la classe timer le contexte afin de permettre la vibration
        // le reste sert à déterminer les différents temps et l'état d'avancement
        timer = new timer(preptime, worktime, downtime, longdowntime, cycles, initialCycles, sequences, state, getContext());

        // Abonner l'activité au compteur pour "suivre" les événements
        timer.addOnUpdateListener(this);

        // Mise à jour graphique
        miseAJour();

    }


    private void startListActivity() {
        startActivity(new Intent(getActivity(), ListActivity.class));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    // Remettre à zéro le compteur
    public void onReset(View view) {
        timer.reset();
    }

    // Mise à jour graphique
    private void miseAJour() {

        // Affichage des informations du compteur
        timerValue.setText(""+ String.format("%02d", timer.getSecondes()) );
        whatrwedoing.setText(""+ timer.getActionName());

    }

    /**
     * Méthode appelée à chaque update du compteur (l'activité est abonnée au compteur)
     *
     */
    public void onUpdate() {
        miseAJour();
    }

    /**
     * Gestion du retournement du téléphone et des interruptions
     *
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        savedInstanceState.putLong("downtime", downtime);
        savedInstanceState.putLong("longdowntime", longdowntime);
        savedInstanceState.putLong("worktime", worktime);
        savedInstanceState.putLong("cycles", timer.getCycleNumber());
        savedInstanceState.putLong("sequences", timer.getSeqNumber());
        savedInstanceState.putInt("colorressource", colorressource);
        savedInstanceState.putString("message", message.getText().toString());
        savedInstanceState.putString("dateAndTime", dateAndTime.getText().toString());
        savedInstanceState.putString("state", timer.getTimerState());
        savedInstanceState.putLong("initialCycleNumber", timer.getInitialCycleNumber());
        savedInstanceState.putLong("preptime", timer.getSecondes());

        super.onSaveInstanceState(savedInstanceState);

    }



}
