package RecyclerViewReceiver;
//nikoru
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.snap.R;
import com.example.snap.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ReceiverAdapter extends RecyclerView.Adapter<ReceiverViewHolder> {

    private List<ReceiverObject> usersList;
    private Context context;


    public ReceiverAdapter(List<ReceiverObject> usersList, Context context){
        this.usersList = usersList;
        this.context = context;
    }
    @Override
    public ReceiverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_receiver_item, null);
        ReceiverViewHolder rcv = new ReceiverViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ReceiverViewHolder holder, int position) {
        holder.mEmail.setText(usersList.get(position).getEmail());
        holder.mReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean receiveState = !usersList.get(holder.getLayoutPosition()).getReceive();
                usersList.get(holder.getLayoutPosition()).setReceive(receiveState);
            }
        });



    }

    @Override
    public int getItemCount() {
        return this.usersList.size();
    }
}
