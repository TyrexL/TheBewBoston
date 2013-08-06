package com.example.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener, android.widget.RadioGroup.OnCheckedChangeListener {


	TextView tv_question, tv_text;
	Button bReturn;
	RadioGroup selectionList;
	String gotBread;
	String setData;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.send);
	initialize();
	
	SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	String et = getData.getString("name", "Travis is");
	String values = getData.getString("list", "4");
	if(values.contentEquals("1"))
	{
		tv_question.setText(et);
	}
	//getData();
}


private void getData() {
	Bundle	gotBasket = getIntent().getExtras();
	gotBread = (String) gotBasket.getString("key");
	tv_question.setText(gotBread);
}


private void initialize() {
	tv_question = (TextView) findViewById(R.id.tvQuestion);
	tv_text = (TextView) findViewById(R.id.tvText);
	bReturn = (Button) findViewById(R.id.bReturn);
	bReturn.setOnClickListener(this);
	selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
	selectionList.setOnCheckedChangeListener(this);
}


@Override
public void onClick(View v) {
	Intent person = new Intent();
	Bundle backpack = new Bundle();
	backpack.putString("answer", setData);
	person.putExtras(backpack);
	setResult(RESULT_OK, person);
	finish();
}


@Override
public void onCheckedChanged(RadioGroup group, int checkedId) {
	switch(checkedId)
	{
	case R.id.rCrazy:
		setData = "Probably right!";
		break;
	case R.id.rSexy:
		setData = "Defenitely right!";
		break;
	case R.id.rBoth2:
		setData = "Spot on!";
	break;
	}
	tv_text.setText(setData);
}



}
