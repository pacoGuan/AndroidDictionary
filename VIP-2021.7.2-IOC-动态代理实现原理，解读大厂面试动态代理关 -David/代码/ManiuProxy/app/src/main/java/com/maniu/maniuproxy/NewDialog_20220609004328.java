package com.maniu.maniuproxy;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.maniu.maniuproxy.annotion.ContentView;
import com.maniu.maniuproxy.annotion.OnClick;
import com.maniu.maniuproxy.annotion.ViewInject;

@ContentView(R.layout.dialog_news)
public class NewDialog extends  BaseDialog {
    @ViewInject(R.id.dialogBtn)
    Button dialogBtn;
    public NewDialog(@NonNull Context context) {
        super(context);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Toast.makeText(getContext(), "dialogBtn "+dialogBtn, Toast.LENGTH_SHORT).show();
//    }
    @OnClick(R.id.dialogBtn)
public void click(View view) {

    Toast.makeText(getContext(), "  dialog点击啦", Toast.LENGTH_SHORT).show();
}
}
