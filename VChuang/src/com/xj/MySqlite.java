package com.xj;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 
 * @author Administrator SQLiteOpenHelper是干什么用的？
 *         1、他是android为我们提供的操作SQLITE数据库的一个帮助类 2、需要注意的方法 1、Oncreate()方法
 *         只调用一次(在我们需要获取该数据库并且该数据库文件不存在的时候调用) 用法： 1、一般在该方法里面执行创建数据表操作 2、添加一些输出数据
 * 
 *         3、onUpgrade() 数据库版本升级的时候调用 、 4、构造方法 SQLiteDataBase(Context context,
 *         String name, CursorFactory factory,int version)
 *         context：上下文对象（可以传context，activity对象，application对象。。） name：数据库的名称
 *         factory：一般传null version:数据库版本，可以用于升级数据库
 */
public class MySqlite extends SQLiteOpenHelper {

	private static final String DATEBASE_NAME = "vchuangsqliteAndroid.db"; // 数据库名称

	private static int version = 1; // 数据库版本号

	private Context context;

	/**
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public MySqlite(Context context) {
		super(context, DATEBASE_NAME, null, version);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 只调用一次(在我们需要获取该数据库并且该数据库文件不存在的时候调用) 1、一般在该方法里面执行创建数据表操作 2、添加一些。。数据
	 * 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		/**
		 * 创建数据表的步骤： 1、拼写SQL语句 2、执行建表操作 execSQL() 执行sql语句的方法
		 * 
		 * 
		 */
		String sql = "create table user(_id INTEGER PRIMARY KEY AUTOINCREMENT,username varchar,password varchar,age int,sex varchar,type varchar,company varchar,phonenum varchar,email varchar,job varchar,city varchar,address varchar)";
		db.execSQL(sql);
		// String insertOne =
		// "insert into user values(3,'android','android',1,8)";
		// db.execSQL(insertOne);

	}

	@Override
	// 升级数据库的时候使用
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
