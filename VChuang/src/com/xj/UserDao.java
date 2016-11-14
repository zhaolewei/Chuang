package com.xj;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * User对象的dao 执行对user的增、删 、改、 查
 * 
 * @author Administrator
 *
 */
public class UserDao {

	private MySqlite mySqlite; // 数据库对象

	public UserDao(MySqlite mySqlite) {
		this.mySqlite = mySqlite;
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
	public long insert1User(User user) {
		// 1、获取SQLiteDatabase
		SQLiteDatabase db = mySqlite.getReadableDatabase();
		// 2、获取一个ContentValue对象
		ContentValues values = new ContentValues();
		// 3、设置要插入的数据参数
		values.put("username", user.getUsername_user());
		values.put("password", user.getPassword_user());
		values.put("age", user.getAge_user());
		values.put("sex", user.getSex_user());
		values.put("type", user.getType_user());
		values.put("company", user.getCompany_user());
		values.put("phonenum", user.getPhonenum_user());
		values.put("email", user.getEmail_user());
		values.put("job", user.getJob_user());
		values.put("city", user.getCity_user());
		values.put("address", user.getAddress_user());
		// 4、执行sql操作
		/**
		 * table :表名
		 */
		long id = db.insert("user", null, values);
		// 关闭数据库连接
		db.close();
		return id;
	}

	public long update1User(User user) {
		// 1、获取SQLiteDatabase
		SQLiteDatabase db = mySqlite.getReadableDatabase();
		// 2、获取一个ContentValue对象
		ContentValues values = new ContentValues();
		// 3、设置要更新的数据参数
		values.put("username", user.getUsername_user());
		values.put("password", user.getPassword_user());
		values.put("age", user.getAge_user());
		values.put("sex", user.getSex_user());
		values.put("type", user.getType_user());
		values.put("company", user.getCompany_user());
		values.put("phonenum", user.getPhonenum_user());
		values.put("email", user.getEmail_user());
		values.put("job", user.getJob_user());
		values.put("city", user.getCity_user());
		values.put("address", user.getAddress_user());
		// 4、执行sql操作
		/**
		 * table :表名
		 */
		long count = db.update("user", values, "username=?", new String[]{user.getUsername_user()});
		
		// 关闭数据库连接
		db.close();
		return count;

	}

	/**
	 * 获取数据库中所有的User对象
	 * 
	 * @return
	 */
	public List<User> getAllUser() {
		// 1、获取SQLiteDatabase
				SQLiteDatabase db = mySqlite.getReadableDatabase();
		
		List<User> lists = new ArrayList<User>();
		String sql = "select * from user ";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			String userName = cursor.getString(1);
			String passWord = cursor.getString(2);
			int age = cursor.getInt(3);
			String sex = cursor.getString(4);
			String type = cursor.getString(5);
			String company = cursor.getString(6);
			String phonenum = cursor.getString(7);
			String email = cursor.getString(8);
			String job = cursor.getString(9);
			String city = cursor.getString(10);
			String address = cursor.getString(11);
			User user = new User(userName, passWord, age, sex, type, company, phonenum, email, job, city, address);
			lists.add(user);

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
	public long deleteUser(User user){
		//1、获取数据库对象
		SQLiteDatabase db= mySqlite.getReadableDatabase();
		//2、执行删除操作
			//delete user where user_name='dadada'
		long count = db.delete("user", "username=?", new String[]{user.getUsername_user()});
		//3、关闭数据库连接
		db.close();
		return count;
	}
	/**
	 * 查询所有的用户
	 * @return
	 */
	public List<User> getAllUser1(){
		List<User> list = new ArrayList<User>();
		//1、获取数据库连接对象
		SQLiteDatabase db = mySqlite.getReadableDatabase();
		//2、执行查询操作
		Cursor cursor = db.query("user", null, null, null, null, null, "_id");
		//从结果集中获取数据
		if(cursor != null && cursor.getCount()>0){
			while (cursor.moveToNext()) {
				String userName = cursor.getString(cursor.getColumnIndex("username"));
				String passWord = cursor.getString(cursor.getColumnIndex("password"));
				int age = cursor.getInt(3);
				String sex = cursor.getString(cursor.getColumnIndex("sex"));
				String type = cursor.getString(cursor.getColumnIndex("type"));
				String company = cursor.getString(cursor.getColumnIndex("company"));
				String phonenum = cursor.getString(cursor.getColumnIndex("phonenum"));
				String email = cursor.getString(cursor.getColumnIndex("email"));
				String job = cursor.getString(cursor.getColumnIndex("job"));				
				String city = cursor.getString(cursor.getColumnIndex("city"));
				String address = cursor.getString(cursor.getColumnIndex("address"));
				list.add(new User(userName, passWord, age, sex, type, company, phonenum, email, job, city, address));
				
			}
		}
		//4、返回数据
		return list;
	}
	public User getUserByUserNameAndPassWord(String  userName,String passWord){
		//1、获取数据库对象
		SQLiteDatabase db = mySqlite.getReadableDatabase();
		//2、执行查询操作
		Cursor cursor = db.query("user", null, "username=? and password=?", new String[]{userName,passWord}, null, null, null);
		//3、遍历cursor，获取数据
		if(cursor != null && cursor.getCount()>0){
			while(cursor.moveToNext()){
				String userName1 = cursor.getString(cursor.getColumnIndex("username"));
				String passWord1 = cursor.getString(cursor.getColumnIndex("password"));
				int age = cursor.getInt(3);
				String sex = cursor.getString(cursor.getColumnIndex("sex"));
				String type = cursor.getString(cursor.getColumnIndex("type"));
				String company = cursor.getString(cursor.getColumnIndex("company"));
				String phonenum = cursor.getString(cursor.getColumnIndex("phonenum"));
				String email = cursor.getString(cursor.getColumnIndex("email"));
				String job = cursor.getString(cursor.getColumnIndex("job"));				
				String city = cursor.getString(cursor.getColumnIndex("city"));
				String address = cursor.getString(cursor.getColumnIndex("address"));
				return new User(userName1, passWord1, age, sex, type, company, phonenum, email, job, city, address);
			}
		}
		//4.关闭cursor
		cursor.close();
		//5.关闭数据库
		db.close();
		return null;
	}
	
}

