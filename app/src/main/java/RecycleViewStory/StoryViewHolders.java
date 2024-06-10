package RecycleViewStory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.snap.DisplayImageActivity;
import com.example.snap.R;

public class StoryViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mEmail;
    public LinearLayout mLayout;

    public StoryViewHolders(View itemView) {
        super (itemView);
        itemView.setOnClickListener(this);
        mEmail = itemView.findViewById(R.id.email);
        mLayout = itemView.findViewById(R.id.layout);

    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DisplayImageActivity.class);
        Bundle b = new Bundle();
        b.putString("userId",mEmail.getTag().toString());
        b.putString("chatOrStory",mLayout   .getTag().toString());
        intent.putExtras(b);
        view.getContext().startActivity(intent);
    }
}
