package gui.ceng.mu.edu.week6;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MovieFragment extends Fragment {

    private OnMovieSelected listener;

    public MovieFragment() {

    }

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnMovieSelected) {
            listener = (OnMovieSelected) context;
        } else {
            throw new ClassCastException(
                    context.toString() + " must implement OnMovieSelected");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }



    private void notifyMovieSelected(@NonNull gui.ceng.mu.edu.week6.Movie movie) {
        if (listener != null) {
            listener.movieSelected(movie);
        }
    }

}
