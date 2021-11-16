package com.example.ex_2;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;




public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_TOP = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_END = 2;
    private static final int VIEW_TYPE_EMPTY = 3;

    private List<SearchTag> searchTagList;
    private Context context;

    public SearchAdapter(@NonNull List<SearchTag> Tags,
                         @NonNull Context context) {
        this.context = context;
        this.searchTagList = Tags;
        Log.e("11",searchTagList.toString());
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("11",searchTagList.toString());
        if (searchTagList.isEmpty()) {
            if (position == 0) {
                return VIEW_TYPE_EMPTY;
            }
            return VIEW_TYPE_END;
        }
        if (position == 0) {
            return VIEW_TYPE_TOP;
        } else if (position == getItemCount() - 1) {
            return VIEW_TYPE_END;
        }
        return VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_TOP: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_goods_tag_top, parent, false);
                return new GoodsTagTopHolder(view);
            }
            case VIEW_TYPE_ITEM: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_goods_tag_item, parent, false);
                return new GoodsTagItemHolder(view);
            }
            case VIEW_TYPE_END: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_goods_tag_end, parent, false);
                GoodsTagEndHolder holder = new GoodsTagEndHolder(view);
                holder.setEvent();
                return holder;
            }
            default: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_goods_tag_empty, parent, false);
                return new EmptyViewHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GoodsTagItemHolder) {
            SearchTag searchTag = searchTagList.get(position - 1);
            GoodsTagItemHolder goodsTagItemHolder = (GoodsTagItemHolder) holder;
            goodsTagItemHolder.setData(searchTag);
            goodsTagItemHolder.setEvent(searchTag);
        }
    }

    @Override
    public int getItemCount() {
        if (searchTagList.isEmpty()) {
            return 2;
        }
        return searchTagList.size() + 2;
    }

    private class GoodsTagTopHolder extends RecyclerView.ViewHolder {
        GoodsTagTopHolder(View view) {
            super(view);
        }
    }

    private class GoodsTagItemHolder extends RecyclerView.ViewHolder {
        private TextView mGoodsTagItemTv;
        private RelativeLayout mGoodsTagItemLayout;

        GoodsTagItemHolder(View view) {
            super(view);
            mGoodsTagItemLayout = (RelativeLayout) view.findViewById(R.id.goods_tag_item_layout);
            mGoodsTagItemTv = (TextView) view.findViewById(R.id.goods_tag_item_tv);
        }

        public void setData(SearchTag searchTag) {
            String label = searchTag.getName();
            mGoodsTagItemTv.setText(label);
        }

        public void setEvent(SearchTag searchTag) {

        }
    }

    private class GoodsTagEndHolder extends RecyclerView.ViewHolder {
        private TextView mGoodsTagLinkTv;

        GoodsTagEndHolder(View view) {
            super(view);
            mGoodsTagLinkTv = (TextView) view.findViewById(R.id.goods_tag_link_tv);
        }

        public void setEvent() {
            mGoodsTagLinkTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        EmptyViewHolder(View itemView) {
            super(itemView);
            TextView textView = (TextView) itemView.findViewById(R.id.empty_text);
        }
    }
}
