/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ORCUN
 */
public class main {

  // dip not : kategori listesi ve hash table kategoriler bazen icleri bos halde kalabiliyorlar ona cozum bul problem yaratabilir.
    private static Hashtable<String,List>  anahtarKelimeler = new Hashtable<String,List>();  // 2.hash table
    private static Hashtable<String,tree> hashTablosu = new Hashtable<String,tree> ();       // 1. hash table
    private static List<String> kategoriler = new ArrayList<String>();
    private static Hashtable<Integer,Musteri> kayitliMusteriler = new Hashtable<Integer,Musteri>();
    
      public static void main(String[] args) {        
        Scanner klavye = new Scanner(System.in);
               
        // System.out.print("Urunlerin bulundugu dosya adini .txt seklinde giriniz :");
        //String dosyaAdi = klavye.next();
        dosyaIslemleri dosya = new dosyaIslemleri("urunler.txt");
        int satirSayisi = dosya.satirSayisi();
        int i =0 ;
        Scanner okunan = dosya.oku();
        while(i<satirSayisi)
        {   
            List urunBilgileri = new ArrayList();
            String bilgiler = dosya.satirOku(okunan);
            StringTokenizer str = new StringTokenizer(bilgiler,",");
            String kategori = str.nextToken();
            String urunAd   = str.nextToken();
            //Burdan itibaren urunbilgileri listesine ekleniyor
            String marka    = str.nextToken();
            String model    = str.nextToken();
            int miktar      = Integer.parseInt(str.nextToken());
            double maliyet     = Double.parseDouble(str.nextToken());
            double satisFiyati = Double.parseDouble(str.nextToken());
            String urunAciklamasi = str.nextToken();
            
            urunBilgileri.add(marka); urunBilgileri.add(model); urunBilgileri.add(miktar);                                    // Urunle ilgili kategori haric butun bilgiler
            urunBilgileri.add(maliyet);  urunBilgileri.add(satisFiyati); urunBilgileri.add(urunAciklamasi);                   // urun bilgileri listesinde tutulur
            AnahtarHashTableOlustur(urunBilgileri);    //2.hashTable olusturuluyor.
            if(getHashTablosu().containsKey(kategori))                                                    // Dosyadan okunan kategori hastable da var mı ?
            {
                getHashTablosu().get(kategori).NodeEkle(urunAd,urunBilgileri);
                i++;
            }
            else
            {
                tree agac = new tree(kategori);
                getHashTablosu().put(kategori, agac);
                getHashTablosu().get(kategori).NodeEkle(urunAd,urunBilgileri); 
                getKategoriler().add(kategori);
                i++;
            }
                      
        } // end of while
        girisEkrani();
    }// end of main
    public static Musteri MusteriUstMenu(Musteri m1)
    {
                        Scanner klavye = new Scanner(System.in);
                        System.out.println("Welcome "+m1.getAdSoyad());
                        System.out.println("1-Product search");
                        System.out.println("2-Product information by category");
                        System.out.println("3-Buy cheapest N product");
                        System.out.println("4-Basket approve / buying process");
                        System.out.println("5-Product removal from basket ");
                        System.out.println("6-Exit");
                        int iGiris = klavye.nextInt();
                        switch(iGiris)
                        {
                            case 1:
                                m1=musteriAltMenu(m1);
                                break;
                            case 2 :
                                System.out.println("Enter the category name that you want to search");
                                System.out.println("category :");
                                String aKategori = klavye.next();
                                m1.Bilgiler(aKategori);
                                m1 =MusteriUstMenu(m1);
                                break;    
                            case 3:
                                m1.enUcuzNurunSatinAl();
                                m1 =MusteriUstMenu(m1);
                                break;
                            case 4:
                                m1.sepetiOnayla();
                                m1 =MusteriUstMenu(m1);
                                break;
                            case 5:
                                System.out.println("Please enter the products that you want to remove from the basket.(quit: -1)");
                                int i =0;
                                try
                                {
                                    for(List urun :m1.getSepet())
                                    {
                                        i++;
                                        System.out.println(i+"- "+urun.get(0)+" "+urun.get(1));
                                    }
                                    int secim2= klavye.nextInt();
                                    if(secim2==-1)
                                        break;
                                    else
                                        m1.sepetUrunCikar(m1.getSepet().get(secim2));
                                    System.out.println("product removal from basket successful");
                                    girisEkrani();
                                    
                                }catch(Exception Err)
                                {
                                    System.out.println("Error!");   
                                    girisEkrani();
                                }
                                m1 =MusteriUstMenu(m1);
                                break;
                            case 6 : 
                                girisEkrani();
                                break;     
                        }     
        return m1;
    }
    public static void girisEkrani()  // burada 2. hash table secenegi yok doldur.
    {
        Musteri m2 = new Musteri(247256);
        Personel p1 = new Personel();
        kayitliMusteriler.put(247256, m2);
        Scanner klavye = new Scanner(System.in);               
        System.out.println("Welcome to program.");
        System.out.println("1-Customer sign in");    
        System.out.println("2-Staff sign in"); 
        System.out.println("3-Exit");
        int giris = klavye.nextInt();
        switch(giris)
        {
            case 1:
                System.out.println("1-New customer register"
                                  + "2-Customer log in");
                int mGiris = klavye.nextInt();
                Musteri m1 ;
                switch(mGiris)
                {                    
                    case 1 :
                        m1 = new Musteri();
                        kayitliMusteriler.put(m1.getSifre(), m1);   // buraya hata kontrolu ekle
                        girisEkrani();
                        break;
                    case 2 :
                        System.out.println("Enter your password");
                        int sifre = klavye.nextInt();
                        m1 = kayitliMusteriler.get(sifre);
                        m1 =MusteriUstMenu(m1);
                        break;
                }// end of musteri paneli
                break;
            case 2:
                
                System.out.println("Enter your password.");
                int psifre=klavye.nextInt();
                while(psifre!=p1.getPersonelSifre())
                {
                    System.out.print("Nice try ! Try again (quit : -1) :");
                    psifre=klavye.nextInt();
                    System.out.println();
                    if(psifre==-1)
                        break;
                }
                if(psifre==-1)
                    break;
                p1=PersonelAltMenu(p1);
                break;
            case 3 :
                return;
                   
            default:
                System.out.println("Incorrect entry !!! ");
                girisEkrani();
                break;
              
        }
        
    }
    public static Personel PersonelAltMenu(Personel p1)
    {
        Scanner klavye = new Scanner(System.in);
        System.out.println("What do you want to do:");
        System.out.println("1-Adding new product from name or category");
        System.out.println("2-Product searching or deleting, from name or brand");
        System.out.println("3-Update the product infos "); 
        System.out.println("4-Calculate income,expenses and profit for company");
        System.out.println("5-Change the password");
        System.out.println("6-login screen");
        int secim3 = klavye.nextInt();
        switch(secim3)
        {
            case 1:
                p1.urunEkle();
                p1=PersonelAltMenu(p1);
                break;
            case 2:
                System.out.println("Name :");
                String ad = klavye.next();
                System.out.println("Brand :");
                String marka = klavye.next();
                System.out.println("Model :");
                String model = klavye.next();
                System.out.println("1-Deleting  ");
                System.out.println("2-Searching");
                int hangisi = klavye.nextInt();
                if(hangisi==1)
                {
                    List bulunanUrun=p1.urunBul(ad, marka, model);
                    System.out.println("Product found:");
                    System.out.println(bulunanUrun);
                }
                else if(hangisi==2)
                    p1.urunSil(ad, marka, model);
                else
                {
                    System.out.println("Error !");
                }
                p1=PersonelAltMenu(p1);
                break;
            case 3:
                p1.urunBilgileriDegistir();
                p1=PersonelAltMenu(p1);
                break;
            case 4:
                Personel.sirketHarcamalari();
                p1=PersonelAltMenu(p1);
            case 5:
                System.out.print("Current password:");
                int sfre = klavye.nextInt();
                System.out.println();
                if(sfre==Personel.getPersonelSifre())
                {
                    System.out.print("New Password :");
                    sfre = klavye.nextInt();
                    Personel.setPersonelSifre(sfre);
                }
                else
                {
                    System.out.println("Incorrect Password!!");
                    System.out.println("");
                                                       
                }
                PersonelAltMenu(p1);
                break;
            case 6:
                girisEkrani();
                break;
            
                    
        }
        return p1;
    }
    public static Musteri musteriAltMenu(Musteri m1)
    {
        Scanner klavye = new Scanner(System.in);
            System.out.println("1-Product search from it's name");
            System.out.println("2-Products between a-b price");
            System.out.println("3-Search product by its name and price range ");
            System.out.println("4-Product search from entered keyword");
            System.out.println("5-Upper menu");
            System.out.println("6-Exit");
            int secim = klavye.nextInt();
            List<List> urunler = new ArrayList<List>();
            int i =0;
            switch(secim)
            {
                case 1:
                    
                    System.out.println("Product name: ");
                    String urunAd = klavye.next();                                               
                    urunler =m1.urunSiparisi(urunAd);
                    System.out.println("Please select the items that you want to add to the basket.");
                    i=0;
                    for(List urun : urunler)
                    { 
                        i++;
                        System.out.println(i+"-"+urun.get(0)+" "+urun.get(1)); 
                    }
                    int scm = klavye.nextInt();
                    m1.sepeteUrunEkle(urunler.get(scm-1));
                    System.out.println(urunler.get(scm-1).get(0)+" "+urunler.get(scm-1).get(1)+" adding successfully done!");
                    m1=musteriAltMenu(m1);
                    break;
                case 2:
                    System.out.print("min price :");
                    double min = klavye.nextInt();
                    System.out.println("");
                    System.out.print("max price :");
                    double max = klavye.nextInt();
                    urunler = m1.urunSiparisi(min, max);
                    System.out.println("Choose the products that you want to add to the basket.");
                    i=0;
                    for(List urun : urunler)
                    {
                        i++;
                        System.out.println(i+"-"+urun.get(0)+" "+urun.get(1)); 
                    }                    
                    scm = klavye.nextInt();
                    m1.sepeteUrunEkle(urunler.get(scm-1));
                    System.out.println(urunler.get(scm-1).get(0)+" "+urunler.get(scm-1).get(1)+" adding successfully done!");
                     m1=musteriAltMenu(m1);                      
                    break;
                case 3:
                    System.out.print("Product name :");
                    urunAd = klavye.next();
                    System.out.println("");
                    System.out.print("min price:");
                    min = klavye.nextDouble();
                    System.out.println("");
                    System.out.print("max price :");
                    max = klavye.nextDouble();
                    urunler = m1.urunSiparisi(urunAd,min, max);
                    System.out.println("Choose the product that you want to add to the basket.");
                    i=0;
                    for(List urun : urunler)
                    {
                        i++;
                        System.out.println(i+"-"+urun.get(0)+" "+urun.get(1)); 
                    }                    
                    scm = klavye.nextInt();
                    m1.sepeteUrunEkle(urunler.get(scm-1));
                    System.out.println(urunler.get(scm-1).get(0)+" "+urunler.get(scm-1).get(1)+" adding successfully done!");
                    m1=musteriAltMenu(m1);
                    break;
                case 4:
                    System.out.print("Keyword :");
                    String kelime = klavye.next();
                    System.out.println();
                    AnahtarHashTableDolas(kelime);
                    musteriAltMenu(m1);
                    break;
                case 5:  
                    MusteriUstMenu(m1);
                    break;
                case 6:
                    break;
                default :
                    System.out.println("Error! Try Again!");
                    m1=musteriAltMenu(m1);
                    // giris ekranini ikiye bol ve burda ust menuye cik
                    break;
            } // end of switch
        return m1;
        }// end of musteri alt menu   
    public static void AnahtarHashTableOlustur(List urunBilgileri)     // 2.hash table
    {
        StringTokenizer str = new StringTokenizer((String) urunBilgileri.get(5),"-");
        getAnahtarKelimeler().clear();
        while(str.hasMoreElements())
        {   
            
            String gecici =str.nextToken();
            if(getAnahtarKelimeler().containsKey(gecici))                            //Anahtar kelimeler global olarak tanımlanmıştır.Bizim
            {                                                                   //2.hash table ımızı temsil ediyor.        
                getAnahtarKelimeler().get(gecici).add(urunBilgileri);                // gecerli kelime varsa urunu listeye ekler.
            }
            else
            {
                List anahtarSozcukler = new ArrayList();
                anahtarSozcukler.add(urunBilgileri);                            // Liste icinde Liste oluşturuyoruz.
                getAnahtarKelimeler().put(gecici, anahtarSozcukler);                 // yoksa yenisini oluşturur.
            }
             
        }       
    }
    public static void AnahtarHashTableDolas(String kelime)
    {
        if(getAnahtarKelimeler().containsKey(kelime))
        {
            int size =getAnahtarKelimeler().get(kelime).size();        
            for(int i =0 ; i<size ; i++)
            {
                System.out.println(getAnahtarKelimeler().get(kelime).get(i));
            }
        }
    }
      /**
     * @return the anahtarKelimeler
     */
   
    public static Hashtable<String,List> getAnahtarKelimeler() {
        return anahtarKelimeler;
    }

    /**
     * @param aAnahtarKelimeler the anahtarKelimeler to set
     */
    public static void setAnahtarKelimeler(Hashtable<String,List> aAnahtarKelimeler) {
        anahtarKelimeler = aAnahtarKelimeler;
    }

    /**
     * @return the hashTablosu
     */
    public static Hashtable<String,tree> getHashTablosu() {
        return hashTablosu;
    }

    /**
     * @param aHashTablosu the hashTablosu to set
     */
    public static void setHashTablosu(Hashtable<String,tree> aHashTablosu) {
        hashTablosu = aHashTablosu;
    }

    /**
     * @return the kategoriler
     */
    public static List<String> getKategoriler() {
        return kategoriler;
    }

    /**
     * @param aKategoriler the kategoriler to set
     */
    public static void setKategoriler(List<String> aKategoriler) {
        kategoriler = aKategoriler;
    }

    /**
     * @return the kayitliMusteriler
     */
    public static Hashtable<Integer,Musteri> getKayitliMusteriler() {
        return kayitliMusteriler;
    }

    /**
     * @param aKayitliMusteriler the kayitliMusteriler to set
     */
    public static void setKayitliMusteriler(Hashtable<Integer,Musteri> aKayitliMusteriler) {
        kayitliMusteriler = aKayitliMusteriler;
    }

   
}
