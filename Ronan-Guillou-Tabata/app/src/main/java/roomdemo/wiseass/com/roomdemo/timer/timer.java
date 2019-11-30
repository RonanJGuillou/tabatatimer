package roomdemo.wiseass.com.roomdemo.timer;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.io.Serializable;
import java.util.Objects;

import static android.content.Context.VIBRATOR_SERVICE;

public class timer extends UpdateSource implements Serializable {

    // DATA
    private long updatedTime;
    private long initialTime;

    private Context contexttimer;

    private Vibrator vibrator;

    private long initialCycleNumber;
    private String timerState;

    private long prepTime;
    private long workTime;
    private long downTime;
    private long longDownTime;

    private boolean didiwork;

    private long cycleNumber;
    private long seqNumber;
    private String ActionName;

    private CountDownTimer timer;   // https://developer.android.com/reference/android/os/CountDownTimer.html

    public timer(long prepTime, long work, long down, long longDown, long cycles, long initialCycles, long sequences, String state, Context context) {
        initialTime = prepTime*1000;
        updatedTime = prepTime*1000;
        workTime = work;
        downTime = down;
        longDownTime = longDown;
        cycleNumber = cycles;
        initialCycleNumber = initialCycles;
        seqNumber = sequences;

        timerState = state;

        contexttimer = context;

        switch (timerState) {
            case "initial":
                ActionName = "Préparation";
                didiwork = false;
                break;
            case "Travail":
                ActionName = timerState;
                didiwork = true;
                break;
            case "Repos":
                ActionName = timerState;
                didiwork = false;
                break;
            case "LongRepos":
                ActionName = timerState;
                didiwork = false;
                break;
        }

    }

    // Lancer le compteur
    public void start() {

        if (timer == null) {

            // Créer le CountDownTimer
            timer = new CountDownTimer(updatedTime, 10) {

                // Callback fired on regular interval
                public void onTick(long millisUntilFinished) {
                    updatedTime = millisUntilFinished;

                    // Mise à jour
                    update();
                }

                // Callback fired when the time is up
                public void onFinish() {
                    updatedTime = 0;

                    if (seqNumber > 1) {
                        if (cycleNumber != 0) {
                            if (!didiwork) {
                                didiwork = true;
                                nextTimer(workTime);
                                ActionName = "Travail";
                            } else {
                                didiwork = false;
                                cycleNumber --;
                                nextTimer(downTime);
                                ActionName = "Repos";
                            }
                        } else {
                            cycleNumber = initialCycleNumber;
                            seqNumber --;
                            didiwork = false;
                            nextTimer(longDownTime);
                            ActionName = "LongRepos";
                        }
                    } else {
                        if (cycleNumber != 0) {
                            if (!didiwork) {
                                didiwork = true;
                                nextTimer(workTime);
                                ActionName = "Travail";
                            } else {
                                didiwork = false;
                                cycleNumber --;
                                nextTimer(downTime);
                                ActionName = "Repos";
                            }
                        } else {
                            ActionName = "FINIIIIII";
                        }
                    }
                    shakeItBaby();
                    // Mise à jour
                    update();
                }

            }.start();   // Start the countdown
        } else {


            // Arreter le timer
            stop();

            // Mise à jour
            update();

        }

    }

    // Mettre en pause le compteur
    public void pause() {

        if (timer != null) {

            // Arreter le timer
            stop();

            // Mise à jour
            update();
        }
    }


    // Remettre à le compteur à la valeur initiale
    private void nextTimer(long value) {

        if (timer != null) {

            // Arreter le timer
            stop();
        }

        // Réinitialiser
        updatedTime = value*1000;
        initialTime = updatedTime;

        // Mise à jour
        update();

        start();

    }
    public void reset() {

        if (timer != null) {

            // Arreter le timer
            stop();
        }

        // Réinitialiser
        updatedTime = initialTime;

        // Mise à jour
        update();

    }


    // Arrete l'objet CountDownTimer et l'efface
    private void stop() {
        timer.cancel();
        timer = null;
    }

    public int getMinutes() {
        return getSecondes() / 60;
    }

    public int getSecondes() {
        int secs = (int) (updatedTime / 1000);
        return secs % 60;
    }

    public int getMillisecondes() {
        return (int) (updatedTime % 1000);
    }

    public String getActionName() {
        return this.ActionName;
    }

    public long getCycleNumber() {
        return this.cycleNumber;
    }

    public long getInitialCycleNumber() {
        return this.initialCycleNumber;
    }

    public long getSeqNumber() {
        return this.seqNumber;
    }

    public String getTimerState() {
        if (ActionName.equals("Préparation")){
            return this.timerState;
        }
        return this.ActionName;
    }

    // Vibraaaaaaaaaaaaaaaaaate
    private void shakeItBaby() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) Objects.requireNonNull(contexttimer.getSystemService(VIBRATOR_SERVICE))).vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) contexttimer.getSystemService(VIBRATOR_SERVICE)).vibrate(200);
        }
    }




}

