package com.maniu.maniuproxy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maniu.maniuproxy.annotion.ContentView;
import com.maniu.maniuproxy.annotion.OnClick;
import com.maniu.maniuproxy.annotion.OnLongClick;
import com.maniu.maniuproxy.annotion.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    //     @Vie?wInject  ioc 用到了动态代理
//动态代理
    @OnLongClick(value = {R.id.btn,R.id.btn2})
    public boolean click(View view) {
        NewDialog newsDialog = new NewDialog(this);
        newsDialog.show();
        return false;
    }
}
