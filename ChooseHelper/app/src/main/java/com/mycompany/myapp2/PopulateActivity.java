package com.mycompany.myapp2;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import java.util.*;
import android.content.*;
import android.content.pm.*;
import android.view.View.*;
import android.widget.AdapterView.*;

/**
Here we populate both of lists:items and criterias
*/

public class PopulateActivity extends Activity
{
	
	EditText et1,et2;
	ListView lvMain,lvMain2;
	
	int posit,posit2;
	
	
	AlertDialog.Builder ad;
    Context context;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
	
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		setContentView(R.layout.populate_layout);

		
		et1=(EditText)findViewById(R.id.et1);
		et2=(EditText)findViewById(R.id.et2);
		
		// находим список
		lvMain = (ListView) findViewById(R.id.lv1);
	    lvMain2 = (ListView) findViewById(R.id.lv2);		
								
				
		lvMain.setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
											   int pos, long id) {
					// TODO Auto-generated method stub

                   posit=pos;
					
					AlertDialog.Builder builder = new AlertDialog.Builder(PopulateActivity.this);
					builder.setTitle("")
						.setMessage("Вы действительно хотите удалить этот пункт?")
						.setCancelable(false)
						.setNegativeButton("Отмена",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						})


						.setPositiveButton("Удалить",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								
								
							showMessage(MyApp.arrayNames.get(posit)+" удалено!");
								
								MyApp.arrayNames.remove(posit);

								refreshAdapter();

							}
						});


					AlertDialog alert = builder.create();
					alert.show();
					
					
					return true;
				}
			}); 
		
		
			
			
			
			
		lvMain2.setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
											   int pos, long id) {
					// TODO Auto-generated method stub

					
					
					posit2=pos;

					AlertDialog.Builder builder = new AlertDialog.Builder(PopulateActivity.this);
					builder.setTitle("")
						.setMessage("Вы действительно хотите удалить этот пункт?")
						.setCancelable(false)
						.setNegativeButton("Отмена",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						})


						.setPositiveButton("Удалить",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();


								showMessage(MyApp.arrayCriteria.get(posit2)+" удалено!");

								MyApp.arrayCriteria.remove(posit2);

								refreshAdapter();

							}
						});


					AlertDialog alert = builder.create();
					alert.show();
					
					
					return true;
				}
			});
		
		
		refreshAdapter();
	}
	
	
	
	
	   // adds a new item for comparing        
	public void onClick1(View v){
		
		
		if((et1.getText().toString()).matches("")){
			
			showMessage("Поле не должно быть пустым!");
			
		}else{
		
		MyApp.arrayNames.add(et1.getText().toString());
		
		et1.setText("");
		
		refreshAdapter();
		
		}
	}
	
	
	
	   //adds a new item criteria of choosing
	public void onClick2(View v){
		
		
		
		if((et2.getText().toString()).matches("")){

			showMessage("Поле не должно быть пустым!");

		}else{

MyApp.arrayCriteria.add(et2.getText().toString());
		
et2.setText("");

		refreshAdapter();
	}
	}
	
	
	
	//go to table of estimation
	public void onClick3(View v){
		
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	
	
	
	void refreshAdapter(){
		//create adapters
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
																android.R.layout.simple_list_item_1, MyApp.arrayNames.toArray(new String[0]));
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
																 android.R.layout.simple_list_item_1, MyApp.arrayCriteria.toArray(new String[0]));

		lvMain.setAdapter(adapter);
		lvMain2.setAdapter(adapter2);
		
	}
	
	
	//just simple plain method for show float messages
	void showMessage(String s){
		
		Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
	}
	}
