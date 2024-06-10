package RecyclerViewReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.snap.R;

public class ReceiverViewHolder extends RecyclerView.ViewHolder{
    public TextView mEmail;
    public CheckBox mReceive;

    public ReceiverViewHolder(View itemView) {
        super (itemView);
        mEmail = itemView.findViewById(R.id.email);
        mReceive = itemView.findViewById(R.id.receive);

    }


}
