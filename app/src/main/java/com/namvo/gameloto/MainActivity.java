package com.namvo.gameloto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText mEdtSoBatDau, mEdtSoKetThuc;
    Button mBtnThem, mBtnTaoLai, mBtnRandom;
    TextView mTxtKetQua;

    String mStringSoBatDau, mStringSoKetThuc, mStringKetQua = "";
    Integer mSoBatDau,mSoKetThuc;
    ArrayList<Integer> mDanhSachSo = new ArrayList<>();
    Random mNgauNhien = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtSoBatDau = findViewById(R.id.edtSoBatDau);
        mEdtSoKetThuc = findViewById(R.id.edtSoKetThuc);
        mBtnThem = findViewById(R.id.btnThem);
        mBtnTaoLai = findViewById(R.id.btnTaoLai);
        mBtnRandom =  findViewById(R.id.btnRandom);
        mTxtKetQua = findViewById(R.id.txtKetQua);

        mBtnRandom.setEnabled(false);
        mBtnTaoLai.setEnabled(false);
        if(mStringKetQua=="")
            mTxtKetQua.setText("");

        mBtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStringSoBatDau = mEdtSoBatDau.getText().toString();
                mStringSoKetThuc = mEdtSoKetThuc.getText().toString();

                if(mStringSoBatDau.isEmpty() && mStringSoKetThuc.isEmpty()){
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                mSoBatDau =Integer.parseInt(mStringSoBatDau);
                mSoKetThuc = Integer.parseInt(mStringSoKetThuc);
                mBtnRandom.setEnabled(true);
                mBtnTaoLai.setEnabled(true);
                mBtnThem.setEnabled(false);
                mEdtSoBatDau.setEnabled(false);
                mEdtSoKetThuc.setEnabled(false);

                if( mSoBatDau >= mSoKetThuc ){
                    mSoKetThuc = mSoBatDau + 1;
                    mEdtSoKetThuc.setText(mSoKetThuc + "");
                }
                for( int i = mSoBatDau; i <= mSoKetThuc; i++){
                    mDanhSachSo.add(i);
                }
            }
        });

        mBtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDanhSachSo.size()==0){
                    Toast.makeText(MainActivity.this, "Trò chơi đã kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }

                int index = mNgauNhien.nextInt(mDanhSachSo.size());
                if(mDanhSachSo.size()==1){
                    mStringKetQua += mDanhSachSo.get(index);
                }else {
                    mStringKetQua +=  mDanhSachSo.get(index)+ " - ";
                }

                mDanhSachSo.remove(index);
                mTxtKetQua.setText(mStringKetQua);
            }
        });

        mBtnTaoLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnRandom.setEnabled(false);
                mBtnTaoLai.setEnabled(true);
                mBtnThem.setEnabled(true);
                mEdtSoBatDau.setEnabled(true);
                mEdtSoKetThuc.setEnabled(true);
                mTxtKetQua.setText("");
                mStringKetQua="";
                mDanhSachSo.clear();
            }
        });
    }
}
