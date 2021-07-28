package io.realworld.android.programmermoviewatchlist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static android.content.Context.MODE_PRIVATE;

public class FirstFragment extends Fragment {

    SharedPreferences sharedPreferences;
    TextView username;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Init(view);
        sharedPreferences = getActivity().getSharedPreferences(TaskConstants.SHARED_PREF_NAME, MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString(TaskConstants.KEY_USERNAME, null);

        username.setText(getResources().getString(R.string.welcome_user, savedUsername));
        view.findViewById(R.id.button_first).setOnClickListener(view1 ->
                NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));
    }

    private void Init(View view) {
        username = view.findViewById(R.id.username_first);
    }
}