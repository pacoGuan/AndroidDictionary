package com.maniu.maniuproxy;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class BaseDialog   extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        IOC注入
        InjetUtils.inject(this);
    }

}
