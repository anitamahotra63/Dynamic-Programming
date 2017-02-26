
package lect24;

public class dynamic_programming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.out.println(count_climb(6).num);
		//System.out.println(count_climb(6,new int[8]));
		//System.out.println(lcs("bnita","anuji",new int[6][6]));
		//System.out.println(edit_distance("ani","defijk"));
		
		//System.out.println(fibonacci_by_dp(90).num_m1);
		//System.out.println(fibonacci_bad_complexity(45));
		//System.out.println(fibonacci_by_storage(90,new int[91]));
		
		
		//System.out.println(count_climb_by_dp(90).num);
		//System.out.println(count_climb_iterative(90));
		//System.out.println(count_climb_by_storage(40,new int[41]));
		//System.out.println(count_climb_normal(40));
		
		System.out.println(power_normal(2,15));
		System.out.println(power_by_dp(2,15));
		System.out.println(power_iterative(2,15));
		System.out.println(power_by_storage(2,15,new int[16]));
		
	}
	
	private static class triplet{
		
		int num;
		int numM1;
		int numM2;
		
		public triplet(int num,int numM1,int numM2)
		{
			this.num=num;
			this.numM1=numM1;
			this.numM2=numM2;
		}
	}
	
	public static triplet count_climb(int num)
	{
		if(num==0)
		{
			return new triplet(0,0,0);
		}
		else if(num==1)
		{
			return new triplet(1,0,0);
		}
		else if(num==2)
		{
			return new triplet(2,1,0);
		}
		else if(num==3)
		{
			return new triplet(4,2,1);
		}
		
		triplet new_triplet=count_climb(num-1);
		triplet retval=new triplet(0,0,0);
		retval.num=new_triplet.num+new_triplet.numM1+new_triplet.numM2;
		retval.numM1=new_triplet.num;
		retval.numM2=new_triplet.numM1;
		
		return retval;
	}

	public static int count_climb(int num,int[] storage)
	{
		if(num==1)
		{
			storage[num]=1;
			return 1;
		}
		else if(num==2)
		{
			storage[num]=2;
			return 2;
		}
		else if(num==3)
		{
			storage[num]=3;
			return 4;
		}
		int retval=count_climb(num-1,storage)+count_climb(num-2,storage)+count_climb(num-3,storage);
		if(storage[num]==retval)
		{
			return retval;
		}
		else
		{
			storage[num]=retval;
			return retval;
		}
	}

	//count_climb_in iterative, do it.
	//fibonacci iterative-O(n)
	//count_climb iterative-O(n)
	
	public int lcs(String s1,String s2)
	{
		if(s1.length()==0 || s2.length()==0)
		{
			return 0;
		}
		int retval;
		if(s1.charAt(0)==s2.charAt(0))
		{
			retval=1+lcs(s1.substring(1),s2.substring(1));
			
		}
		else{
			int f1=lcs(s1,s2.substring(1));
			int f2=lcs(s1.substring(1),s2);
			retval=Math.max(f1, f2);
		}
		return retval;
	}

	public static int lcs(String s1,String s2,int[][] storage)			//improove this code
	{
		if(s1.length()==0 || s2.length()==0)
		{
			return 0;
		}
		int retval;	
		
		if(storage[s1.length()][s2.length()]!=0)
		{
			retval=storage[s1.length()][s2.length()];
		}
		else{
			if(s1.charAt(0)==s2.charAt(0))
			{
				retval=lcs(s1.substring(1),s2.substring(1),storage)+1;
			}
			else
			{
				int f1=lcs(s1.substring(1),s2,storage);
				int f2=lcs(s1,s2.substring(1),storage);
				retval=Math.max(f1,f2);
			}
		}
		
		return retval;

	}

	private static class string_triplet{
		int s1m1s2m1;
		int s1m1s2;
		int s1s2m1;
		
		public string_triplet(int s1m1s2m1,int s1m1s2,int s1s2m1)
		{
			this.s1m1s2m1=s1m1s2m1;
			this.s1m1s2=s1m1s2;
			this.s1s2m1=s1s2m1;
		}
		
	}
	
	/*public static string_triplet lcs_with_triplet(String s1,String s2)
	{
		
		
		
		
		
		string_triplet st=new string_triplet(0,0,0);
		st.s1m1s2=lcs_with_triplet(s1.substring(1),s2);
		
	}*/

	//string triplet by pair method,iterative
	//Edit distance
	
	public static int edit_distance(String s1,String s2)
	{
		if(s1.length()==0)
		{
			return s2.length()-s1.length();
			
		}
		else if(s2.length()==0)
		{
			return s1.length()-s2.length();
		}
		
		int retval;
		if(s1.charAt(0)==s2.charAt(0))
		{
			retval=edit_distance(s1.substring(1),s2.substring(1));
		}
		else
		{
			int cost_rp=1+edit_distance(s1.substring(1),s2.substring(1));
			int cost_rm=1+edit_distance(s1.substring(1),s2);
			int cost_ad=1+edit_distance(s1,s2.substring(1));
			
			retval=Math.min(cost_rp, Math.min(cost_rm, cost_ad));
		}
		
		return retval;
	}
	
	/*public static int edit_distance_iterative(String s1,String s2)
	{
		int retval;
		int cost_rp=0,cost_rm=0,cost_ad=0;
		for(int i=0;i<s1.length();i++)
		{
			for(int j=0;j<s2.length();j++)
			{
				if(s1.charAt(j)==s2.charAt(j))
				{
					s1=s1.substring(1);
					s2=s2.substring(1);
				}
				else
				{
					cost_rp+=1;
					//s1=s1.substring(1);
					//s2=s2.substring(1);
					cost_rm+=1;
					//s2
					//s1=s1.substring(1);
					cost_ad+=1;
					//s1
					//s2=s2.substring(1);
					
				}
			}
		}
		
	}*/
	//power program by all methods
	 
	/*FIBONACCI PROGRAM*/
	public static int fibonacci_bad_complexity(int num)
	{//here, call for the recursion function is apllied multiple times for the same no., therefore it is neccessary to 
		//apply the concept of dynamic programming.
		if(num==0)
		{
			return 0;
		}
		if(num==1)
		{
			return 1;
		}
		
		int fib=fibonacci_bad_complexity(num-1)+fibonacci_bad_complexity(num-2);
		
		return fib;
	}

	//fibonacci by the concept of dynamic programming
	//1.make a storage area where we can store the required result so as to reduce the unwanted calls for those no.,
	//whose answer we already know
	public static class fib_class
	{
		int num_m1;
		int num_m2;
		
		public fib_class(int num_m1,int num_m2)
		{
			this.num_m1=num_m1;
			this.num_m2=num_m2;
		}
	}

	public static fib_class fibonacci_by_dp(int num)
	{
		if(num==0)
		{
			return new fib_class(0,0);
		}
		if(num==1)
		{
			return new fib_class(1,0);
		}
		
		fib_class pair=fibonacci_by_dp(num-1);
		fib_class retval=new fib_class(0,0);
		retval.num_m1=pair.num_m1+pair.num_m2;
		retval.num_m2=pair.num_m1;
		return retval;
		
	}

	//fibonacci by the concept of storage
	//1.jiska fibonacci nikal liya hai, store karke rakh lo, and dobara mat nikalo, stored value me se hi access kar lo
	
	public static int fibonacci_by_storage(int num,int[] storage)
	{
		if(num==0)
		{
			storage[num]=num;
			return storage[num];
		}
		else if(num==1)
		{
			storage[num]=num;
			return storage[num];
		}
		int retval=0;
		if(storage[num]!=0)
		{
			retval=storage[num];
		}
		else
		{
			retval=fibonacci_by_storage(num-1,storage)+fibonacci_by_storage(num-2,storage);
			storage[num]=retval;
		}
		return retval;
		
	}
	
	/*COUNT CLIMB*/
	public static int count_climb_normal(int num)
	{
		if(num==0)
		{
			return 0;
		}
		else if(num==1)
		{
			return 1;
		}
		else if(num==2)
		{
			return 2;
		}
		else if(num==3)
		{
			return 4;
		}
		
		int retval;
		retval=count_climb_normal(num-1)+count_climb_normal(num-2)+count_climb_normal(num-3);
		return retval;
	}

	//by dp
	
	public static class count_climb_class
	{
		int num;
		int num_m1;
		int num_m2;
		
		public count_climb_class(int num,int num_m1,int num_m2)
		{
			this.num=num;
			this.num_m1=num_m1;
			this.num_m2=num_m2;
		}
		
	}

	public static count_climb_class count_climb_by_dp(int num)
	{
		if(num==0)
		{
			return new count_climb_class(0,0,0);
		}
		else if(num==1)
		{
			return new count_climb_class(1,0,0);
		}
		else if(num==2)
		{
			return new count_climb_class(2,1,0);
		}
		else if(num==3)
		{
			return new count_climb_class(4,2,1);
		}
		count_climb_class triplet=count_climb_by_dp(num-1);
		count_climb_class retval=new count_climb_class(0,0,0);
		retval.num=triplet.num+triplet.num_m1+triplet.num_m2;
		retval.num_m1=triplet.num;
		retval.num_m2=triplet.num_m1;
		
		return retval;
		
	}

	//by storage
	
	public static int count_climb_by_storage(int num,int[] storage)
	{
		if(num==0)
		{
			storage[num]=0;
			return 0;
		}
		else if(num==1)
		{
			storage[num]=1;
			return 1;
		}
		else if(num==2)
		{
			storage[num]=2;
			return 2;
		}
		else if(num==3)
		{
			storage[num]=4;
			return 4;
		}
		
		int retval=0;
		if(storage[num]!=0)
		{
			retval=storage[num];
		}
		else
		{
			retval=count_climb_by_storage(num-1,storage)+count_climb_by_storage(num-2,storage)+count_climb_by_storage(num-3,storage);
		}
		return retval;
	}


	public static int count_climb_iterative(int num)
	{//improove
		if(num==1)
		{
			return 1;
		}
		else if(num==2)
		{
			return 2;
		}
		else if(num==3)
		{
			return 4;
		}
		int num1=1;
		int num2=2;
		int num3=4;
		int count=4;
		while(count<=num)
		{
			int temp=num3;
			num3=num1+num2+num3;
			int  temp1=num2;
			num2=temp;
			num1=temp1;
			count++;
		}
		return num3;
	}

	/*POWER FUNCTION*/
	
	public static int power_normal(int x,int n)
	{
		if(n==1)
		{
			return x;
		}
		
		
		if(n%2==0)
		{
			int power=power_normal(x,n/2)*power_normal(x,n/2);
			return power;
		}
		else 
		{
			int power=power_normal(x,n/2)*power_normal(x,n/2)*x;
			return power;
		}
	
	}

	//by dp
	public static long power_by_dp(int x,int n)
	{
		if(n==1)
		{
			return x;
		}
		
		long power=power_by_dp(x,n/2);
		if(n%2==0)
		{
			power=power*power;
		}
		else if(n%2!=0)
		{
			power=power*power*x;
		}
		return power;
	}

	//by iteration
	public static long power_iterative(int x, int n)
	{
		long power=1;
		for(int i=1;i<=n;i++)
		{
			power*=x;
		}
		return power;
	}

	//by storage
	public static int power_by_storage(int x,int n,int[] storage)
	{
		if(n==1)
		{
			storage[n]=x;
			return x;
		}
		int retval;
		if(storage[n]!=0)
		{
			retval=storage[n];
			return retval;
		}
		else{
			if(n%2==0)
			{
				retval=power_by_storage(x,n/2,storage)*power_by_storage(x,n/2,storage);
			}
			else
			{
				retval=power_by_storage(x,n/2,storage)*power_by_storage(x,n/2,storage)*x;
			}
			return retval;
		}
		
		
		
	}

	/*LCS*/
	
	/*EDIT DISTANCE*/		
	

}
