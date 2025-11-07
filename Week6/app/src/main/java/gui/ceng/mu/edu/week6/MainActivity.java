package gui.ceng.mu.edu.week6;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import gui.ceng.mu.edu.week6.Movie;

public class MainActivity extends AppCompatActivity implements OnMovieSelected {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, MovieFragment.newInstance())
                    .commit();
        }
    }

    // Interface’ten gelen callback: bir film seçildiğinde ne olacak?
    @Override
    public void movieSelected(Movie movie) {
        // Burada portre/yatay duruma göre:
        // - Portre: DetailsActivity’e Intent ile movie gönder
        // - Yatay: Sağdaki container’a DetailsFragment koy/güncelle
        // Şimdilik basitçe log veya toast atabilirsin.
        // Toast.makeText(this, movie.getName(), Toast.LENGTH_SHORT).show();
    }
}
