package com.example.neibucunchu;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//在内部存储中读写文件
public class MainActivity extends Activity {

    private EditText et_name;
    private EditText et_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = (EditText) findViewById(R.id.et_name);
        et_pass = (EditText) findViewById(R.id.et_pass);
        //打开应用便调用读取方法
        readAccount();

    }

    //打开应用，读取文件，将内容显示在界面上
    public void readAccount(){
        File file = new File("data/data/indi.cc.rwinrom/info.txt");
        //判断文件是否存在
        if(file.exists()){
            FileInputStream fis = null;
            try {
                fis = new FileInputStream( file);
                //把字节流转换成字符流
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                //读取txt文件里的用户名和密码
                String text = br.readLine();
                String[] s = text.split("##");

                et_name.setText(s[0]);
                et_pass.setText(s[1]);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }


        }
    }


    //点击登录将用户名和密码存入文件
    public void login(View v){
        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();

        CheckBox cb = (CheckBox) findViewById(R.id.cb);

        FileOutputStream fos = null ;

        //判断选框是否被勾选
        if(cb.isChecked()){
            //data/data/indi.cc.rwinrom:这就是内部存储空间的路径
            File file = new File("data/data/indi.cc.rwinrom/info.txt");
            try {
                fos = new FileOutputStream(file);
                fos.write((name + "##" + pass).getBytes());
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                if(fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }
        //创建并显示吐司对话框
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }


}

