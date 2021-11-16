package com.example.ex_2;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mSearchTagEdit;
    private RecyclerView mRecyclerView;
    private Handler handler = new Handler();
    private List<SearchTag> searchTagList;
    private SearchAdapter mAdapter;

    private Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            searchTags(mSearchTagEdit.getText().toString().trim());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
        setEvent();
    }

    private void initView() {
        mSearchTagEdit = (EditText) findViewById(R.id.search_tag_input_edit);
        mRecyclerView = (RecyclerView) findViewById(R.id.search_tag_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAnimation(null);
    }

    private void initAdapter() {
        searchTagList = new ArrayList<>();
        mAdapter = new SearchAdapter(searchTagList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setEvent() {
        mSearchTagEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //no-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //no-op
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //输入完成后严重8毫秒在请求
                if (delayRun != null) {
                    handler.removeCallbacks(delayRun);
                }
                handler.postDelayed(delayRun, 800);
            }
        });

    }

    /**
     * 请求数据
     * @param searchTagName
     */
    private void searchTags(String searchTagName) {
        List<SearchTag> searchTags=new ArrayList<>();
        searchTags.add(new SearchTag("测试数据1"));
        searchTags.add(new SearchTag("测试数据2"));
        searchTags.add(new SearchTag("测试数据3"));
        searchTags.add(new SearchTag("测试数据4"));
        searchTags.add(new SearchTag("测试数据5"));
        searchTags.add(new SearchTag("测试数据6"));
        searchTags.add(new SearchTag("测试数据7"));
        searchTags.add(new SearchTag("测试数据8"));
        searchTags.add(new SearchTag("测试数据9"));
        searchTags.add(new SearchTag("测试数据10"));
        searchTagList.clear();
        searchTagList.addAll(searchTags);
        mAdapter.notifyDataSetChanged();
    }
}