package com.example.premedievalcalendar;
		
		import android.annotation.TargetApi;
		import android.app.Activity;
		import android.os.Build;
		import android.support.v7.app.AppCompatActivity;
		import android.os.Bundle;
		import android.view.Menu;
		import android.view.MenuItem;
		import android.view.View;
		import android.widget.AdapterView;
		import android.widget.ArrayAdapter;
		import android.widget.EditText;
		import android.widget.Spinner;
		import android.widget.TextView;
		import android.widget.Toolbar;
		
		import java.time.LocalDate;
		import java.util.Calendar;
		import java.util.concurrent.atomic.AtomicInteger;
		
		import static com.example.premedievalcalendar.R.*;
		import static java.lang.Integer.valueOf;
						                            import static java.lang.String.*;
								                                          import static java.time.LocalDate.now;

@TargetApi(Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity
{
	public static LocalDate localDate = LocalDate.now();
	public int da;
	public int mont;
	private EditText ye;
	Spinner selectdate = (Spinner) findViewById( id.spinnerdate);
	Spinner selectmonth=(Spinner) findViewById( id.spinnermonth );
	private EditText annumindex;
	private TextView ascertained;
	ArrayAdapter<CharSequence> selecteddate;
	
	{
		selecteddate = ArrayAdapter.createFromResource( this,
				array.selecteddate, android.R.layout.simple_spinner_item );
	}
	
	ArrayAdapter<CharSequence> selectedmonth;
	
	{
		selectedmonth = ArrayAdapter.createFromResource( this,
				array.selectedmonth, android.R.layout.simple_spinner_item );
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView( layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById( id.action_settings);
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	
	public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener
	{
		
		public void onItemSelected(AdapterView<?> parent, View view,
		                           int pos, long id)
		{
			mont= (int) parent.getItemAtPosition(pos);
		}
		
		public void onNothingSelected(AdapterView<?> parent)
		{
		}
	}
	public class SpinnerActivities extends Activity implements AdapterView.OnItemSelectedListener
	{
		
		public void onItemSelected(AdapterView<?> parent, View view,
		                           int pos, long id)
		{
			da= (int) parent.getItemAtPosition(pos);
			ye= (EditText) findViewById(R.id.annumindex);
			ascertained.setText(Determine(da,mont, valueOf( String.valueOf( ye ) )));
		}
		
		public void onNothingSelected(AdapterView<?> parent)
		{
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		selectdate.setOnItemSelectedListener( (AdapterView.OnItemSelectedListener) this );
		selectmonth.setOnItemSelectedListener( (AdapterView.OnItemSelectedListener) this );
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	public String Determine(int da, int mont, int ya)
	{
		int difference;
		String Determine = null;
		if (!(!(mont % 2 == 0.0) || !(mont > 7) || !(da == 31)))
		{
			da=30;
		}
		if ((mont==2)&&(da==30))
		{
			da=29;
		}
		if (!(leap( valueOf(String.valueOf(ye) )) && (mont == 2) ))
		{
			if (da == 29) {
				switch (da = 1) {
				}
				switch (mont = 3) {
				}
			}
			else
			{
			da = 28;
		}}
		
		difference=(int) Math.abs(PastOrFuture(da,mont,ye)*Remainder(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH)-PastOrFuture(da,mont,ye)*Remainder(valueOf( String.valueOf( ye ) ),mont,da))+middle(valueOf( String.valueOf( ye ) ));
		AtomicInteger diff = new AtomicInteger();
		switch ((Math.abs(Remainder(Calendar.YEAR, Calendar.MONTH,Calendar.DAY_OF_MONTH)-Remainder((1972),(12),(16)))+middle( 1972) )%7)
		{
			case 0:
			{
				diff.set(6);
			}
			case 1:
			{
				diff.set(7);
			}
			default:
			{
				diff.set(Math.abs( (Math.abs( Remainder( Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH ) - Remainder( 1972, 12, 16 ) ) + middle( 1972  )) % 7 - 1 ));
			}
		}
		int meter = 0;
		if (PastOrFuture(da,mont,ye)==1.0)
		{
			if (Math.signum(diff.get() -difference%7)==1.0)
			{
				switch (meter = Math.abs(diff.get() - difference % 7)) {
				}
			} else
			{
				meter=7-Math.abs(diff.get() -difference%7);
			}
		}
		else  if (PastOrFuture(da,mont,ye)==-1)
		{
			if (Math.signum(diff.get() -difference%7)==1.0)
			{
				meter=Math.abs(diff.get() -difference%7);
			}
			else
			{
				meter=diff.get() +difference%7;
				if ((meter>7)||(meter<0))
				{
					meter=3-meter;
				}
			}
		}
		if (meter==1)
		{
			return "Monday";
		}
		else if (meter==2)
		{
			return "Tuesday";
		}
		else if (meter==3)
		{
			return "Wednesday";
		}
		else if (meter==4)
		{
			return "Thursday";
		}
		else if (meter==5)
		{
			return "Friday";
		}
		else if (meter==6)
		{
			return "Saturday";
		}
		else
		{
			return "Sunday";
		}
	}
	public Boolean leap (int ya)
	{
		if (ya%4==0)
		{
			if (ya%100==0)
			{
				if (ya%400==0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	public int Remainder(int ya, int mon, int da)
	{
		int Remainder = 0,i;
		for (i=1;i<mon;i++)
		{
			switch (i)
			{
				case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			{
				Remainder=Remainder+31;
			}
				case 4: case 6: case 9: case 11:
			{
				Remainder=Remainder+30;
			}
				case 2:
				{
					if (leap(ya)==true)
					{
						Remainder=Remainder+29;
					}
					else
					{
						Remainder=Remainder+28;
					}
				}
			}
		}
		Remainder=Remainder+da;
		return Remainder;
	}
	public int middle(int year)
	{
		int i,middle = 0;
		
		for (i=(int) (valueOf( String.valueOf( ye ) )+PastOrFuture(da,mont,ye)); PastOrFuture(da,mont,ye)*i<=PastOrFuture(da,mont,ye)*Calendar.YEAR; i=(int) (i+PastOrFuture(da,mont,ye)))
		{
			if (leap(i))
			{
				middle=middle+366;
			}
			else
			{
				middle=middle+365;
			}
		}
		return middle;
	}
	public float PastOrFuture(int da, int mont, EditText ye)
	{
		
		float a;
		a = Math.signum( Calendar.YEAR - valueOf( String.valueOf( ye ) ) );
		if (a==0.0)
		{
			a=Math.signum(Calendar.MONTH-mont);
		}
		if (a==0.0)
		{
			a=Math.signum(Calendar.DAY_OF_MONTH-da);
		}
		if (a==0.0)
		{
			a= (float) 1.0;
		}
		return a;
	}}
