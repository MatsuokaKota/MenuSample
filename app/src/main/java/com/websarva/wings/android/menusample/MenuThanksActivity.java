package com.websarva.wings.android.menusample;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MenuThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thanks);
        //インテントオブジェクトを取得
        Intent intent = getIntent();
        //リスト画面から渡されたデータの取得
        String menuName = intent.getStringExtra("menuName");
        String menuPrice = intent.getStringExtra("menuPrice");
        //定食名と金額を表示させるTextViewを取得
        TextView tvMenuName = findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);
        //アクションバーを取得
        ActionBar actionBar = getSupportActionBar();
        //アクションバーの「戻る」メニューを有効に設定
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //戻り値用の変数を初期値trueで用意
        boolean returnVal = true;
        //選択されたメニューのIDを取得
        int itemId = item.getItemId();
        //選択されたメニューが「戻る」の場合，アクティビティーを終了
        if(itemId == android.R.id.home){
            finish();
        }
        //それ以外
        else{
            //親クラスの同名メソッドを呼び出し，その戻り値をreturnValとする
            returnVal = super.onOptionsItemSelected(item);
        }
        return returnVal;
    }

    //戻るボタンをタップしたときの処理
    public void onBackButtonClick(View view) {
        finish();
    }
}