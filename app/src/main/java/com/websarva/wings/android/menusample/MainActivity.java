package com.websarva.wings.android.menusample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //リストビューを表すフィールド
    private ListView _lvMenu;
    //リストビューに表示するリストデータ
    private List<Map<String, Object>> _menuList;
    //SimpleAdapterの第４引数fromに使用する定数フィールド
    //配列になっている場合は，[]をつける
    private static final String[] FROM = {"name", "price"};
    //SimpleAdapterの第５引数toに使用する定数フィールド
    private static final int[] TO = {R.id.tvMenuNameRow, R.id.tvMenuPriceRow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //画面部品ListVieを取得し，フィールドに格納
        _lvMenu = findViewById(R.id.lvMenu);
        //定食メニューListオブジェクトをprivateメソッドを利用して用意し，フィールドに格納
        _menuList = createTeishokuList();
        //SimpleAdapterを生成
        //第3引数に各行のレイアウトを決めるxmlを使っている
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.row, FROM, TO);
        //アダプタの登録
        _lvMenu.setAdapter(adapter);
        //リストタップのリスナクラスの登録
        _lvMenu.setOnItemClickListener(new ListItemClickListener());
    }

    private List<Map<String, Object>> createTeishokuList(){
        //定食メニューリスト用のListオブジェクトを用意
        List<Map<String, Object>> menuList = new ArrayList<>();
        //から揚げ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録．
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "から揚げ定食");
        menu.put("price", 850);
        menu.put("desc", "若鶏のから揚げにサラダ,ご飯とお味噌汁がつきます．");
        menuList.add(menu);
        //ハンバーグ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録．
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ，ご飯とお味噌汁がつきます.");
        menuList.add(menu);
        return menuList;
    }

    private List<Map<String, Object>> createCurryList(){
        //カレーメニューリスト用のListオブジェクトを用意
        List<Map<String, Object>> menuList = new ArrayList<>();
        //ビーフカレーのデータを格納するMapオブジェクトの用意とmenuListへのデータ登録．
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "チキンカレー");
        menu.put("price", 520);
        menu.put("desc", "特製スパイスをきかせた国産ビーフ100%のカレーです．");
        menuList.add(menu);
        //ポークカレーのデータを格納するMapオブジェクトの用意とmenuListへのデータ登録．
        menu = new HashMap<>();
        menu.put("name", "ポークカレー");
        menu.put("price", 420);
        menu.put("desc", "特製スパイスをきかせた国産ポーク100%のカレーです．");
        menuList.add(menu);
        return menuList;
    }

    //リストがタップされたときの処理が記述されたメンバクラス
    private class ListItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            //タップされた行のデータを取得．SimpleAdapterでは1行分のデータはMap型!
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
            //定食名と金額を取得
            String menuName = (String) item.get("name");
            Integer menuPrice = (Integer) item.get("price");
            //インテントオブジェクトを生成
            Intent intent = new Intent(MainActivity.this, MenuThanksActivity.class);
            //第2画面に送るデータを格納
            intent.putExtra("menuName", menuName);
            //MenuThanksActivityでのデータの受け取りと合わせるために，金額にここで「円」を追加する．
            intent.putExtra("menuPrice", menuPrice + "円");
            //第２画面の起動
            startActivity(intent);
        }
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        //メニューインフレーターの取得
        MenuInflater inflater = getMenuInflater();
        //オプションメニュー用.xmlファイルをインフレート(javaオブジェクトにする)
        inflater.inflate(R.menu.menu_options_menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //戻り値用の変数を初期値trueで用意
        boolean returnVal = true;
        //選択されたメニューのIDを取得
        int itemId = item.getItemId();
        //IDのR値による処理の分岐
        switch(itemId) {
            //定食メニューが選択された場合の処理
            case R.id.menuListOptionTeishoku:
                //定食メニューのリストデータの生成
                _menuList = createTeishokuList();
                break;
            //カレーメニューが選択された場合の処理
            case R.id.menuListOptionCurry:
                //カレーメニューリストデータの生成
                _menuList = createCurryList();
                break;
            //それ以外
            default:
                //親クラスの同名メソッドを呼び出し，その戻り値をreturnValする
                returnVal = super.onOptionsItemSelected(item);
                break;
        }
        //SimpleAdapterを選択されたメニューデータで生成
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, _menuList, R.layout.row, FROM, TO);
        //アダプタの登録
        _lvMenu.setAdapter(adapter);
        return returnVal;
    }
}