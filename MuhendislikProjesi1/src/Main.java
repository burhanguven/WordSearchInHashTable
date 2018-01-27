
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public void HashAtma() throws IOException
	{
		String kelime[]=new String[50];
		int hash[]=new int[100];
		int ascii[]=new int[50];
		int geciciTop[]=new int[50];
		int strOku=0;
		int top;
		int mod;
		int crp=1;
		
		File file=new File("kelime.txt");
		BufferedReader reader =new BufferedReader(new FileReader(file));
		String satir = reader.readLine();
		
		while(satir!=null)
		{
			kelime[strOku]=satir;
			satir=reader.readLine();
			strOku++;
		}
	
		for(int i=0; i<kelime.length; i++)
		{
			//System.out.println(kelime[i]);
			String str;
	    	str=kelime[i];
	    	mod=0;
	    	top=0;
	    	char parcala[]=str.toCharArray();
	    	for(int j=1; j<=parcala.length; j++)
	    	{
	    		top+=parcala[j-1]*j*j;
	    		//System.out.println(parcala[j-1]);
	    	}
	    	mod=top%101;
	    	geciciTop[i]=top;
	    	top=0;
	    		//System.out.println(geciciTop[i]);
	    	if(mod>=100)
			{
				mod=mod-100;
			}
	    	if(hash[mod]==0)
	    	{
	    		hash[mod]=geciciTop[i];
	    	}
	    	else
	    	{
	    		for(int q=0; q<hash.length; q++)
	    		{
	    			int gecici=0;
		    		gecici=(mod+(q+1)*(q+1))%101;
		    			
	    			if(gecici>=100)
    				{
	    				gecici=gecici-100;
    				}
	    			if(hash[gecici]==0)
	    			{
	    				hash[gecici]=geciciTop[i];
	    				break;
	    								
	   			}    					    				
	    		}
	    	}
		}
		 for(int a=0; a<hash.length; a++)
		   {
		    	//System.out.println(hash[a]);
		   }
		 DegerOkuma(hash);
		
	  }
	
	public void DegerOkuma(int hash1[]) //kalavyeden girilen deðeri ascii ye çevirme fonk.
	{
		int arananTop=0;
		int arananMod=0;
		Scanner scn=new Scanner(System.in);
		System.out.print("Aranacak kelimeyi giriniz: ");
		String kelime=scn.nextLine();
		//System.out.println(deger);

		char arananParcala[]=kelime.toCharArray();
    	for(int j=1; j<=arananParcala.length; j++)
    	{
    		arananTop+=arananParcala[j-1]*j*j;
    		//System.out.println(arananParcala[j-1]);
    	}
    	arananMod=arananTop%101;
		//System.out.println("modum: "+ arananMod);
		//System.out.println("ascii toplam: "+ arananTop);
    	
    	//ARADIGIMIZ MOD HASH IN BOYUTUNDAN BUYUKSE MOD U DÝZÝ BOYUTUNDAN CIKARTIRIZ
    	if(arananMod>=100)
    	{
    		arananMod=arananMod-100;
    	}
    	//arananMod hash1 DE VARSA DIZIDE BULUNDU DIYE EKRANA BASAR.
    	if(hash1[arananMod]==arananTop)
        {
        	System.out.println("Aranan kelime "+kelime+" text de bulundu.");
        }
    	//EGER ARANAN DEGER TEXT DOSYAMIZDA YOK ISE KARESÝNÝ ALMA ISLEMÝMÝZÝ UYGULAYACAGIZ.
    	else
    	{	
    		for(int i=0; i<hash1.length; i++)
    		{
    			int arananGecici=0;
	    		arananGecici=(arananMod+(i+1)*(i+1))%101;
	    		
	    		if(arananGecici>=100)
				{
    				arananGecici=arananGecici-100;
				}
    			if(hash1[arananGecici]==arananTop)
    			{
    				System.out.println("Aranan kelime "+kelime+" text de bulundu.");
    				arananMod=arananGecici;
    				break;				
    			}  
    			
    		}
    		//ARADIGIMIZ DEGER DOSYADA YOKSA YOK DIYE EKRANA BASACAK.
    		
    		
    	}
    	if(hash1[arananMod]!=arananTop)
		{
    		System.out.println("Aranan deger dosyada yok silerek ve yer deðiþtirerek bakýlacak: ");
			silerekArama(hash1, kelime);
			yerdegistirerekArama(hash1,kelime);
		}
    	
	}
	public void silerekArama(int hash2[],String kelime2)
	{
		int silerekTop=0;
		int arananMod=0;
		int silerekGecici=0;
		int arttir;
		String bulnanKelime="";
		int bulundu=0;
		
		
		char arananParcala[]=kelime2.toCharArray();
    	
		char geciciParcala[]=new char[arananParcala.length];
		
		for(int j=0; j<arananParcala.length; j++)
    	{	
    		arttir=1;
    		for(int h=0; h<arananParcala.length; h++ )
			{
				geciciParcala[h]=arananParcala[h];
			}
    		char[] geciciHarf=new char[1];
    		geciciHarf[0]=geciciParcala[j];
    		geciciParcala[j]='0';
    		bulnanKelime="";
    		silerekTop=0;
    		
    		for(int i=0; i<arananParcala.length; i++)
    		{
    			if(geciciParcala[i]!='0')
    			{
    				silerekTop=silerekTop+geciciParcala[i]*(arttir)*(arttir);
    				//SILEREK ARAMDA ÝLK ELENI 0 LAYIP GERÝ KALANI KONTROL ETMEK ÝÇÝN.
    				bulnanKelime+=Character.toString(geciciParcala[i]);
    				arttir++;
    			} 			
    		}	
    		arananMod=silerekTop%101;
    		
    		if(arananMod>=100)
        	{
        		arananMod=arananMod-100;
        	}
    		if(hash2[arananMod]==silerekTop)
    		{
    			System.out.println("Aranan deðer silinerek bulundu: "+bulnanKelime);
    			bulundu=1; 
    		}
    		else
    		{
    			for(int a=0; a<hash2.length; a++)
    			{
    				int geciciAranan=0;
    				geciciAranan=(arananMod+(a+1)*(a+1))%101;
    				
    				if(geciciAranan>=100)
    				{
    					geciciAranan=geciciAranan-100;
    				}
        			if(hash2[geciciAranan]==silerekTop)
        			{
        				System.out.println("Aranan deger silinerek bulundu: "+bulnanKelime);
        				arananMod=geciciAranan;
        				bulundu=1;
        			}  
        		}
    		 }
    		//arananParcala[j]=geciciHarf[0];
    	}
    	if(bulundu==0)
    	{
    		System.out.println("Aranan deðer silinerek bulunamadý!!!");
    	}
    	
	}
	public void yerdegistirerekArama(int hash3[],String kelime3)
	{
		int yerdegistirerekTop=0;
		int arananMod=0;
		int yerdegistirerekGecici=0;
		int arttir;
		int bulundu=0;
		int arttir2;
		
		
		char yerdegistirerekParcala[]=kelime3.toCharArray();
		
		char[] temp=new char[1];
		char[] geciciKelime=new char[yerdegistirerekParcala.length];
		
		  
			
			
			for(int j=0; j<geciciKelime.length; j++)
			{
				geciciKelime[j]=yerdegistirerekParcala[j];
			}
			for(int k=0; k<geciciKelime.length; k++)
			{
				int sahte=0;
				arttir2=1;
				sahte=k+1;
				if(sahte<geciciKelime.length)
				{
					temp[0]=geciciKelime[k];
					geciciKelime[k]=geciciKelime[k+1];
					geciciKelime[k+1]=temp[0];
					
					yerdegistirerekTop=0;
					arttir2=1;
					for(int s=0; s<geciciKelime.length; s++)
					{
						yerdegistirerekTop+=geciciKelime[s]*arttir2*arttir2;
						arttir2++;
					}
					arananMod=yerdegistirerekTop%101;
					if(arananMod>=100)
			    	{
			    		arananMod=arananMod-100;
			    	}
			    	//arananMod hash1 DE VARSA DIZIDE BULUNDU DIYE EKRANA BASAR.
			    	if(hash3[arananMod]==yerdegistirerekTop)
			        {
			        	System.out.print("Aranan kelime yer deðitirerek bulundu: ");
			        	for(int b=0; b<geciciKelime.length; b++)
			        	{
			        		System.out.print(geciciKelime[b]);
			        	}
			        	System.out.println("");
			        	bulundu=1;
			        	yerdegistirerekTop=0;
			        	arttir2=1;
			        }
			    	else
			    	{
			    		for(int q=0; q<hash3.length; q++)
			    		{
			    			int arananGecici=0;
				    		arananGecici=(arananMod+(q+1)*(q+1))%101;
				    		
				    		if(arananGecici>=100)
							{
			    				arananGecici=arananGecici-100;
							}
			    			if(hash3[arananGecici]==yerdegistirerekTop)
			    			{
			    				System.out.print("Aranan kelime yer deðitirerek bulundu: ");
			    				//aranan kelime yer deðiþtirerek bulunduðunda
			    				for(int b=0; b<geciciKelime.length; b++)
					        	{
					        		System.out.print(geciciKelime[b]);
					        	}
			    				System.out.println("");
			    				arananMod=arananGecici;
			    				bulundu=1;	
			    				arttir2=1;			  
			    			}
			    			
			    	   	}  
			    	}
				}
				
				for(int x=0; x<yerdegistirerekParcala.length; x++)
				{
					geciciKelime[x]=yerdegistirerekParcala[x];
				}
				
			}
	
		if(bulundu==0)
    	{
    		System.out.println("Aranan deðer yerdeðiþtirerek bulunamadý!!!");
    	}
	}

	public static void main(String[] args) throws IOException {
		
	    Main deneme=new Main();
	    deneme.HashAtma();
	    Main deneme2=new Main();
	    deneme2.HashAtma();
   }
}
