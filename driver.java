import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

//Dale Heuser
public class driver 
{
	private static ArrayList<String> input;
	private static int i=0;
	private static boolean add=false;
	private static boolean mult=false;
	
	//this makes sure that the token is a number, and not something like 12#4
	public static boolean is_num(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	//this finds the item in the string or if it does not the string in not valid 
	//if it finds what it is looking for it consumes it from the string
	public static void find(String s)
	{
		if(s.equals(input.get(i)))
		{
			i++;
		}
		else
		{
			System.out.println("error");
			System.exit(1);
		}
	}
	
	public static int E()
	{
		switch(input.get(i))
		{	
			//add if it has 2 numbers returned to it else return what it gets from t
			case"(":
			{
				int t=T();
				int e_p=E_prime();
				if(t!=-1 && e_p!=-1 )
				{
					return t+e_p;
				}
				return t;
			}
			default:
			{
				if(is_num(input.get(i)))
				{
					int t=T();
					int e_p=E_prime();
					if(t!=-1 && e_p!=-1)
					{
						return t+e_p;
					}
					return t;
				}
				else
				{
					System.out.println("error");
					System.exit(1);
				}
				break;
			}
		}
		return -1;
	}
	
	public static int E_prime()
	{
		switch(input.get(i))
		{
			//add if it has 2 numbers returned to it else return what it gets from t
			case"+":
			{	
				find("+");
				int t=T();
				int e_p=E_prime();
				if(t!=-1 && e_p!=-1)
				{
					return t+e_p;
				}
				return t;
			}
			//nothing is done if the symbol goes to lambda 
			case")":
			{
				//do nothing
				break;
			}
			case"$":
			{
				//do nothing
				break;
			}
			default:
			{
				System.out.println("error");
				System.exit(0);
			}
		}
		return -1;
	}
	
	public static int T()
	{
		switch(input.get(i))
		{
			case"(":
			{
				// add if possible and if add is true and mult is false
				int f=F();
				int t_p=T_prime();
				if(f!=-1 && t_p!=-1 && add==true && mult==false)
				{
					add=false;
					return f+t_p;
				}
				//add if possible and mult is true to get here it means that add is false
				else if(f!=-1 && t_p!=-1 && mult==true)
				{
					mult=false;
					return f*t_p;
				}
				return f;
			}
			default:
			{
				//multiply if possible 
				if(is_num(input.get(i)))
				{
					int f=F();
					int t_p=T_prime();
					if(f!=-1 && t_p!=-1)
					{
						return f*t_p;
					}
					return f;
				}
				else
				{
					System.out.println("error");
					System.exit(1);
				}
				break;
			}
		}
		return -1;
	}
	
	public static int T_prime()
	{
		switch(input.get(i))
		{
			//will add later so set add to true
			case"+":
			{
				//do nothing
				add=true;
				break;
			}
			//will multiply later so set mult to true
			case"*":
			{
				find("*");
				mult=true;
				int f=F();
				int t_p=T_prime();
				if(f!=-1 && t_p!=-1)
				{
					return f*t_p;
				}
				return f;
			}
			//do nothing for lambda 
			case")":
			{
				//do nothing
				break;
			}
			case"$":
			{
				//do nothing
				break;
			}
			default:
			{
				System.out.println("error");
				System.exit(1);
			}
		}
		return -1;
	}
	
	public static int F()
	{
		switch(input.get(i))
		{
			//get the ( and ) return what E() gives
			case"(":
			{
				find("(");
				int e=E();
				find(")");
				return e;
			}
			//found int so return it
			default:
			{
				if(is_num(input.get(i)))
				{
					i++;
					return Integer.parseInt(input.get(i-1)); 
				}
				else
				{
					System.out.println("error");
					System.exit(1);
				}
				break;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) 
	{
		//Scanner scan = new Scanner(System.in);
		//System.out.println("enter the string");
		//String test=scan.nextLine();
		String test = args[0];
		
	    input = new ArrayList<String>();
	   
		StringTokenizer st = new StringTokenizer(test, "(+)*", true);
	    
		while (st.hasMoreTokens()) 
	    {
	        input.add(st.nextToken());	
	    }
		//add in the $ so user does not have to
		input.add("$");
		//start the parser 
		int ans=E();
		//did not consume the whole string so it is invalid 
		if(i==input.size()-1)
		{
			System.out.println(ans);
		}
		else
		{
			System.out.println("error");
		}
	}
}
