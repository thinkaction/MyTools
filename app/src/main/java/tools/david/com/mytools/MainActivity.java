package tools.david.com.mytools;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tools.david.com.mytools.activities.MyCamera;
import tools.david.com.mytools.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
       Button camera = (Button) findViewById(R.id.btn_camera);
        camera.setOnClickListener(new MyClick());
    }

    private class MyClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_camera:
                    invokeUI(MyCamera.class);
                    break;
            }
        }
    }

}
