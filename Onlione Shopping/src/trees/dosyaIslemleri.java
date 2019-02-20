/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;


public class dosyaIslemleri {
    private String dosyaAdi ;
    public dosyaIslemleri(String dosyaAdi)
    {
        this.dosyaAdi=dosyaAdi;
    }
     public Scanner oku()            //   rehber.txt dosyasini okur.
   {           
        Scanner okuma = null;
        
      try {   
        okuma = new Scanner(new FileInputStream(dosyaAdi));
         } 
      catch (FileNotFoundException i) {
             
        System.err.println("Dosya bulunamadi");
        System.exit(0);        
        }
      return okuma ;  
   }  
   public void kapa(Scanner okuma)   // rehber.txt yi kapatir.
   {    
       okuma.close();
   }
  public int satirSayisi()               // Okunacak dosyadaki satir sayisini dondurur.
   {
        int sayac = 0;
        Scanner okuma = null;
        try {
            okuma = new Scanner(new FileInputStream(dosyaAdi));
        }
        catch (FileNotFoundException i) {
            System.err.println("Dosya bulunamadi");
            System.exit(0);
        }   
        while (okuma.hasNextLine())    
        {   
            String str = okuma.nextLine();
            sayac++ ;
        }
    
       return sayac ;       
        
   }
  public String satirOku(Scanner okuma)       // Dosyadan bir satiri okur.
    {
        String a = okuma.nextLine();
        return a;
    }       
   public void veriEkle(String veri)        // Bu methot dosyaya bir veri eklemek icindir.Guncelleme yapar dosyanin uzerine yazmaz.
   {    
       try{
             File dosya = new File(dosyaAdi);
             FileWriter yazici = new FileWriter(dosya,true);
             BufferedWriter yaz = new BufferedWriter(yazici);
             yaz.write(veri);
             yaz.close();
             
            }
         catch (Exception hata){
              hata.printStackTrace();
            }
   }
   public void dosyayiBosalt()           // Dosyayi bosaltir.
    {
        try{
             File dosya = new File(dosyaAdi);
             FileWriter yazici = new FileWriter(dosya);
             BufferedWriter yaz = new BufferedWriter(yazici);
             yaz.write("");             
             yaz.close();
            }
         catch (Exception hata){
              hata.printStackTrace();
            }
    }
   public void yeniSatir()              // Veri ekle methoduyla sinerjisi vardir.Basitce dosyada alt satira gecilir.
    {
        try{
             File dosya = new File(dosyaAdi);
             FileWriter yazici = new FileWriter(dosya,true);
             BufferedWriter yaz = new BufferedWriter(yazici);
             yaz.newLine();
             yaz.close();
             
            }
         catch (Exception hata){
              hata.printStackTrace();
            }
        
    }
 
   }

