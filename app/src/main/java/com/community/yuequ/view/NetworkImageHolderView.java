package com.community.yuequ.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.community.yuequ.R;
import com.community.yuequ.contorl.ImageManager;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
       View v=  View.inflate(context,R.layout.banner_item,null);
        imageView = (ImageView) v.findViewById(R.id.iv_banner);
        return v;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        ImageManager.getInstance().loadUrlImage(context,data,imageView);

//        Picasso.with(context) //
//                .load(data) //
//                .placeholder(R.mipmap.ic_launcher) //
//                .error(R.mipmap.ic_launcher) //
//                .tag(context) //
//                .into(imageView);
    }
}
