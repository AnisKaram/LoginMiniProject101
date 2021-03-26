package mobile.computing.myloginapp.practice102;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpFragment extends Fragment {

    TextView emailTV, passwordTV, confPassTV;
    EditText emailET, passwordET, confPassET;
    Button signUpBT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View signUpView =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        emailTV = (TextView) signUpView.findViewById(R.id.emailTextViewSignUpFrag);
        passwordTV = (TextView) signUpView.findViewById(R.id.passwordTextViewSignUpFrag);
        confPassTV = (TextView) signUpView.findViewById(R.id.confPassTextViewSignUpFrag);
        emailET = (EditText) signUpView.findViewById(R.id.emailEditTextSignUpFrag);
        passwordET = (EditText) signUpView.findViewById(R.id.passwordEditTextSignUpFrag);
        confPassET = (EditText) signUpView.findViewById(R.id.confPassEditTextSignUpFrag);
        signUpBT = (Button) signUpView.findViewById(R.id.signUpButtonSignUpFrag);


        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email_Input = emailET.getText().toString().trim().toLowerCase();
                String password_Input = passwordET.getText().toString();
                String confPAss_Input = confPassET.getText().toString();

                /**
                 * Each time the user click on the Register button, the email & password will be added to the ArrayList (Database class)
                 */
                int i = 0;
                while (i <= Database.ArrayList.size())
                {
                    if (!existUser(email_Input) && password_Input.equals(confPAss_Input) && !password_Input.isEmpty() && !email_Input.isEmpty())
                    {
                        AllUsers allUsers = new AllUsers(email_Input, password_Input);
                        Database.ArrayList.add(allUsers);
                        Toast.makeText(getContext(), "You have successfully registered.  ", Toast.LENGTH_SHORT).show();

                        emailET.setText(null);
                        passwordET.setText(null);
                        confPassET.setText(null);

                        break;
                    }

                    // if one of the fields are empty
                    else if (email_Input.isEmpty() || password_Input.isEmpty())
                    {
                        Toast.makeText(getContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    // if pass and confirm pass are not equal
                    else if (!password_Input.equals(confPAss_Input))
                    {
                        Toast.makeText(getContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    // if the email already exist
                    else if (existUser(email_Input))
                    {
                        Toast.makeText(getContext(), "Email is already in-use!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    i++;
                } // While-Loop
            } // onClick
        });
        return signUpView;
    }

    private boolean existUser(String email)
    {
        boolean itExists = false;

        for (int i = 0; i < Database.ArrayList.size(); i++)
        {
            AllUsers allUsers = (AllUsers) Database.ArrayList.get(i);

            if (email.equals(allUsers.getEmail()))
            {
                itExists = true;
            }
            else
            {
                itExists = false;
            }
            break;
        }
        return itExists;

//        for (AllUsers allUsers: Database.ArrayList)
//        {
//            if (email.equals(allUsers.getEmail()))
//            {
//                itExists = true;
//                break;
//            }
//        }
    }
}