import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile; 
import java.lang.NumberFormatException; 
import java.io.FileNotFoundException;
import java.util.Scanner;
class Employee
{
	protected int emp_ID;
	protected String emp_name;
    public Employee(String emp_name,int emp_ID)
	{
		this.emp_name=emp_name;
		this.emp_ID=emp_ID;
		
	}
}
class Programmer extends Employee
{
	public Programmer(String emp_name,int emp_ID)
	{
		super(emp_name,emp_ID);
		
	}
	void addFriend()
	{
		try{File file2=new File("programmer.txt");
		if(!file2.exists()) {file2.createNewFile();}
		RandomAccessFile raf= new RandomAccessFile(file2, "rw"); 
		boolean found = false;
		String Name,name;
		int index;  // name!id
		while (raf.getFilePointer() < raf.length())
		{
			name=raf.readLine();   // abcd!123
			index = name.indexOf('!');
			Name=name.substring(0,index);
			String emp_id;
			emp_id=name.substring(index+1);
			if (emp_id.equals(String.valueOf(emp_ID))) { 
                    found = true; 
                    break; 
			 }
		}
		if (found == false) { 
			    raf.writeBytes(emp_name+'!'+emp_ID);
				raf.writeBytes(System.lineSeparator()); 
				System.out.println("record added");
					raf.close();
			 }

		}catch (IOException ioe) { 
  
            System.out.println(ioe); 
        } 
        catch (NumberFormatException nef) { 
  
            System.out.println(nef); 
        } 	
	}
	public static void viewRecord()
	{
			String nameNumberString; 
			int index; 
			String name=new String();
			String emp_id=new String();
			System.out.println("---------------------");
			System.out.println("ID            name   ");
			System.out.println("---------------------");
			try{RandomAccessFile raf = new RandomAccessFile("programmer.txt", "rw"); 
			while (raf.getFilePointer() < raf.length())
			{
				nameNumberString = raf.readLine(); 
				//System.out.println(nameNumberString);
				index = nameNumberString.indexOf('!');
				name = nameNumberString.substring(0, index); 				
				emp_id=nameNumberString.substring(index+1);
				System.out.println(emp_id+"            "+name);		
				 
			}
	        }
	        catch (FileNotFoundException e){
			System.out.println("error occurred");
			e.printStackTrace();
		}catch (IOException f){
			System.out.println("an error occurred");
			f.printStackTrace();
		}catch (StringIndexOutOfBoundsException siobe){
			System.out.println("");
	
		}

	}
	void deleteRecord(String w)
	{
		String emp_id;
		try{
			File file2=new File("programmer.txt");
			if(!file2.exists())
				file2.createNewFile();
			RandomAccessFile raf= new RandomAccessFile(file2, "rw"); 
            boolean found = false;
			String Name,namestring;
			int index;
			while (raf.getFilePointer() < raf.length()) { 
				namestring=raf.readLine();
				index = namestring.indexOf('!'); 
				//index2=name.lastIndexOf('!');
				Name=namestring.substring(0,index);
				emp_id=String.valueOf(emp_ID);
				emp_id=namestring.substring(index+1);
				if (emp_id.equals(String.valueOf(w))) { 
                    found = true; 
                    break; 
			 }
			}
			if(found==true)
			{
				File tmpfile=new File("tmp.txt");
				RandomAccessFile tmpraf = new RandomAccessFile(tmpfile, "rw"); 
					raf.seek(0);
					while (raf.getFilePointer() < raf.length()) {
						namestring=raf.readLine();
						index = namestring.indexOf('!'); 
						Name=namestring.substring(0,index);
						emp_id=String.valueOf(emp_ID);
							emp_id=namestring.substring(index+1);
							if(emp_id.equals(w)){
								continue;
							}
						tmpraf.writeBytes(namestring);
						tmpraf.writeBytes(System.lineSeparator());
					}
					raf.seek(0);
					tmpraf.seek(0);
					while(tmpraf.getFilePointer()<tmpraf.length()){
						raf.writeBytes(tmpraf.readLine());
						raf.writeBytes(System.lineSeparator());
					}
					raf.setLength(tmpraf.length());
					tmpraf.close(); 
					raf.close();
					tmpfile.delete(); 
					System.out.println(" Friend deleted. "); 
			}
			else
			{
				raf.close();
				System.out.println(" Input name"+ " does not exists. ");
			}
		}catch (IOException ioe) { 
			System.out.println(ioe); 
		} 

	}
	void update(String w)
	{		System.out.println("enter the name you want to update");
			Scanner q=new Scanner(System.in);
			String s=q.next();
			String emp_id;
		try{  File file2=new File("programmer.txt");
				if(!file2.exists())
				file2.createNewFile();
			RandomAccessFile raf= new RandomAccessFile(file2, "rw"); 
            boolean found = false;
				String Name,name;
				int index;
			while (raf.getFilePointer() < raf.length()) { 
				name=raf.readLine();
				index = name.indexOf('!'); 
				//index2=name.lastIndexOf('!');
				Name=name.substring(0,index);
			
				emp_id=String.valueOf(emp_ID);
				emp_id=name.substring(index+1);
				if (emp_id.equals(String.valueOf(w))) { 
                    	found = true; 
                    	break; 
			 		}
			}
			if(found==true)
			{  
					 String namestring;
				
				File tmpfile=new File("tmp.txt");
				RandomAccessFile tmpraf 
					= new RandomAccessFile(tmpfile, "rw"); 
					raf.seek(0);
					while(raf.getFilePointer()<raf.length()){
						
						namestring=raf.readLine();
						index = namestring.indexOf('!'); 
						Name=namestring.substring(0,index);
						emp_id=String.valueOf(emp_ID);
							emp_id=namestring.substring(index+1);
							if(emp_id.equals(w))
							{
								namestring=s+'!'+emp_id;
							}
							tmpraf.writeBytes(namestring);
					tmpraf.writeBytes(System.lineSeparator());
					}
					raf.seek(0);
					tmpraf.seek(0);
					while(tmpraf.getFilePointer()<tmpraf.length()){
						raf.writeBytes(tmpraf.readLine());
						raf.writeBytes(System.lineSeparator());
					}
					raf.setLength(tmpraf.length());
					tmpraf.close(); 
					raf.close();
					tmpfile.delete(); 
					System.out.println(" Friend updated. "); 
			}
			else
			{
				raf.close();
				System.out.println(" Input name"
								+ " does not exists. ");
			}
	       }catch (IOException ioe) { 
			System.out.println(ioe); 
		} 
	
	}

}
class ems
{
	public static void main(String args[])
	{	
		Scanner q=new Scanner(System.in);
		String name=new String();
		int id;
		int x,d=1;
		do{
			    System.out.println("1 ===> Add New Employee Record");
				System.out.println("2 ===> View All Employee Record ");	
				System.out.println("3 ===> Delete Employee Record ");
				System.out.println("4 ===> Update Specific Record ");
				System.out.println("enter your choice");
				x=q.nextInt();
				switch(x)
				{
				case 1:
					System.out.println("are you programmer or manager");
					String s=q.next();
					if(s.equals("programmer"))
					{
								System.out.println("enter your name");
								name=q.next();
								System.out.println("enter your id");
								id=q.nextInt();
								Programmer p=new Programmer(name,id);	
								p.addFriend();
					}
					else{
								System.out.println("enter your name");
								name=q.next();
								System.out.println("enter your id");
								id=q.nextInt();
								//manager m=new manager(name,id);	
								//m.addFriend();
					}
					break;
				case 2:
					System.out.println("are you programmer/ manager");
					s=q.next();
					if(s.equals("programmer"))
					{
						Programmer.viewRecord();
					}
					else
					{
						//manager.viewRecord();
					}
					break;

			case 3:
				System.out.println("are you programmer/manager");
				s=q.next();
				if(s.equals("programmer"))
				{
					System.out.println("enter your name");
					name=q.next();
					System.out.println("enter your id");
					id=q.nextInt();
					Programmer ps=new Programmer(name,id);
					System.out.println("enter the id you want to delete");
					String w=q.next();
					ps.deleteRecord(w);
				}
				else{
					System.out.println("enter your name");
					name=q.next();
					System.out.println("enter your id");
					id=q.nextInt();
					//manager ms=new manager(name,id);
					System.out.println("enter the id you want to delete");
					String w=q.next();
					//ms.deleteRecord(w);
				}
				break;
			case 4:
				System.out.println("are you programmer/manager");
				s=q.next();
				if(s.equals("programmer"))
				{System.out.println("enter your name");
				name=q.next();
				System.out.println("enter your id");
				id=q.nextInt();
				Programmer psa=new Programmer(name,id);
				System.out.println("enter the id you want to update");
				String o=q.next();
				psa.update(o);
				}
				else{
				System.out.println("enter your name");
				name=q.next();
				System.out.println("enter your id");
				id=q.nextInt();
				//manager msa=new manager(name,id);
				System.out.println("enter the id you want to update");
				String o=q.next();
				//msa.update(o);
				}
				break;
				}
			System.out.println("Do you want to continue? Y->1/N->0");
			d=q.nextInt();
				
		}while(d==1);

	}
}