/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ORCUN
 */
public class Personel {
    private static List bulunanUrun= new ArrayList();
    private static int personelSifre;
    private static double gelirler;
    private static double giderler;
    public Personel()
    {
        this.personelSifre =0000;
       
    }
    public void urunEkle()  
    {
        Scanner klavye = new Scanner(System.in);
        List urunBilgileri = new ArrayList();
        System.out.println("Infos for the new product ;");
        System.out.print("Category(Must be start with capital letters) :");
        String kategori = klavye.next();
        System.out.print("Product Name :");
        String urunAd  = klavye.next();
        System.out.print("Brand :");
        String marka = klavye.next();
        System.out.print("Model :");
        String model = klavye.next();
        System.out.print("Number of Products :");
        int miktar = klavye.nextInt();
        System.out.print("Cost :");
        int maliyet = klavye.nextInt();
        System.out.print("Sale Price :");
        int satisFiyati  = klavye.nextInt();
        System.out.print("Product info(Put '-' between each word! ) :");
        String urunAciklamasi = klavye.next();
        urunBilgileri.add(marka); urunBilgileri.add(model); urunBilgileri.add(miktar);                                    
        urunBilgileri.add(maliyet);  urunBilgileri.add(satisFiyati); urunBilgileri.add(urunAciklamasi);
        if(main.getHashTablosu().containsKey(kategori))
        {
            main.getHashTablosu().get(kategori).NodeEkle(urunAd, urunBilgileri);
        }
        
        else
        {
            tree tr = new tree (kategori);
            main.getHashTablosu().put(kategori, tr);
            main.getHashTablosu().get(kategori).NodeEkle(urunAd, urunBilgileri);
            main.getKategoriler().add(kategori);   // kategorilerin bulundugu listede guncelleniyor.
        }
        
    }
    public List urunBul(String ad,String marka,String model)   // Ad marka modelden urunu bulur ve Liste halinde dondurur.
    {   
        bulunanUrun.clear();
        List urun = new ArrayList() ;
        for(String kategori : main.getKategoriler())
        {
            main.getHashTablosu().get(kategori).urunBul(main.getHashTablosu().get(kategori).getRoot(), ad, marka, model);
        }
        if(bulunanUrun.size()!=0)   
            return (List)bulunanUrun.get(0) ; 
        else
            return null;
        
    }

    public void urunBilgileriDegistir()
    {
        Scanner klavye = new Scanner(System.in);
        System.out.println("Enter the product info that you want to update.");
        System.out.print("Name :");
        String urunAd = klavye.next();
        System.out.println();
        System.out.print("Brand :");
        String marka = klavye.next();
        System.out.println();
        System.out.print("Model :");
        String model = klavye.next();
        System.out.println();
        for(String kategori : main.getKategoriler())  // Kategori bilinmedigi icin butun urun bulunana kadar butun kategoriler geziliyor.
        {
            main.getHashTablosu().get(kategori).urunBilgileriDegistir(main.getHashTablosu().get(kategori).getRoot(),urunAd,marka,model);
        }
    }
    public static void sirketHarcamalari()
    {
        System.out.println("Financial Records :");
        System.out.println("Incomes  :"+gelirler);
        System.out.println("Expenses   :"+giderler);
        System.out.println("Total Profit :"+(gelirler-giderler));
      
    }
    public static void urunSil(String urunAd,String marka,String model)
    {
        for(String kategori : main.getKategoriler())
        {
            main.getHashTablosu().get(kategori).urunSil(main.getHashTablosu().get(kategori).getRoot(), urunAd, marka, model);
        }
    }
    public static List getBulunanUrun() {
        return bulunanUrun;
    }

    /**
     * @param aBulunanUrun the bulunanUrun to set
     */
    public static void setBulunanUrun(List aBulunanUrun) {
        bulunanUrun = aBulunanUrun;
    }

    /**
     * @return the personelSifre
     */
    public static int getPersonelSifre() {
        return personelSifre;
    }

    /**
     * @param aPersonelSifre the personelSifre to set
     */
    public static void setPersonelSifre(int aPersonelSifre) {
        personelSifre = aPersonelSifre;
    }

    /**
     * @return the gelirler
     */
    public static double getGelirler() {
        return gelirler;
    }

    /**
     * @param aGelirler the gelirler to set
     */
    public static void setGelirler(double aGelirler) {
        gelirler += aGelirler;
    }

    /**
     * @return the giderler
     */
    public static double getGiderler() {
        return giderler;
    }

    /**
     * @param aGiderler the giderler to set
     */
    public static void setGiderler(double aGiderler) {
        giderler += aGiderler;
    }

    

   
          
}
