package tools.david.com.mytools.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import tools.david.com.mytools.R;
import tools.david.com.mytools.base.BaseActivity;

/**
 * Created by hl on 2016/7/15.
 */
public class MyCamera extends BaseActivity {
    private static final int IMG_CAPTURE = 100;
    private static final String Tag = "MyCamera";
    private static final int CAMERA_CODE = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycamera);
        initView();
    }

    private void initView() {

        Button btn_open = (Button) findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new MyClick());
    }


    private class MyClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_open:
                    if(checkPermission(Manifest.permission.CAMERA)){
                        Bundle bundle = new Bundle();
                       // bundle.putString(MediaStore.EXTRA_OUTPUT, "");   //存储路径
                        bundle.putString("action",MediaStore.ACTION_IMAGE_CAPTURE);
                        bundle.putInt("reqCode",IMG_CAPTURE);
                        invokeUIWithResult(bundle,null);
                    }
                    break;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(Tag,"resultCode"+resultCode);
        switch(requestCode){
            case IMG_CAPTURE:

                break;
        }
}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.v(Tag,requestCode+"--"+permissions[0]+grantResults[0]);
        switch(requestCode){
            case CAMERA_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Bundle bundle = new Bundle();
                    bundle.putString("action",MediaStore.ACTION_IMAGE_CAPTURE);
                    bundle.putInt("reqCode",IMG_CAPTURE);
                    invokeUIWithResult(bundle,null);
                }
                break;
        }
    }
}
