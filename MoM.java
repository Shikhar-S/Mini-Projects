import java.util.*;
class MoM
{
	static void swap(int []arr,int i,int j)
	{
		int t=arr[i];
		arr[i]=arr[j];
		arr[j]=t;
	}
	static int partition(int []arr,int left,int right,int pivotIndex) //divides the array based on pivot
	{
		int pivotValue=arr[pivotIndex];
		swap(arr,pivotIndex,right);
		int storeIndex=left;
		for(int i=left;i<right;i++)
		{
			if(arr[i]<pivotValue)
			{
				swap(arr,storeIndex,i);
				storeIndex++;
			}
		}
		swap(arr,right,storeIndex);
		return storeIndex;
	}
	static int pivot(int []arr,int left,int right) //divides into groups of 5 and is in mutual recursion with select
	{
		if(right-left<5)
		{
			return partition5(arr,left,right);
		}
		for(int i=left;i<=right;i+=5)
		{
			int subright=i+4;
			if(subright>right)
				subright=right;
			int m5=partition5(arr,i,subright);
			//System.out.println(i+ " "+ subright+" med-->"+m5);
			swap(arr,m5,left+(int)Math.floor((i-left)/5)); //shift the median values to the beginning of array
		}
		return select(arr,left,left+(int)Math.ceil((right-left)/5)-1,left+(right-left)/10); // work on the shifted values to find their median . Hence the name "Median of Medians"

	}
	static int partition5(int arr[],int left,int right)// finds median of a group of atmost 5 elements
	{
		int tarr[]=new int[5];
		int c=0;
		int mx;
		for(int j=left;j<=right;j++)
		tarr[c++]=arr[j];
		for(int j=c;j<5;j++)
			tarr[j]=Integer.MAX_VALUE;
		Arrays.sort(tarr); //just 5 elements so sorting won't affect the complexity of the algorithm.
		int m=tarr[c/2];
		for(int i=left;i<=right;i++)
			if(m==arr[i])
				return i;
		return -1;
	}
	static int select(int []arr,int left,int right,int k) //returns the index of kth statistic
	{
		while(true)
		{
			if(left==right)
			{
				return left;
			}
			int pivotIndex=pivot(arr,left,right);
			pivotIndex=partition(arr,left,right,pivotIndex);
			if(k==pivotIndex)
				return k;
			else if(k<pivotIndex)
				right=pivotIndex-1;
			else
				left=pivotIndex+1;
		}
	}
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of numbers to be processed.");
		int n;
		n=sc.nextInt();
		if(n<0)
		{
			System.out.println("N can not be less than zero. Quitting. Run again.");
			System.exit(0);
		}
		int arr[]=new int[n];
		System.out.println("Enter "+n+" numbers.");
		for(int i=0;i<n;i++)
			arr[i]=sc.nextInt();
		int x=0;
		if(n%2==1)
			x=select(arr,0,n-1,n/2);
		else
			x=select(arr,0,n-1,n/2-1);
		System.out.println(arr[x]);
	}
}