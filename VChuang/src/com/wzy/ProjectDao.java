package com.wzy;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * User对象的dao 执行对user的增、删 、改、 查
 * 
 * @author Administrator
 *
 */
public class ProjectDao {

	private MySQLite mySQLite; // 数据库对象

	public ProjectDao(MySQLite mySQLite) {
		this.mySQLite = mySQLite;
	}

	/**
	 * 向数据库添加一条记录
	 * 
	 * @param user
	 * @return
	 */

	/**
	 * 使用SQLiteDatabase提供的insert方法进行插入操作
	 * 
	 * @return
	 */
	public long insertProject(Project project) {
		// 1、获取SQLiteDatabase
		SQLiteDatabase db = mySQLite.getReadableDatabase();
		// 2、获取一个ContentValue对象
		ContentValues values = new ContentValues();
		// 3、设置要插入的数据参数
		values.put("name", project.getName());
		values.put("url", project.getUrl());
		values.put("introduction", project.getIntroduction());
		values.put("stage", project.getStage());
		values.put("area", project.getArea());
		values.put("city", project.getCity());
		values.put("description", project.getDescription());
		values.put("picPath", project.getPicPath());
		
		// 4、执行sql操作
		/**
		 * table :表名
		 */
		long id = db.insert("project", null, values);
		
		Log.i("success",id+"");
		Log.i("projectsystem", "项目创建成功");
		// 关闭数据库连接
		db.close();
		return id;
	}

	public long updateProject(Project project) {
		// 1、获取SQLiteDatabase
		SQLiteDatabase db = mySQLite.getReadableDatabase();
		// 2、获取一个ContentValue对象
		ContentValues values = new ContentValues();
		// 3、设置要更新的数据参数
		values.put("name", project.getName());
		values.put("url", project.getUrl());
		values.put("introduction", project.getIntroduction());
		values.put("stage", project.getStage());
		values.put("area", project.getArea());
		values.put("city", project.getCity());
		values.put("description", project.getDescription());
		values.put("picPath", project.getPicPath());
		// 4、执行sql操作
		/**
		 * table :表名
		 */
		long count = db.update("project", values, "name=?", new String[]{project.getName()});
		
		// 关闭数据库连接
		db.close();
		return count;

	}

	/**
	 * 获取数据库中所有的User对象
	 * 
	 * @return
	 */
	public List<Project> getAllProject() {
		// 1、获取SQLiteDatabase
				SQLiteDatabase db = mySQLite.getReadableDatabase();
		
		List<Project> lists = new ArrayList<Project>();
		String sql = "select * from project ";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			String name = cursor.getString(1);
			String url = cursor.getString(2);   
			String introduction = cursor.getString(3);               
			String stage = cursor.getString(4);                
			String area = cursor.getString(5);  
			String city = cursor.getString(6);   
			String description = cursor.getString(7);          
		    byte[] picPath = cursor.getBlob(8); 
			Project project = new Project(name,url,introduction,stage,area,city,description,picPath);
			lists.add(project);

		}
		cursor.close();
		db.close();
		return lists;
	}
	/**
	 * 执行删除操作
	 * @param user
	 * @return
	 */
	public long deleteUser(Project project){
		//1、获取数据库对象
		SQLiteDatabase db= mySQLite.getReadableDatabase();
		//2、执行删除操作
			//delete user where user_name='dadada'
		long count = db.delete("project", "name=?", new String[]{project.getName()});
		//3、关闭数据库连接
		db.close();
		return count;
	}
	/**
	 * 查询所有的用户
	 * @return
	 */
	public List<Project> getAllProject1(){
		List<Project> list = new ArrayList<Project>();
		//1、获取数据库连接对象
		SQLiteDatabase db = mySQLite.getReadableDatabase();
		//2、执行查询操作
		Cursor cursor = db.query("project", null, null, null, null, null, "_id");
		//从结果集中获取数据
		if(cursor != null && cursor.getCount()>0){
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
				String url = cursor.getString(cursor.getColumnIndex("url"));
				String stage = cursor.getString(cursor.getColumnIndex("stage"));
				String area = cursor.getString(cursor.getColumnIndex("area"));
				String description = cursor.getString(cursor.getColumnIndex("description"));
				String city = cursor.getString(cursor.getColumnIndex("city"));
				byte[] picPath = cursor.getBlob(cursor.getColumnIndex("picPath"));
				list.add(new Project(name,introduction,url,stage,area,description,city,picPath));
				
			}
		}
		//4、返回数据
		return list;
	}
	public Project getProjectByName(String name){
		//1、获取数据库对象
		SQLiteDatabase db = mySQLite.getReadableDatabase();
		//2、执行查询操作
		Cursor cursor = db.query("project", null, "name=?", new String[]{name}, null, null, null);
		//3、遍历cursor，获取数据
		if(cursor != null && cursor.getCount()>0){
			while(cursor.moveToNext()){
				String name1 = cursor.getString(cursor.getColumnIndex("name"));
				String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
				String url = cursor.getString(cursor.getColumnIndex("url"));
				String stage = cursor.getString(cursor.getColumnIndex("stage"));
				String area = cursor.getString(cursor.getColumnIndex("area"));
				String description = cursor.getString(cursor.getColumnIndex("description"));
				String city = cursor.getString(cursor.getColumnIndex("city"));
				byte[] picPath = cursor.getBlob(cursor.getColumnIndex("picPath"));
	
				return new Project(name1,introduction,url,stage,area,description,city,picPath);
			}
		}
		//4.关闭cursor
		cursor.close();
		//5.关闭数据库
		db.close();
		return null;
	}
	
}
