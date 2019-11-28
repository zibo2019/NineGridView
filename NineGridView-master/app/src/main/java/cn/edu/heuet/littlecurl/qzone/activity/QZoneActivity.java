package cn.edu.heuet.littlecurl.qzone.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.heuet.littlecurl.R;
import cn.edu.heuet.littlecurl.qzone.adapter.RecyclerVidewAdapter;
import cn.edu.heuet.littlecurl.qzone.bean.Location;
import cn.edu.heuet.littlecurl.qzone.bean.MyMedia;
import cn.edu.heuet.littlecurl.qzone.bean.RecyclerViewItem;

public class QZoneActivity extends AppCompatActivity {

    // Log打印的通用Tag
    private final String TAG = "QZoneActivity:";

    // 下拉刷新控件
    @BindView(R.id.spl)
    SwipeRefreshLayout swipeRefreshLayout;

    // 数据展示
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public RecyclerVidewAdapter recyclerViewAdapter;
    private ArrayList<RecyclerViewItem> recyclerViewItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qzone);
        ButterKnife.bind(this);

        // 自定义数据
        loadMyTestDate();

        // 布局管理器必须有，否则不显示布局
        // No layout manager attached; skipping layout
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        // RecyclerView适配器
        recyclerViewAdapter = new RecyclerVidewAdapter(this, recyclerViewItemList);
        recyclerView.setAdapter(recyclerViewAdapter);


        // 下拉刷新控件设置参数
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 加载数据（先清空原来的数据）
                recyclerViewItemList.clear();
                // loadBackendData(url);
                loadMyTestDate();
                // 打乱顺序（为了确认确实是刷新了）
                Collections.shuffle(recyclerViewItemList);
                // 通知适配器数据已经改变
                recyclerViewAdapter.notifyDataSetChanged();
                // 下拉刷新完成
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    // 自定义的测试数据（假装这是网络请求并解析后的数据）
    private void loadMyTestDate() {
        // 先构造MyMedia
        String imgUrl1 = "http://i2.tiimg.com/702441/6e3d61b352409f34.png";
        String imgUrl2 = "http://i2.tiimg.com/702441/ca8092e87a2f2b30.jpg";
        String imgUrl3 = "http://i2.tiimg.com/702441/081b443af609c94c.png";
        // 视频内容：敲架子鼓
        String videoUrl1 = "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4";
        // 视频内容：感受到鸭力
        String videoUrl2 = "http://gslb.miaopai.com/stream/w95S1LIlrb4Hi4zGbAtC4TYx0ta4BVKr-PXjuw__.mp4?vend=miaopai&ssig=8f20ca2d86ec365f0f777b769184f8aa&time_stamp=1574944581588&mpflag=32&unique_id=1574940981591448";
        // 视频内容：美洲狮幼崽
        String videoUrl3 = "http://sjycdn.miaopai.com/stream/kCM1yjM7W-50LLA9jvEzoaj28stOGtWtmDdEIQ__.mp4?ssig=07f39662efb375bc4ff32681c04cd195&time_stamp=1574944758729";
        // 视频内容：狗崽子
        String videoUrl4 = "http://gslb.miaopai.com/stream/7-5Q7kCzeec9tu~9XvZAxNizNAL1TJC7KtJCuw__.mp4?vend=miaopai&ssig=82b42debfc2a51569bafe6ac7a993d89&time_stamp=1574944868488&mpflag=32&unique_id=1574940981591448";

        MyMedia myMedia1 = new MyMedia(imgUrl1, videoUrl1);
        MyMedia myMedia2 = new MyMedia(imgUrl2);
        MyMedia myMedia3 = new MyMedia(imgUrl3, videoUrl2);
        MyMedia myMedia4 = new MyMedia(imgUrl1, videoUrl3);
        MyMedia myMedia5 = new MyMedia(imgUrl3, videoUrl4);
        // 再构造mediaList
        ArrayList<MyMedia> mediaList = new ArrayList<>();
        for (int i = 0; i < 2; i++) { // 加入6张图片
            mediaList.add(myMedia1);
            mediaList.add(myMedia2);
            mediaList.add(myMedia3);
        }
        for (int i = 0; i < 2; i++) { // 加入6张图片
            mediaList.add(myMedia4);
            mediaList.add(myMedia2);
            mediaList.add(myMedia5);
        }
        Location location = new Location();
        location.setAddress("Test Address");
        // 最后构造EvaluationItem
        final RecyclerViewItem recyclerViewItem1 = new RecyclerViewItem(mediaList, "河北经贸大学自强社是在校学生处指导、学生资助管理中心主办下，于2008年4月15日注册成立的，一个以在校学生为主体的学生公益社团。历经十年的发展，在学生处、学生资助管理中心的大力支持下，在每一届自强人的团结努力下，自强社已经由成... ", "2019-11-02",
                "10080", "自强社", location, imgUrl1);
        final RecyclerViewItem recyclerViewItem2 = new RecyclerViewItem(mediaList, "河北经贸大学信息技术学院成立于1996年，由原计算机系/经济信息系合并组建而成，是我校建设的第一批学院。", "2019-11-02",
                "10080", "信息技术学院", location, imgUrl2);
        final RecyclerViewItem recyclerViewItem3 = new RecyclerViewItem(mediaList, "河北经贸大学雷雨话剧社是河北经贸大学唯一以话剧为主，兼小品，相声等多种表演艺术形式，由一批热爱表演，热爱话剧，热爱中国传统艺术与当代流行艺术结合的同学共同组成的文艺类大型社团。雷雨话剧社坚持以追求话剧“更新颖”、“更大型”、“更专业”为奋斗目标，坚持在继承传统文化和前辈的演出经验... ", "2019-11-02",
                "10080", "雷雨话剧社", location, imgUrl3);
        recyclerViewItemList.add(recyclerViewItem1);
        recyclerViewItemList.add(recyclerViewItem2);
        recyclerViewItemList.add(recyclerViewItem3);
    }
}
