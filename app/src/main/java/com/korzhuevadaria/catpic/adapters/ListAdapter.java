package com.korzhuevadaria.catpic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.korzhuevadaria.catpic.R;
import com.korzhuevadaria.catpic.models.PhotoItem;
import com.korzhuevadaria.catpic.utils.Utils;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final int EMPTY_VIEW = 77777;
    private LayoutInflater inflater;
    private List<PhotoItem> dataModelArrayList = new ArrayList<>();

    public ListAdapter(Context ctx, List<PhotoItem> dataModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == EMPTY_VIEW) {
            return new EmptyViewHolder(layoutInflater.inflate(R.layout.empty_list, parent, false));
        } else {
            return new ItemViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == EMPTY_VIEW) {
            EmptyViewHolder emptyView = (EmptyViewHolder) holder;
            emptyView.emptyListText.setText("No meowcontent!");
        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Picasso.get().load(dataModelArrayList.get(position).getPhotoSizes().get(0).getUrl()).into(itemViewHolder.avatar);
            itemViewHolder.date.setText(Utils.convertUnixTimeToString(dataModelArrayList.get(position).getDate()));
        }
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size() > 0 ? dataModelArrayList.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataModelArrayList.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        ImageView avatar;

        public ItemViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.list_date);
            avatar = (ImageView) itemView.findViewById(R.id.list_avatar);
        }

    }

    class EmptyViewHolder extends RecyclerView.ViewHolder{
        TextView emptyListText;

        public EmptyViewHolder(View itemView) {
            super(itemView);

            emptyListText = (TextView) itemView.findViewById(R.id.empty_list_text);
        }
    }
}