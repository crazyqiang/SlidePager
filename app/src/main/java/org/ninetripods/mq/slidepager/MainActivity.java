package org.ninetripods.mq.slidepager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import org.ninetripods.mq.slidepager.indicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int item_grid_num = 12;//每一页中GridView中item的数量
    public static int number_columns = 4;//gridview一行展示的数目
    private ViewPager view_pager;
    private ViewPagerAdapter mAdapter;
    private List<DataBean> dataList;
    private List<GridView> gridList = new ArrayList<>();
    private CirclePageIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    private void initViews() {
        //初始化ViewPager
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new ViewPagerAdapter();
        view_pager.setAdapter(mAdapter);
        dataList = new ArrayList<>();
        //圆点指示器
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setVisibility(View.VISIBLE);
        indicator.setViewPager(view_pager);
    }

    private void initDatas() {
        if (dataList.size() > 0) {
            dataList.clear();
        }
        if (gridList.size() > 0) {
            gridList.clear();
        }
        //初始化数据
        for (int i = 0; i < 60; i++) {
            DataBean bean = new DataBean();
            bean.name = "第" + (i + 1) + "条数据";
            dataList.add(bean);
        }
        //计算viewpager一共显示几页
        int pageSize = dataList.size() % item_grid_num == 0
                ? dataList.size() / item_grid_num
                : dataList.size() / item_grid_num + 1;
        for (int i = 0; i < pageSize; i++) {
            GridView gridView = new GridView(this);
            GridViewAdapter adapter = new GridViewAdapter(dataList, i);
            gridView.setNumColumns(number_columns);
            gridView.setAdapter(adapter);
            gridList.add(gridView);
        }
        mAdapter.add(gridList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_t_t:
                //3行3列
                number_columns = 3;
                item_grid_num = 9;
                break;
            case R.id.menu_t_f:
                //3行4列
                number_columns = 4;
                item_grid_num = 12;
                break;
            case R.id.menu_f_f:
                //4行4列
                number_columns = 4;
                item_grid_num = 16;
                break;
            case R.id.menu_f_fi:
                //4行5列
                number_columns = 5;
                item_grid_num = 20;
                break;
            default:
                break;
        }
        initDatas();
        return super.onOptionsItemSelected(item);
    }
}
