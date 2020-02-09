package epamgift.EpamHT2;

import java.util.*;
public class Gift_Analysis 
{
	static Scanner sc=new Scanner(System.in);
	static ArrayList<Choclate_Analysis> chocolates=new ArrayList<Choclate_Analysis>();
	static ArrayList<Choclate_Analysis> candies=new ArrayList<Choclate_Analysis>();
	static ArrayList<Sweet_Analysis> sweets=new ArrayList<Sweet_Analysis>();
	static HashMap<String,Integer> nameToWeight=new HashMap<String,Integer>();
	static HashMap<String,Integer> nameToPrice=new HashMap<String,Integer>();
	static int sweetWeight,sweetPrice;
	public static void main(String[] args)
	{
		inputChocolates();
		inputSweets();
		System.out.println("Total weight of the gift is:"+calcTotalWeight());
		System.out.println("Choose the way to sort(enter the number): 1. By Price 2. By Weight");
		int sortType=sc.nextInt();
		if(sortType==1)
		{
			Comparator<Choclate_Analysis> compareByPrice=(Choclate_Analysis c1,Choclate_Analysis c2)->((Integer)c1.getPrice()).compareTo(c2.getPrice());
			Collections.sort(chocolates,compareByPrice);
		}
		else
		{
			Comparator<Choclate_Analysis> compareByWeight=(Choclate_Analysis c1,Choclate_Analysis c2)->((Integer)c1.getWeight()).compareTo(c2.getWeight());
			Collections.sort(chocolates,compareByWeight);
		}
		System.out.println("The sort result:");
		for(Choclate_Analysis chocolate: chocolates)
		{
			System.out.println(chocolate.getPrice());
		}
		calcRange(sortType);
	}
	
	public static void inputSweets()
	{
		
		System.out.println("Enter the number of sweets in gifts:\n");
		int numberOfSweets=sc.nextInt();
		for(int sweetCount=1; sweetCount<=numberOfSweets;sweetCount++)
		{
			System.out.println("Enter the weight of the sweet");
			int sweetWeight=sc.nextInt();
			System.out.println("Enter the price of the sweet");
			int sweetPrice=sc.nextInt();
			Sweet_Analysis sweet=new Sweet_Analysis(sweetWeight,sweetPrice);
			sweets.add(sweet);
		}
	}
	public static int calcTotalWeight()
	{
		int totalWeight=0;
		for(Choclate_Analysis choco: chocolates)
		{
			totalWeight+=choco.getWeight();
		}
		for(Sweet_Analysis sweet:sweets)
		{
			totalWeight+=sweet.getWeight();
		}
		return totalWeight;
	}
	public static void inputChocolates()
	{
		System.out.println("Enter the number of choclates in gifts:\n");
		int numberOfChocolates=sc.nextInt();
		for(int chocoCount=1; chocoCount<=numberOfChocolates;chocoCount++)
		{
			System.out.println("Enter the choclate type(Enter the number): 1. Candy 2.Wafer");
			int chocolateType=sc.nextInt();
			System.out.println("Enter the weight of the choclate");
			int chocoWeight=sc.nextInt();
			System.out.println("Enter the price of the chocolate");
			int chocoPrice=sc.nextInt();
			if(chocolateType==1)
			{
				System.out.println("Enter the name of the candy");
				String candyName=sc.next();
				if(nameToWeight.containsKey(candyName))
				{
					nameToWeight.put(candyName,(int)nameToWeight.get(candyName)+chocoWeight);
				}
				else 
					nameToWeight.put(candyName,chocoWeight);
				if(nameToPrice.containsKey(candyName))
				{
					nameToPrice.put(candyName,(int)nameToPrice.get(candyName)+chocoPrice);
				}
				else
					nameToPrice.put(candyName,chocoPrice);
			}
			Choclate_Analysis choco =new Choclate_Analysis();
			chocolates.add(choco);
			if(chocolateType==1)
			{
				candies.add(choco);
			}
		}
	}
	public static void calcRange(int sortType)
	{
		Scanner sc=new Scanner(System.in);
	   System.out.println("Let's find the range:");
	   int lowerLimit,higherLimit;
	   if(sortType==1)
	   {
		   System.out.println("Enter the lowerlimit of the price:");
		   lowerLimit=sc.nextInt();
		   
		   System.out.println("Enter the higherlimit of the price");
		   higherLimit=sc.nextInt();
		   
		   Set<Map.Entry<String,Integer>> candySet=nameToPrice.entrySet();
		   Iterator<Map.Entry<String,Integer>> candyIterator=candySet.iterator();
		   while(candyIterator.hasNext())
		   {
			   Map.Entry candyElement=(Map.Entry)candyIterator.next();
			   int currentPrice=(int)candyElement.getValue();
			   if(currentPrice>= lowerLimit && currentPrice<=higherLimit)
				   System.out.println(candyElement.getKey());
		   }
	   }
	   else
	   {
		   System.out.println("Enter the lowerlimit of the weight:");
		   lowerLimit=sc.nextInt();
		   System.out.println("Enter the higherlimit of the weight:");
		   higherLimit=sc.nextInt();
		   Set<Map.Entry<String,Integer>> candySet=nameToWeight.entrySet();
		   Iterator<Map.Entry<String,Integer>> candyIterator=candySet.iterator();
		   while(candyIterator.hasNext())
		   {
			   Map.Entry candyElement=(Map.Entry)candyIterator.next();
			   int currentWeight=(int)candyElement.getValue();
			   if(currentWeight>= lowerLimit && currentWeight<=higherLimit)
				   System.out.println(candyElement.getKey());
		   }
		   
	   }
	}

}
