/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author ORCUN
 */
public class Musteri {
    private String adSoyad ;
    private double  maas ;
    private String cinsiyet ;
    private String medeniDurum;
    private int sifre ;
    private static List<List> sepet = new ArrayList<List>();
    private static List<List> geciciSepet = new ArrayList<List>();
    private static List<List> oncekiSepetler = new ArrayList<List>();
    private static PriorityQueue<Double> q1=new PriorityQueue<Double>();
    
    public Musteri()
    {
        try
        {
            Scanner klavye = new Scanner(System.in);
            System.out.println("Please fill in the fields for complete your registration .");
            System.out.print("Name and Surname :");
            this.adSoyad = klavye.next();
            System.out.println("");
            System.out.print("Salary(Simply enter zero if you dont work) :");
            this.maas = klavye.nextDouble();
            System.out.println("");
            System.out.print("Gender (Male = e ,Female = k) :");
            this.cinsiyet = klavye.next();
            System.out.println("");
            System.out.print("Marital status (married = e , single=b) :");
            this.medeniDurum=klavye.next();
            System.out.println("");
            System.out.print("Create your password(only numbers ) :");
            this.sifre=klavye.nextInt();
        }
        catch(Exception Error)
        {
            System.out.println("Please check your infos!!");
        }

                }
    public Musteri(int sayi)
    {
        sifre =sayi;
    }
    public List urunSiparisi(String urunAd)
    {
      geciciSepet.clear();
      for(String kategori : main.getKategoriler())  
      {
          main.getHashTablosu().get(kategori).mUrunBul(main.getHashTablosu().get(kategori).getRoot(), urunAd);          
      }
      if(geciciSepet.size()!=0)
          return geciciSepet;
      else
          return null;
    }

    public List urunSiparisi(double fiyat1,double fiyat2)
    {
        geciciSepet.clear();
        for(String kategori : main.getKategoriler())  
        {
            main.getHashTablosu().get(kategori).mFiyataGoreUrunBul(main.getHashTablosu().get(kategori).getRoot(), fiyat1, fiyat2);
        }
        if(geciciSepet.size()!=0)
            return geciciSepet;
        else
            return null;
    }
    public List urunSiparisi(String urunAd,double fiyat1,double fiyat2)
    {
        geciciSepet.clear();
        for(String kategori : main.getKategoriler())  
        {
            main.getHashTablosu().get(kategori).mAdveFiyataGoreUrunBul(main.getHashTablosu().get(kategori).getRoot(), urunAd, fiyat1, fiyat2);
        }
        if(geciciSepet.size()!=0)
            return geciciSepet;
        else
            return null;
    }
    public void sepeteUrunEkle(List urun)
    {
        getSepet().add(urun);
    }
    public void sepetUrunCikar(List urun)
    {
        getSepet().remove(urun);
    }
    public void sepetiOnayla()    // Herhangi bir yerde Hata cikarsa bunu ve tree deki metotlarini kontrol et!!
    {
        oncekiSepetler.add(getSepet());
        for(List urunler : getSepet())
        {   
            for(int i =0 ;i<main.getKategoriler().size();i++)
            {
                String kategori = main.getKategoriler().get(i);
                main.getHashTablosu().get(kategori).mUrunBulveGuncelle(main.getHashTablosu().get(kategori).getRoot(), urunler);
                
            }
            Personel.setGelirler((double)urunler.get(4));
            Personel.setGiderler((double)urunler.get(3));
            
        }
        System.out.println("Basket :"+sepet);
        getSepet().clear();
    }
    public void enUcuzNurunSatinAl()   /// satin alma islemi yapilmadi guncelle
    {
        Scanner klavye = new Scanner(System.in);
        System.out.println("Category name for cheapest N product :");
        String kategori = klavye.next();
        System.out.println("How many product are you gonna order for this category ? : ");
        int N = klavye.nextInt();
        getQ1().clear();
        main.getHashTablosu().get(kategori).mUrunFiyatlariniEkle(main.getHashTablosu().get(kategori).getRoot());;
        System.out.println("queue:");
        System.out.println(q1);
        List<Double> NAdetFiyat = new ArrayList<Double>();
        for(int i=0;i<N;i++)
        {
            NAdetFiyat.add(getQ1().remove());
        }    
        geciciSepet.clear();
        main.getHashTablosu().get(kategori).mfiyataGoreUrunEkle(main.getHashTablosu().get(kategori).getRoot(), NAdetFiyat,N);
        System.out.println("process successful.");
        System.out.println("Purchased Products:");
        for(List a :geciciSepet)   
        {
            System.out.print(" "+a.get(0)+" "+a.get(1)+ " ");
        }
        System.out.println("");
        sepeteUrunEkle(geciciSepet); 
        sepetiOnayla();
    }
    public void Bilgiler(String kategori)
    {
        System.out.println("------PreOrder----");
        main.getHashTablosu().get(kategori).preOrder(main.getHashTablosu().get(kategori).getRoot());
        System.out.println();
        System.out.println("------InOrder-----");
        main.getHashTablosu().get(kategori).inOrder(main.getHashTablosu().get(kategori).getRoot());
        System.out.println();
        System.out.println("------PostOrder----");
        main.getHashTablosu().get(kategori).postOrder(main.getHashTablosu().get(kategori).getRoot());
        System.out.println();
        main.getHashTablosu().get(kategori).bilgiler(main.getHashTablosu().get(kategori).getRoot(),-1);
        System.out.println("Depth of Tree :"+main.getHashTablosu().get(kategori).getDerinlik());
        System.out.println("Number of elements in every level in tree :");
        for(int i=0;i<=main.getHashTablosu().get(kategori).getDerinlik();i++)
        {
            int [] duzey = main.getHashTablosu().get(kategori).getDuzeydekiElemanSayisi();
            System.out.println(i+".level : number of elements :"+duzey[i]);
        }
    }
    /**
     * @return the adSoyad
     */
    public String getAdSoyad() {
        return adSoyad;
    }

    /**
     * @param adSoyad the adSoyad to set
     */
    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

  

    /**
     * @return the cinsiyet
     */
    public String getCinsiyet() {
        return cinsiyet;
    }

    /**
     * @param cinsiyet the cinsiyet to set
     */
    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    /**
     * @return the medeniDurum
     */
    public String getMedeniDurum() {
        return medeniDurum;
    }

    /**
     * @param medeniDurum the medeniDurum to set
     */
    public void setMedeniDurum(String medeniDurum) {
        this.medeniDurum = medeniDurum;
    }

    /**
     * @return the sifre
     */
    public int getSifre() {
        return sifre;
    }

    /**
     * @param sifre the sifre to set
     */
    public void setSifre(int sifre) {
        this.sifre = sifre;
    }

    /**
     * @return the sepet
     */
   

    /**
     * @return the geciciSepet
     */
    public static List<List> getGeciciSepet() {
        return geciciSepet;
    }

    /**
     * @param aGeciciSepet the geciciSepet to set
     */
    public static void setGeciciSepet(List<List> aGeciciSepet) {
        geciciSepet.add(aGeciciSepet);
    }

    /**
     * @return the q1
     */
  

    /**
     * @return the oncekiSepetler
     */
    public static List<List> getOncekiSepetler() {
        return oncekiSepetler;
    }

    /**
     * @param aOncekiSepetler the oncekiSepetler to set
     */
    public static void setOncekiSepetler(List<List> aOncekiSepetler) {
        oncekiSepetler = aOncekiSepetler;
    }

    /**
     * @return the maas
     */
    public double getMaas() {
        return maas;
    }

    /**
     * @param maas the maas to set
     */
    public void setMaas(double maas) {
        this.maas = maas;
    }

    /**
     * @return the sepet
     */
    public static List<List> getSepet() {
        return sepet;
    }

    /**
     * @param aSepet the sepet to set
     */
    public static void setSepet(List<List> aSepet) {
        sepet = aSepet;
    }

    /**
     * @return the q1
     */
    public static PriorityQueue<Double> getQ1() {
        return q1;
    }

    /**
     * @param aQ1 the q1 to set
     */
    public static void setQ1(PriorityQueue<Double> aQ1) {
        q1 = aQ1;
    }

  

  
  

   
}
