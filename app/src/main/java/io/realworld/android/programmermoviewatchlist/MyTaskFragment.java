package io.realworld.android.programmermoviewatchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTaskFragment extends Fragment {

    RecyclerView recyclerView;
    TaskAdapter taskAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_my_task, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Init(view);

        String[] movieNames = getResources().getStringArray(R.array.movie_names);
        String[] movieImageUrls = getResources().getStringArray(R.array.movie_image_urls);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        taskAdapter = new TaskAdapter(requireContext(), movieNames, movieImageUrls);
        recyclerView.setAdapter(taskAdapter);

    }

    private void Init(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
    }
}