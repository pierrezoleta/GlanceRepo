package RecycleViewStory;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.snap.R;

public class StoryViewHolders extends RecyclerView.ViewHolder{
    public TextView mEmail;


    public StoryViewHolders(View itemView) {
        super (itemView);
        mEmail = itemView.findViewById(R.id.email);

    }


}
