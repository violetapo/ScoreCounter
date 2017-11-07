package ua.napps.scorekeeper.dice;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.DrawableRes;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import ua.com.napps.scorekeeper.R;

public class DiceActivity extends AppCompatActivity {

    TextView debugInfo;

    ImageView dice;

    SpringForce springForce;

    TextView tapOnMe;

    private SpringAnimation springAnimation;

    private DiceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        ImageButton backArrow = findViewById(R.id.btn_back);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.light_status_bar));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        dice = findViewById(R.id.dice);
        debugInfo = findViewById(R.id.debug_info);
        tapOnMe = findViewById(R.id.tv_tap_on_me);

        springForce = new SpringForce()
                .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_LOW)
                .setFinalPosition(1);

        dice.setOnClickListener(v -> viewModel.rollDice());
        backArrow.setOnClickListener(v -> finish());

        viewModel = ViewModelProviders.of(this).get(DiceViewModel.class);

        subscribeToModel();
    }

    private void rollDice(int diceResult, int previousResult) {
        int prevValue;
        if (previousResult == 0) {
            tapOnMe.setVisibility(View.GONE);
            prevValue = ((int) (Math.random() * 6)) + 1;
        } else {
            prevValue = previousResult;
            debugInfo.setText(String.format("Previous result: %d", previousResult));
            ((Animatable) dice.getDrawable()).stop();
            if (springAnimation != null) {
                springAnimation.cancel();
            }
        }

        @DrawableRes int diceResId = 0;

        switch (diceResult) {
            case 1: {
                switch (prevValue) {

                    case 2: {
                        diceResId = R.drawable.avd_two_to_one;
                        break;
                    }
                    case 3: {
                        diceResId = R.drawable.avd_three_to_one;
                        break;
                    }
                    case 4: {
                        diceResId = R.drawable.avd_four_to_one;
                        break;
                    }
                    case 5: {
                        diceResId = R.drawable.avd_five_to_one;
                        break;
                    }
                    case 6: {
                        diceResId = R.drawable.avd_six_to_one;
                        break;
                    }
                }
                break;
            }
            case 2: {
                switch (prevValue) {
                    case 1: {
                        diceResId = R.drawable.avd_one_to_two;
                        break;
                    }
                    case 3: {
                        diceResId = R.drawable.avd_three_to_two;
                        break;
                    }
                    case 4: {
                        diceResId = R.drawable.avd_four_to_two;
                        break;
                    }
                    case 5: {
                        diceResId = R.drawable.avd_five_to_two;
                        break;
                    }
                    case 6: {
                        diceResId = R.drawable.avd_six_to_two;
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (prevValue) {
                    case 1: {
                        diceResId = R.drawable.avd_one_to_three;
                        break;
                    }
                    case 2: {
                        diceResId = R.drawable.avd_two_to_three;
                        break;
                    }
                    case 4: {
                        diceResId = R.drawable.avd_four_to_three;
                        break;
                    }
                    case 5: {
                        diceResId = R.drawable.avd_five_to_three;
                        break;
                    }
                    case 6: {
                        diceResId = R.drawable.avd_six_to_three;
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (prevValue) {
                    case 1: {
                        diceResId = R.drawable.avd_one_to_four;
                        break;
                    }
                    case 2: {
                        diceResId = R.drawable.avd_two_to_four;
                        break;
                    }
                    case 3: {
                        diceResId = R.drawable.avd_three_to_four;
                        break;
                    }
                    case 5: {
                        diceResId = R.drawable.avd_five_to_four;
                        break;
                    }
                    case 6: {
                        diceResId = R.drawable.avd_six_to_four;
                        break;
                    }

                }
                break;
            }
            case 5: {
                switch (prevValue) {
                    case 1: {
                        diceResId = R.drawable.avd_one_to_five;
                        break;
                    }
                    case 2: {
                        diceResId = R.drawable.avd_two_to_five;
                        break;
                    }
                    case 3: {
                        diceResId = R.drawable.avd_three_to_five;
                        break;
                    }
                    case 4: {
                        diceResId = R.drawable.avd_four_to_five;
                        break;
                    }
                    case 6: {
                        diceResId = R.drawable.avd_six_to_five;
                        break;
                    }
                }
                break;
            }
            case 6: {
                switch (prevValue) {
                    case 1: {
                        diceResId = R.drawable.avd_one_to_six;
                        break;
                    }
                    case 2: {
                        diceResId = R.drawable.avd_two_to_six;
                        break;
                    }
                    case 3: {
                        diceResId = R.drawable.avd_three_to_six;
                        break;
                    }
                    case 4: {
                        diceResId = R.drawable.avd_four_to_six;
                        break;
                    }
                    case 5: {
                        diceResId = R.drawable.avd_five_to_six;
                        break;
                    }
                }

            }

        }
        springAnimation = getSpringAnimation();
        springAnimation.start();
        if (diceResId != 0) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat
                    .create(this, diceResId);
            dice.setImageDrawable(animatedVectorDrawableCompat);
            ((Animatable) dice.getDrawable()).start();

        }

    }

    private SpringAnimation getSpringAnimation() {
        return new SpringAnimation(dice, DynamicAnimation.ROTATION).setSpring(springForce)
                .setStartValue(100f).setStartVelocity(100);
    }

    private void subscribeToModel() {

        final DiceLiveData diceLiveData = viewModel.getDiceLiveData();
        diceLiveData.observe(this, roll -> {
            if (roll != null && roll > 0) {
                final int previousValue = diceLiveData.getPreviousValue();
                rollDice(roll, previousValue);
            }
        });
    }
}