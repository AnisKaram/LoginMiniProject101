package mobile.computing.myloginapp.practice102;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button signInBTN, signUpBTN;
    FrameLayout mainFrameLayout;
    TextView welcomeTV, signInUpTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInBTN = (Button) findViewById(R.id.signinButton);
        signUpBTN = (Button) findViewById(R.id.signupButton);
        mainFrameLayout = (FrameLayout) findViewById(R.id.mainFrameLayout);
        welcomeTV = (TextView) findViewById(R.id.welcomeTextView);
        signInUpTV = (TextView) findViewById(R.id.signInUpTextView);

        signInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment signIn_Fragment = new SignInFragment();
                replaceFragment(signIn_Fragment);
            }
        });

        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment signUp_Fragment = new SignUpFragment();
                welcomeTV.setEnabled(false);
                signInUpTV.setEnabled(false);
                replaceFragment(signUp_Fragment);
            }
        });
    }

    public void replaceFragment(Fragment newFragment)
    {
        // First get the FragmentManager.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the layout holder with the required fragment object.
        fragmentTransaction.replace(R.id.mainFrameLayout, newFragment);

        // Commit the fragment replace action.
        fragmentTransaction.commit();
    }
}