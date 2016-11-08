//package com.zlw.mymodel.adapter;
//
//import java.util.List;
//
//import com.threegroup.vchuang.R;
//import com.zlw.mymodel.service.Item;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
///**
// * RecyclerView数据适配器
// * Created by zlw on 2015/7/2.
// * 外接方法：
// * 1.获取数据：getDataList()
// * 2.重置数据：setDataList()
// */
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
//
//    private Context mContext;
//    private List<Item> dataList; //数据信息
//
//
//    //============数据操作=============
//
//    /**
//     * 获取数据
//     */
//    public List<Item> getDataList() {
//        return dataList;
//    }
//
//    /**
//     * 设置数据
//     */
//    public void setDataList(List<Item> lists) {
//        dataList = lists;
//    }
//
//
//    public RecyclerViewAdapter(Context context, List<Item> dataList) {
//        this.dataList = dataList;//初始数据
//        mContext = context;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView title;
//        private TextView content;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            title = (TextView) itemView.findViewById(R.id.title);
//            content = (TextView) itemView.findViewById(R.id.item_content);
//        }
//    }
//
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tab1_recycler_view_, parent, false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        //加载数据
//        holder.title.setText(dataList.get(position).getName());
//        holder.content.setText(dataList.get(position).getContent());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//}