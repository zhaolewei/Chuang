package com.wzy;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.threegroup.vchuang.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateProjectActivity extends Activity implements OnClickListener {
	
	private MySQLite mysqlite;
	
	private Button set;
	
	private EditText editname;
	private EditText editurl;
	private EditText editintroduction;
	private EditText editstage;
	private EditText editarea;
	private EditText editcity;
	private EditText editdescription;
	private ImageView image;
	
	private Project project;
	
	private byte[] mContent;
	private Bitmap myBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_project);
		
		image = (ImageView) findViewById(R.id.image);
		image.setOnClickListener(this);
		
		set = (Button) findViewById(R.id.set);
		set.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == set){
			
			SQLinit();
			
			Intent intent = new Intent(CreateProjectActivity.this,MyProjectActivity.class);	
            startActivity(intent);
				
		}else if(v == image){
			
			final CharSequence[] items = {"相册","拍照"};
			
			AlertDialog.Builder dlg = new AlertDialog.Builder(CreateProjectActivity.this);
			dlg.setTitle("选择照片");
			dlg.setItems(items, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					if(which == 1){
						Intent getImageCamera = new Intent("android.media.action.IMAGE_CAPTURE");
						
						startActivityForResult(getImageCamera,1);
						
					}else{
						
						Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
						getImage.addCategory(Intent.CATEGORY_OPENABLE);
						getImage.setType("image/jpeg");
						startActivityForResult(getImage,0);
					}
					
				}
			}).create();
			
		dlg.show();	
		}
		
		
	}
	
	
	@Override
	public void onActivityResult(int requestCode ,int resultCode , Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		ContentResolver contentResolver = getContentResolver();
		
		if(requestCode == 0){
			
			
			Uri selectedImage = data.getData();
			String[] filePathColumn = {MediaStore.Images.Media.DATA};
			
			Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
			cursor.moveToFirst();
			
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturPath = cursor.getString(columnIndex);
			cursor.close();
			
			myBitmap = BitmapFactory.decodeFile(picturPath);
			
			image.setImageBitmap(myBitmap);
			
			
			
			
			
		}else if(requestCode == 1){
			
			Bundle extras = data.getExtras();

			myBitmap = (Bitmap) extras.get("data");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			mContent = baos.toByteArray();
			
			image.setImageBitmap(myBitmap);
			
			
		}
	}
	
	
	public void SQLinit(){
		
		editname = (EditText) findViewById(R.id.editname);
		editurl = (EditText) findViewById(R.id.editurl);
		editintroduction = (EditText) findViewById(R.id.editintroduction);
		editstage = (EditText) findViewById(R.id.editstage);
		editarea = (EditText) findViewById(R.id.editarea);
		editcity = (EditText) findViewById(R.id.editcity);
		editdescription = (EditText) findViewById(R.id.editdescription);
		
		String name = editname.getText().toString();
		String url = editurl.getText().toString();
		String introduction = editintroduction.getText().toString();
		String stage = editstage.getText().toString();
		String arae = editarea.getText().toString();
		String city = editcity.getText().toString();
		String description = editdescription.getText().toString();
		
		byte[] picPath = null;
		image.setDrawingCacheEnabled(true);
		Bitmap bmp = Bitmap.createBitmap(image.getDrawingCache());
		image.setDrawingCacheEnabled(false);
		if(bmp != null){
			picPath = getByteByBitmap(bmp);
		}
		
		Log.e("sqlite", name);
		
		project = new Project(name,url,introduction,stage,arae,city,description,picPath);
		
		Log.i("system", project.toString());
		
		
		mysqlite = new MySQLite(this);	
		ProjectDao dao = new ProjectDao(mysqlite);
		dao.insertProject(project);


		
	}
	
	
	public static byte[] readStream(InputStream in) throws Exception{
		
		byte[] buffer = new byte[1024];
		int len = -1;
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		while((len = in.read(buffer)) != 1){
			
			outStream.write(buffer, 0, len);
			
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		
		return data;
	}
		
	
	public static Bitmap getPicFromBytes(byte[] bytes){
		
		if(bytes != null){
			
			return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		}
		
		return null;
	}
	
	
	public byte[] getByteByBitmap(Bitmap bmp){
	
		byte[] imagebyte = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
		imagebyte = outStream.toByteArray();
		
		try {
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imagebyte;
	}
	
}
		
		