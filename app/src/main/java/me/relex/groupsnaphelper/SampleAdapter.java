package me.relex.groupsnaphelper;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int GROUP_COUNT = 3;

    public static final int ITEM_NORMAL = 0;
    public static final int ITEM_PLACEHOLDER = 1;

    private List<String> mList = new ArrayList<>();

    @Override public int getItemViewType(int position) {
        int size = mList.size();
        if (position < size) {
            return ITEM_NORMAL;
        }
        return ITEM_PLACEHOLDER;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_NORMAL: {
                View view = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1,
                        null);
                int width = parent.getMeasuredWidth() / GROUP_COUNT;
                int height = parent.getMeasuredHeight();
                view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
                return new SampleViewHolder(view);
            }

            default: {
                View view = new View(parent.getContext());
                int width = parent.getMeasuredWidth() / GROUP_COUNT;
                int height = parent.getMeasuredHeight();
                view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
                return new PlaceholderViewHolder(view);
            }
        }
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_NORMAL) {
            ((SampleViewHolder) holder).bindView(mList.get(position), position);
        }
    }

    @Override public int getItemCount() {
        int size = mList.size();
        return (int) (Math.ceil((double) size / GROUP_COUNT) * GROUP_COUNT);
    }

    public void setItems(int count) {
        mList.clear();
        for (int i = 0; i < count; i++) {
            mList.add(String.valueOf(i + 1));
        }
        notifyDataSetChanged();
    }

    public static class SampleViewHolder extends RecyclerView.ViewHolder {

        private int[] mColorSchemeA = new int[] { 0xFFAA00A2, 0xFF7F207B, 0xFFD435CD, 0xFFD460CF };
        private int[] mColorSchemeB = new int[] { 0xFF1DD300, 0xFF138900, 0xFF52E93A, 0xFF7AE969 };

        public SampleViewHolder(View itemView) {
            super(itemView);
            ((TextView) itemView).setGravity(Gravity.CENTER);
            ((TextView) itemView).setTextColor(Color.WHITE);
        }

        public void bindView(String data, int position) {
            ((TextView) itemView).setText(data);

            int mod = position % (GROUP_COUNT * 2);

            if (mod < GROUP_COUNT) {
                itemView.setBackgroundColor(mColorSchemeA[mod % mColorSchemeA.length]);
            } else {
                itemView.setBackgroundColor(
                        mColorSchemeB[(mod - GROUP_COUNT) % mColorSchemeB.length]);
            }
        }
    }

    public static class PlaceholderViewHolder extends RecyclerView.ViewHolder {
        public PlaceholderViewHolder(View itemView) {
            super(itemView);
        }
    }
}




