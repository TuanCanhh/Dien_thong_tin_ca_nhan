package com.example.bai6_formthongtin;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtHoten, edtCCCD, edtbosung;
    Button btngui;
    CheckBox chkdocbao, chkdocsach, chkcoding;
    RadioGroup idgroup;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các thành phần từ layout
        edtHoten = findViewById(R.id.edtHoten);
        edtCCCD = findViewById(R.id.edtCCCD);
        edtbosung = findViewById(R.id.edtbosung);
        btngui = findViewById(R.id.btngui);
        idgroup = findViewById(R.id.idgroup);
        chkcoding = findViewById(R.id.chkcoding);
        chkdocbao = findViewById(R.id.chkdocbao);
        chkdocsach = findViewById(R.id.chkdocsach);

        // Xử lý sự kiện khi nhấn nút gửi
        btngui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra họ tên
                String hoten = edtHoten.getText().toString();
                if (hoten.length() < 5) {
                    Toast.makeText(MainActivity.this, "Họ tên phải ít nhất 5 ký tự", Toast.LENGTH_LONG).show();
                    edtHoten.requestFocus();
                    return;
                }

                // Kiểm tra CCCD
                String CCCD = edtCCCD.getText().toString();
                if (CCCD.length() != 9) {  // phải có 9 số
                    Toast.makeText(MainActivity.this, "CCCD phải có 9 số", Toast.LENGTH_LONG).show();
                    edtCCCD.requestFocus();
                    return;
                }

                // Lấy thông tin bằng cấp từ RadioGroup
                int idselect = idgroup.getCheckedRadioButtonId();
                String bangCap = "";
                if (idselect != -1) {
                    RadioButton radioButton = findViewById(idselect);
                    bangCap = radioButton.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "Bạn chưa chọn bằng cấp", Toast.LENGTH_LONG).show();
                    return;
                }

                // Lấy thông tin sở thích từ các CheckBox
                StringBuilder bosung = new StringBuilder("Sở thích: ");
                if (chkdocbao.isChecked()) bosung.append("Đọc báo, ");
                if (chkdocsach.isChecked()) bosung.append("Đọc sách, ");
                if (chkcoding.isChecked()) bosung.append("Coding, ");

                if (bosung.length() > 11) {
                    bosung.setLength(bosung.length() - 2); // Xóa dấu phẩy cuối cùng
                } else {
                    bosung.append("Không có");
                }

                // Lấy thông tin bổ sung từ EditText
                String thongTinBoSung = edtbosung.getText().toString();
                if (thongTinBoSung.isEmpty()) {
                    thongTinBoSung = "Không có thông tin bổ sung.";
                }

                // Hiển thị thông tin trong AlertDialog
                AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
                mydialog.setTitle("Thông tin cá nhân");
                mydialog.setMessage("Họ tên: " + hoten + "\n" +
                        "CCCD: " + CCCD + "\n" +
                        "Bằng cấp: " + bangCap + "\n" +
                        bosung.toString() + "\n" +
                        "Thông tin bổ sung: " + thongTinBoSung);

                mydialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Thông tin đã được ghi nhận", Toast.LENGTH_SHORT).show();
                    }
                });
                mydialog.create().show();
            }
        });
    }
}
