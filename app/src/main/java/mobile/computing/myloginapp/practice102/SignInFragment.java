package mobile.computing.myloginapp.practice102;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInFragment extends Fragment {

    TextView emailTV, passwordTV;
    EditText emailET, passwordET;
    Button signInBT, resetPassBT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View signInView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        emailTV = (TextView) signInView.findViewById(R.id.emailTextView);
        passwordTV = (TextView) signInView.findViewById(R.id.passwordTextView);
        emailET = (EditText) signInView.findViewById(R.id.emailEditText);
        passwordET = (EditText) signInView.findViewById(R.id.passwordEditText);
        signInBT = (Button) signInView.findViewById(R.id.signinButtonFrag);
        resetPassBT = (Button) signInView.findViewById(R.id.resetButton);

        resetPassBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Fragment resetPassword_Frag = new ResetPasswordFragment();
                FragmentManager fragment_Manager = getFragmentManager();
                fragment_Manager.beginTransaction().replace(R.id.mainFrameLayout, resetPassword_Frag, resetPassword_Frag.getTag()).commit();
            }

        });

        signInBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_Input = emailET.getText().toString();
                String password_Input = passwordET.getText().toString();

                int i = 0;
                while (i <= Database.ArrayList.size())
                {

                    if (Database.ArrayList.isEmpty())
                    {
                        Toast.makeText(getContext(), "Nothing Found, try to register first!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    AllUsers user = Database.ArrayList.get(i);


                    if (user.getEmail().isEmpty() && user.getPassword().isEmpty())
                    {
                        Toast.makeText(getContext(), "Sign up first", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    // email and password are correct
                    else if (email_Input.equals(user.getEmail()) && password_Input.equals(user.getPassword()))
                    {
                        Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();

                        emailET.setText(null);
                        passwordET.setText(null);

                        break;
                    }

                    // email is correct (& registered), password is not correct
                    else if (email_Input.equals(user.getEmail()) && !password_Input.equals(user.getPassword()))
                    {
                        Toast.makeText(getContext(), "Invalid Password, Try Again.", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    // email is not registered
                    else if (!email_Input.equals(user.getEmail()) /*&& password_Input.isEmpty()*/)
                    {
                        Toast.makeText(getContext(), "You need to Register first!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    // If the user did not input anything
                    else if (email_Input.isEmpty() && password_Input.isEmpty()){
                        Toast.makeText(getContext(), "Invalid Email or Password, Try Again!", Toast.LENGTH_LONG).show();
                        break;
                    }
                    else if (email_Input.isEmpty() || password_Input.isEmpty())
                    {
                        Toast.makeText(getContext(), "Invalid Email or Password, Try Again!", Toast.LENGTH_LONG).show();
                        break;
                    }
                    i++;
                }
            }
        });
        return signInView;
    }
}

