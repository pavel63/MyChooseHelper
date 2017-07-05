package com.mycompany.myapp2;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.widget.TableRow.*;
import java.util.*;
import android.view.*;
import android.content.*;
import android.graphics.*;
import android.text.*;
import android.content.pm.*;


/**
here we create and populate table with 
variants wnd criteria.Also count result
*/


public class MainActivity extends Activity 
{
	
	String[]namesCriter=new String[MyApp.arrayCriteria.size()];
	String[]namesItems=new String[MyApp.arrayNames.size()];
	
	int arrayValues[]=new int[namesItems.length];
	
	EditText et;
	TextView tv,tv2;
	ArrayList<ArrayList>mainArray=new ArrayList<ArrayList>();
	ArrayList<EditText>listEditText;
	
	List<EditText> allEds;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
		//set portrait orientation for activity
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		//fetchs global arrays of data
		namesCriter=MyApp.arrayCriteria.toArray(new String[MyApp.arrayCriteria.size()]);
		namesItems=MyApp.arrayNames.toArray(new String[MyApp.arrayNames.size()]);
	
		
		//create table,fill it by names and edittext initial 0
		LinearLayout linearLayout=new LinearLayout(this);
		LinearLayout.LayoutParams llpar=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		linearLayout.setLayoutParams(llpar);
		
		
		TableRow tableRow;
		TableLayout tableLayout=new TableLayout(this);
		
		
		tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

		
		TableRow tableRowsCriteras=new TableRow(this);
		
		for(int i=0;i<=namesCriter.length;i++){
			
			tv=new TextView(this);
			
			if(i!=0){
				tv.setText(namesCriter[i-1]);
			}
			
			tableRowsCriteras.addView(tv);
		}
		
		
		tableLayout.addView(tableRowsCriteras);
		
		
		for(int i=0;i<namesItems.length;i++){
		
			tableRow=new TableRow(this);
			
		listEditText=new ArrayList<EditText>();
		
		for(int ii=0;ii<=namesCriter.length;ii++){
			
			if(ii!=0){
			
			et=new EditText(this);
			
			
			et.setInputType(InputType.TYPE_CLASS_NUMBER);
			

			
			//sets an initial value of edittext
			et.setText("0");
			
			//sets cursor on end of text
			et.setSelection(1,1);
			
			
			listEditText.add(et);
			
			
			tableRow.addView(et);
			}else{
				
				tv2=new TextView(this);
				tv2.setText(namesItems[i]);
				tableRow.addView(tv2);
				
			}
			
		}
		
		tableLayout.addView(tableRow);

		mainArray.add(listEditText);
		
		}

		
		 Button button=new Button(this);
		 
		 button.setBackground(getResources().getDrawable(R.drawable.my_button));
		 
		 
		//button.setBackgroundColor(Color.parseColor("#FFA500"));
		
		 button.setText("Вычислить лучший вариант!");
		 
		 

tableLayout.addView(button);

		 button.setOnClickListener(new View.OnClickListener(){
		 public void onClick(View v){

			 
			 for(int i=0;i<namesItems.length;i++){
				 
				 ArrayList<EditText>ared=mainArray.get(i);
				 
				 
				 for(int ii=0;ii<ared.size();ii++){
					 
					 String prom=ared.get(ii).getText().toString();
					 int value=Integer.valueOf(prom);
					 
					 arrayValues[i]=arrayValues[i]+value;
				 }
				 
			 }
			 
			 
			 int maxIndex = 0;
			 for (int i = 1; i < arrayValues.length; i++) {
				 int newnumber = arrayValues[i];
				 if ((newnumber > arrayValues[maxIndex])) {
					 maxIndex = i;
				 }
			 }
		
			 
			Toast.makeText(getApplicationContext(),"Лучший вариант: "+namesItems[maxIndex],Toast.LENGTH_LONG).show();
			
		 }
		 });
		 
		 
		
		setContentView(tableLayout);
		
		}
}

