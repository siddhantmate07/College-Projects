import java.util.Scanner;

interface ConversionInterface
{
	String[][] units ={{ "GBP" , "CNY" , "INR" , "RUB" , "USD" },
						{ "mm" , "cm" , "m" , "km" , "in" , "ft" , "yd" },
						{ "C" , "F" , "K" },
						{ "mg" , "g" , "kg" , "oz" , "lb" },
						{ "m/s" , "km/hr" , "mi/hr" , "ft/s" , "kn" }};
	void calculate();
}

abstract class ConversionAbstract implements ConversionInterface
{
	protected int firstChoice;
	protected int secChoice;
	protected float inputValue;
	protected float outputValue;

	abstract void conversionList();

	public void from(int j)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Convert From : ");
		try
		{
			firstChoice = scan.nextInt();
			if (firstChoice < 1 || firstChoice > j)
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println("\nChoice Selection Error !!!\n" +
					"Please Enter Correct Integer Choice\n");
			from(j);
		}
	}

	public void to(int j)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Convert To : ");
		try
		{
			secChoice = scan.nextInt();
			if (secChoice < 1 || secChoice > j)
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println("\nChoice Selection Error !!!\n" +
					"Please Enter Correct Integer Choice\n");
			to(j);
		}
	}

	public void takeInput(int i)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("\nEnter Value in "+units[i][firstChoice-1]+" : ");
		try
		{
			inputValue = scan.nextFloat();
		}
		catch (Exception e)
		{
			System.out.println("\nWrong Value Entered !!!\n" +
					"Please Enter Correct Float Value\n");
			takeInput(i);
		}
	}

	public void printConversion(int i)
	{
		{
			System.out.println(inputValue+" "+units[i][firstChoice-1]+" = "+outputValue+" "+units[i][secChoice-1]);
		}
	}

	public void print()
	{
		System.out.println("\n1. Continue\n" +
							"2. Change Conversion Factor\n" +
							"3. Go To Main Menu\n" +
							"4. Exit This Tool\n");
		System.out.print("Enter Your Choice : ");
	}

	public int choice()
	{
		int ch = 0;
		Scanner scan = new Scanner(System.in);
		try
		{
			ch = scan.nextInt();
			if (ch < 1 || ch > 4)
				throw new Exception();
		}
		catch (Exception e)
		{
			System.out.print("\nChoice Selection Error !!!\n" +
					"Please Enter Correct Integer Choice : ");
			choice();
		}
		return ch;
	}
}

class Currency extends ConversionAbstract
{
	double[][] currencyArray = {{ 1 , 9.04 , 100.97 , 101.77 , 1.39 },
								{ 0.11 , 1 , 11.15 , 11.24 , 0.15 },
								{ 0.0099 , 0.09 , 1 , 1.01 , 0.014 },
								{ 0.0098 , 0.089 , 0.99 , 0.014 },
								{ 0.72 , 0.65 , 72.52 , 73.15 , 1 }};

	public void conversionList()
	{
		System.out.println("\n1. British Pound GBP\n" +
							"2. Chinese Yuan CNY\n" +
							"3. Indian Rupee INR\n" +
							"4. Russian Ruble RUB\n" +
							"5. United States Dollar USD\n");
	}

	public void calculate()
	{
		outputValue = (float) ( inputValue * currencyArray[firstChoice-1][secChoice-1] );
	}
}

class Length extends ConversionAbstract
{
	double[][] lengthArray = {{ 1 , 0.1 , 0.001 , 0.000001 , 0.0393701 , 0.00328084 , 0.00109361 },
							{ 10 , 1 , 0.01 , 0.00001 , 0.393701 , 0.0328084 , 0.0109361 },
							{ 1000 , 100 , 1 , 0.001 , 39.3701 , 32.8084 , 1.09361 },
							{ 1000000 , 100000 , 1000 , 1 , 39370.1 , 3280.84 , 1093.61 },
							{ 25.4 , 2.54 , 0.0254 , 0.0000254 , 1 , 0.0833333 , 0.0277778 },
							{ 304.8 , 30.84 , 0.3048 , 0.0003048 , 12 , 1 , 0.3333333 },
							{ 914.4 , 91.44 , 0.9144 , 0.0009144 , 36 , 3 , 1 }};

	public void conversionList()
	{
		System.out.println("\n1. Millimeter (mm)\n" +
							"2. Centimeter (cm)\n" +
							"3. Meter (m)\n" +
							"4. Kilometer (km)\n" +
							"5. Inch (in)\n" +
							"6. Foot (ft)\n" +
							"7. Yard (yd)\n");
	}

	public void calculate()
	{
		outputValue = (float) ( inputValue * lengthArray[firstChoice-1][secChoice-1] );
	}
}

class Temperature extends ConversionAbstract
{
	public void conversionList()
	{
		System.out.println("\n1. Celsius\n" +
							"2. Fahrenheit\n" +
							"3. Kelvin\n");
	}

	public void calculate()
	{
		switch (firstChoice)
		{
			case 1 :
				switch (secChoice)
				{
					case 1 -> outputValue = inputValue * 1;
					case 2 -> outputValue = (inputValue * 9 / 5) + 32 ;
					case 3 -> outputValue = (float) ( inputValue + 273.15 );
				}
				break;
			case 2 :
				switch (secChoice)
				{
					case 1 -> outputValue = ( inputValue - 32 ) * 5 / 9 ;
					case 2 -> outputValue = inputValue * 1 ;
					case 3 -> outputValue = (float) (( inputValue - 32 ) * 5 / 9 + 273.15 );
				}
				break;
			case 3 :
				switch (secChoice)
				{
					case 1 -> outputValue = (float) ( inputValue - 273.15 );
					case 2 -> outputValue = (float) (( inputValue - 273.15 ) * 9 / 5 + 32 );
					case 3 -> outputValue = inputValue * 1 ;
				}
		}
	}
}

class Weight extends ConversionAbstract
{
	double[][] weightArray = {{ 1 , 0.001 , 0.000001 , 0.000035 , 0.0000022 },
							{ 1000 , 1 , 0.001 , 0.035274 , 0.002204 },
							{ 1000000 , 1000 , 1, 35.274 , 2.20462 },
							{ 28349.5 , 28.3495 , 0.02834 , 1 , 0.0625 },
							{ 453592 , 453.592 , 0.453592 , 16 , 1 }};

	public void conversionList()
	{
		System.out.println("\n1. Milligram (mg)\n" +
							"2. Gram (g)\n" +
							"3. Kilogram (kg)\n" +
							"4. Ounce (oz)\n" +
							"5. Pound (lb)\n");
	}

	public void calculate()
	{
		outputValue = (float) ( inputValue * weightArray[firstChoice-1][secChoice-1] );
	}
}

class Speed extends ConversionAbstract
{
	double[][] speedArray = {{ 1 , 3.6 , 2.23694 , 3.28084 , 1.94384 },
							{  0.277778 , 1 , 0.621371 ,  0.911344 ,  0.539957 },
							{ 0.44704 , 1.60934 ,  1 , 1.46667 , 0.868976 },
							{ 0.3084 , 1.09728 , 0.681818 , 1 , 0.592484 },
							{ 0.514444 , 1.852 , 1.15078 , 1.68781 , 1 }};

	public void conversionList()
	{
		System.out.println("\n1. Meter/Second (m/s)\n" +
							"2. Kilometer/Hour (km/hr)\n" +
							"3. Miles/Hour (mi/hr)\n" +
							"4. Feet/Second (ft/s)\n" +
							"5. Knot (kn)\n");
	}

	public void calculate()
	{
		outputValue = (float) (inputValue * speedArray[firstChoice-1][secChoice-1]);
	}
}

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("\nUNIT CONVERTER TOOL");
		startConverter();
	}

	private static void startConverter()
	{
		System.out.println("\n1. Currency Converter\t\t\t2. Length Converter\n" +
				"3. Temperature Converter\t\t4. Weight Converter\n" +
				"5. Speed Converter\t\t\t\t6. About This Tool\n" +
				"7. Exit This Tool\n");
		System.out.print("Enter Your Choice : ");
		takeChoice();
	}

	private static void takeChoice()
	{
		Scanner scan = new Scanner(System.in);
		try
		{
			int choice = scan.nextInt();
			createObject(choice);
			if (choice < 1 || choice > 7)
				throw new Exception();
		}
		catch (Exception e)
		{
			System.out.print("\nChoice Selection Error !!!\n" +
					"Please Enter Correct Integer Choice : ");
			takeChoice();
		}
	}

	private static void createObject(int i)
	{
		switch (i)
		{
			case 1:
				ConversionAbstract c = new Currency();
				converter(c,0,5);
				break;
			case 2:
				ConversionAbstract l = new Length();
				converter(l,1,7);
				break;
			case 3:
				ConversionAbstract t = new Temperature();
				converter(t,2,3);
				break;
			case 4:
				ConversionAbstract w = new Weight();
				converter(w,3,5);
				break;
			case 5:
				ConversionAbstract s = new Speed();
				converter(s,4,5);
				break;
			case 6:
				System.out.println("\nUnit Converter For OODS Project\n\n" +
						"Developed By :\n" +
						"61 Fulesh Chafale\n" +
						"62 Gaurav Daga\n" +
						"76 Shubhbam Das\n" +
						"79 Udit Mishra\n" +
						"84 Siddhant Mate\n\n" +
						"Guided by : Prof. Sunita G. Rawat");
				startConverter();
				break;
			case 7:
				System.out.println("\nThank You for using Unit Converter Tool");
				System.exit(0);
				break;
			default:
		}
	}

	private static void converter(ConversionAbstract o, int i, int j)
	{
		int a = 2;
		while (true)
		{
			switch (a)
			{
				case 2:
					o.conversionList();
					o.from(j);
					o.to(j);
				case 1:
					o.takeInput(i);
					o.calculate();
					o.printConversion(i);
					o.print();
					a = o.choice();
					break;
				case 3: startConverter();
					break;
				default:
					System.out.println("\nThank You for using Unit Converter Tool");
					System.exit(0);
					break;
			}
		}
	}
}