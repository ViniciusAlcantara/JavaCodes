
public class ThreadSimples extends Thread{
	public ThreadSimples(String str)
	{
		super(str);
	}
	public void run()
	{
		int contA =0, contB=0;
		for(int i=0;i<100;i++)
		{
			if(getName().equals("Santos"))
			{
				contA++;
			}else{
				contB++;
			}
			System.out.println(i+" "+getName());
			try{
				sleep((long)(Math.random()*400));			
			}catch(InterruptedException e){
				
			}
		}
		System.out.println("TERMINOU "+getName()+"!");
		System.out.println("quantidade de Santos = " +contA + "\nquantidade de São Paulo = " +contB);
	}
}
