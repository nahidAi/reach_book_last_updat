package money.system.book.maker.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import money.system.book.maker.Activity.Main2Activity;
import money.system.book.maker.Quote;
import money.system.book.maker.R;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.myViewHolder> {
    public Context context;
    private  final List<Quote>favorite;


    public  AdapterFavorite(Context context,List<Quote>favorite) {
        this.context = context;
        this.favorite = favorite;

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        final  Quote q = favorite.get(position);
        holder.personName.setText(q.getName());
        String imgAddress = q.getImage();
        int id = context.getResources().getIdentifier(imgAddress,"drawable",context.getPackageName());
        holder.avatar.setImageResource(id);
        holder.layoutItem.setId(position);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = q.getId();
                Intent intent = new Intent(context, Main2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name","BigPerson");
                intent.putExtra("id",position+"");
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return favorite.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView personName;
        LinearLayout layoutItem;


        public myViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView)itemView.findViewById(R.id.avatar);
            personName = (TextView)itemView.findViewById(R.id.PersonName);
            layoutItem = (LinearLayout)itemView.findViewById(R.id.layout_item);

        }
    }


}
