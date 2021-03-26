package mobile.computing.myloginapp.practice102;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetPasswordFragment extends Fragment {

    TextView emailTV, newPassTV, confPassTV;
    EditText emailET, newPassET, confPassET;
    Button confirmBT, submitBT;
    int emails_Count = 0, pass_Count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reset_password, container, false);

        emailTV = (TextView) v.findViewById(R.id.emailTextViewResetFrag);
        newPassTV = (TextView) v.findViewById(R.id.newPassTextViewResetFrag);
        confPassTV = (TextView) v.findViewById(R.id.confPassTextViewResetFrag);

        emailET = (EditText) v.findViewById(R.id.emailEditTextResetFrag);
        newPassET = (EditText) v.findViewById(R.id.newPassEditTextResetFrag);
        confPassET = (EditText) v.findViewById(R.id.confPassEditTextResetFrag);

        confirmBT = (Button) v.findViewById(R.id.confirmButtonResetFrag);
        submitBT = (Button) v.findViewById(R.id.submitButtonResetFrag);

        newPassET.setEnabled(false);
        confPassET.setEnabled(false);
        confirmBT.setEnabled(false);

        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailET.getText().toString();

                while (emails_Count <= Database.ArrayList.size())
                {

                    // it will checks if the ArrayList isEmpty, because without this function, if the user clicks on submit (app will crash).
                    if (Database.ArrayList.isEmpty())
                    {
                        Toast.makeText(getContext(), "Enter an email, or Register!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    AllUsers users = Database.ArrayList.get(emails_Count);

                    // if the email entered is found in the ArrayList, all the below will start its own function.
                    if (email.equals(users.getEmail()))
                    {
                        newPassET.setEnabled(true);
                        confPassET.setEnabled(true);
                        confirmBT.setEnabled(true);
                        submitBT.setEnabled(false);
                        emailET.setEnabled(false);

                        // The password will be = null, for the email found that the user entered.
                        users.setPassword(null);

                        Toast.makeText(getContext(), "Enter a new password.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else if (!email.equals(users.getEmail()))
                    {
                        Toast.makeText(getContext(), "Email not found!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    emails_Count++;
                } // while-loop
            }
        });

        confirmBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // pass_Count will be equal to the emails_Count, so that we can change the password for the right email.
                pass_Count = emails_Count;

                String newPassword = newPassET.getText().toString();
                String confPassword = confPassET.getText().toString();

                AllUsers passwords = Database.ArrayList.get(pass_Count);
                


                // If the user entered the same recent password he will get a message, and his password will be reset to null.
//                if (newPassword.equals(passwords.getPassword()))
//                {
//                    Toast.makeText(getContext(), "You can't enter the same password", Toast.LENGTH_SHORT).show();
//                    passwords.setPassword(null);
//                }

                // newPassword = confPassword, and the new password is not equal to the previous one, and both field are not empty => new password will be setted and Sign In Fragment will reopen again.
                if (newPassword.equals(confPassword) && !newPassword.equals(passwords.getPassword()) && !newPassword.trim().isEmpty() && !confPassword.trim().isEmpty())
                {
                    passwords.setPassword(newPassword);
                    Fragment signIn_Fragment = new SignInFragment();
                    FragmentManager fragment_Manager = getFragmentManager();
                    fragment_Manager.beginTransaction().replace(R.id.mainFrameLayout, signIn_Fragment, signIn_Fragment.getTag()).commit();
                }

                // if the newPassword != to confPassword
                else if (!newPassword.equals(confPassword))
                {
                    Toast.makeText(getContext(), "All field must be correct!", Toast.LENGTH_SHORT).show();
                }

                // this function already checked, but here to display a toast message
                else if (newPassword.trim().isEmpty() && confPassword.trim().isEmpty())
                {
                    Toast.makeText(getContext(), "All Fields must be valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}