package tools.david.com.mytools.base;



import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/7/15.
 */
public class BaseActivity extends AppCompatActivity{
    private static final int CAMERA_CODE = 10;
    private static final String Tag = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void invokeUI(Class<?> className) {
        Intent intent = new Intent();
        intent.setClass(this, className);
        this.startActivity(intent);

    }


    public void invokeUIWithResult(String action, int requireCode) {
        Intent intent = new Intent();
        intent.setAction(action);
        startActivityForResult(intent, requireCode);
    }

    public void invokeUIWithResult(Bundle param, Class<?> cls) {

        int requireCode = 0;
        Intent intent;

        if(cls != null){
            intent = new Intent(this, cls);
        }else{
            intent = new Intent();
        }
        if(isExist(param.getString("action"))){
            intent.setAction(param.getString("action"));
        }
        if(isExist(param.getInt("requireCode"))){
            requireCode = param.getInt("requireCode");
        }
        startActivityForResult(intent, requireCode);
    }

    public boolean checkPermission(String permission){
        if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                Toast.makeText(this,"please give me the permission",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,new String[]{permission},CAMERA_CODE);
            }else{
                Toast.makeText(this,"require the permission",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,new String[]{permission},CAMERA_CODE);
            }
            return false;
        }
            return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case CAMERA_CODE:
                Log.v(Tag,requestCode+"--"+grantResults[0]);
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"permission is granted",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:tools.david.com.mytools"));
                    startActivity(intent);
                }
                break;
        }
    }

    public boolean isExist(Object str){
        if(str==null || str.equals("")){
            return false;
        }else{
            return true;
        }
    }
}
