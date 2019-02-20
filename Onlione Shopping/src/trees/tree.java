/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ORCUN
 */
public class tree {
    private TreeNode rightChild;
    private TreeNode leftChild ;
    private TreeNode root;
    private String kategori ;
    private int derinlik ;
    private int [] duzeydekiElemanSayisi =new int[1000];
    public tree(String kategori)
    {
        this.root=null;
        this.kategori =kategori;  
        this.derinlik=0;
        
    }
  
    public void NodeEkle(String ad,List yeniUrun)
    {
        TreeNode newNode = new TreeNode(ad);
        
        if(getRoot()==null)
        {            
            newNode.setUrunBilgileri(yeniUrun);           
            setRoot(newNode);
        }
        else
        {
            TreeNode current = getRoot();
            TreeNode parent ;
            while(true)
            {
                parent=current;
                if(ad.compareTo(current.getAd())<0)
                {
                    current=current.getLeftChild();
                    if(current==null)
                    {                        
                        newNode.setUrunBilgileri(yeniUrun);
                        parent.setLeftChild(newNode);
                        return;        
                    }
                }           
                else if (ad.compareTo(current.getAd())>0)
                {
                    current=current.getRightChild();
                    if(current==null)
                    {                    
                        newNode.setUrunBilgileri(yeniUrun);
                        parent.setRightChild(newNode);
                        return;
                    }
                   
                }
                else
                {
                    current.setUrunBilgileri(yeniUrun);
                   
                    break;
                }
                        
            }
        }
                      
    }
    public void urunBul(TreeNode localRoot,String urunAd,String marka,String model)
    {
        if(localRoot!=null)
        {
            if(localRoot.getAd().compareTo(urunAd)>0)
            {
                urunBul(localRoot.getLeftChild(),urunAd,marka,model);
            }
            if(localRoot.getAd().compareTo(urunAd)<0)
            {                
                urunBul(localRoot.getRightChild(),urunAd,marka,model);
            }
            if(localRoot.getAd().compareTo(urunAd)==0)
            {
                
                for(List urunler :localRoot.getUrunBilgileri())
                {
                    if((urunler.get(0).equals(marka)) && (urunler.get(1).equals(model)))
                    {                       
                       Personel.getBulunanUrun().add(urunler);  
                       break;
                    }
                }
            }
        }       
    }
    
    public void urunBilgileriDegistir(TreeNode localRoot,String urunAd,String marka,String model)  //(Hashtable icinden hangi kategoride oldugunu bulmayi yapmadim tamamla)
    {
          if(localRoot!=null)
        {
            if(localRoot.getAd().compareTo(urunAd)>0)
            {
                urunBilgileriDegistir(localRoot.getLeftChild(),urunAd,marka,model);
            }
            if(localRoot.getAd().compareTo(urunAd)<0)
            {      
                urunBilgileriDegistir(localRoot.getRightChild(),urunAd,marka,model);
            }
            if(localRoot.getAd().compareTo(urunAd)==0)
            {   
                for(List urunler :localRoot.getUrunBilgileri())
                {
                    if((urunler.get(0).equals(marka)) && (urunler.get(1).equals(model)))
                    {    
                        Scanner klavye = new Scanner(System.in);
                        System.out.println("Yeni urun bilgilerini giriniz (degistirmeyeceginiz bilgiler icin d, sayilar icin -1 giriniz.  ):");
                        System.out.print("kategori  :");
                        System.out.println();
                        String kategori2 = klavye.next();
                        if(kategori2.equals("d"))                       
                            kategori2 =this.kategori;
                    /*    
                        System.out.print(" urun adi  :");
                        System.out.println();                            bunu yapmak icin node silmek lazım node silmeyi yaz ondan sonra don
                        String urunAd2 = klavye.next();
                        if(urunAd2=="d")
                            urunAd2 = localRoot.getAd();  */
                        
                        
                        System.out.print(" marka :");
                        System.out.println();
                        String marka2  = klavye.next();
                        if(marka2.equals("d"))                      
                            marka2 =(String)urunler.get(0);
                        
                        System.out.print(" model :");
                        System.out.println();
                        String model2       = klavye.next();
                        if(model2.equals("d"))
                            model2 = (String)urunler.get(1);
                        
                        System.out.print(" miktar :");
                        System.out.println();
                        int miktar2         = klavye.nextInt();
                        if(miktar2==-1)
                            miktar2=(int)urunler.get(2);
                        
                        System.out.print(" maliyet :");
                        System.out.println();
                        double maliyet2     = klavye.nextDouble();
                        if(maliyet2==-1)
                            maliyet2=(double)urunler.get(3);
                        
                        System.out.print(" satis fiyati  :");
                        System.out.println();
                        double satisFiyati2 = klavye.nextDouble();
                        if(satisFiyati2==-1)
                            satisFiyati2=(double)urunler.get(4);
                        
                        System.out.print(" urun aciklamasi :");
                        System.out.println();
                        String urunAciklamasi2 =klavye.next();
                        if(urunAciklamasi2.equals("d"))
                            urunAciklamasi2=(String)urunler.get(5);
                        
                        List urunBilgileri = new ArrayList();
                                    
                        urunBilgileri.add(marka2); urunBilgileri.add(model2); urunBilgileri.add(miktar2);           // Urunle ilgili kategori haric butun bilgiler
                        urunBilgileri.add(maliyet2);  urunBilgileri.add(satisFiyati2); urunBilgileri.add(urunAciklamasi2);
                        
                        if(kategori2.compareTo(this.kategori)!=0) //kategori degisiyorsa      //DİKKAT!! buranın asagisindaki bilgileri okumuyor gibi.Bunlari mainin icinde yapmayi dene!
                        {
                            if(main.getHashTablosu().containsKey(kategori2)) // bilgisi degistirilen kategori mevcut mu ?                               
                            {
                                main.getHashTablosu().get(kategori2).NodeEkle(urunAd, urunBilgileri);
                                
                            }
                            else    // yeni kategori yaratiliyor
                            {
                                tree agac = new tree(kategori2);                          
                                main.getHashTablosu().put(kategori2, agac);              
                                main.getHashTablosu().get(kategori2).NodeEkle(urunAd,urunBilgileri);
                                main.getKategoriler().add(kategori2);
                                
                            }
                            localRoot.getUrunBilgileri().remove(urunler);         
                            
                          
                        }
                        else //kategori degisikligi yapilmiyorsa sadece node icinde liste degisikligi yeterli olacaktir.
                        {
                             localRoot.getUrunBilgileri().remove(urunler);
                             localRoot.setUrunBilgileri(urunBilgileri);
                                
                        }
                        System.out.println("urun bilgileri degismistir. Yeni bilgiler :");
                        System.out.println(urunBilgileri);
                        break;
                        
                    }
                    
                }
            }
        }
    }
    public void urunSil(TreeNode localRoot,String urunAd,String marka,String model)    //(calismiyor ??
    {
        if(localRoot!=null)
        {
            if(localRoot.getAd().compareTo(urunAd)>0)
            {
                urunSil(localRoot.getLeftChild(),urunAd,marka,model);                
            }
            if(localRoot.getAd().compareTo(urunAd)<0)
            {                
                urunSil(localRoot.getRightChild(),urunAd,marka,model);
            }
            if(localRoot.getAd().compareTo(urunAd)==0)
            {
                for(List urunler :localRoot.getUrunBilgileri())
                {
                    if((urunler.get(0).equals(marka)) && (urunler.get(1).equals(model)))
                    {      
                       localRoot.urunBilgisiSil(urunler);
                       System.out.println(urunler+" silindi");
                       break;
                    }
                }
            }
        }       
    }
    public void preOrder(TreeNode LocalRoot)    // once kök sonra sol alt agac üstten baslanarak
    {
        if(LocalRoot!=null)
        {
            LocalRoot.yazdir();
            preOrder(LocalRoot.getLeftChild());
            preOrder(LocalRoot.getRightChild());
        }
    }
    public void postOrder(TreeNode LocalRoot)      // once sol alt agac en alttan baslanarak sonra sag agac en alttan baslanarak sonra kök
    {
        if(LocalRoot!=null)
        {
            postOrder(LocalRoot.getLeftChild());            
            postOrder(LocalRoot.getRightChild());
            LocalRoot.yazdir();
        }
    }
    public void inOrder(TreeNode LocalRoot)      // alt agac - kök -sag agac en alttan degil en soldan baslar. 
    {                                            // orjinal sırayla dolasir.
        if(LocalRoot!=null)
        {
            inOrder(LocalRoot.getLeftChild());
            LocalRoot.yazdir();
            inOrder(LocalRoot.getRightChild());
        }
    }

    public void mUrunBul(TreeNode localRoot,String urunAd)
    {
        if(localRoot!=null)
        {
            if(localRoot.getAd().compareTo(urunAd)>0)
            {
                mUrunBul(localRoot.getLeftChild(),urunAd);
            }
            if(localRoot.getAd().compareTo(urunAd)<0)
            {                
                mUrunBul(localRoot.getRightChild(),urunAd);
            }
            if(localRoot.getAd().compareTo(urunAd)==0)
            {
                if(localRoot.getUrunBilgileri().size()>1)
                {
                    for(List urun : localRoot.getUrunBilgileri())
                    {
                        Musteri.setGeciciSepet(urun);
                    }
                }
                else
                    Musteri.setGeciciSepet(localRoot.getUrunBilgileri());
            }
        }       
    }
    public void FiyataGoreEkle(TreeNode localRoot,double fiyat1,double fiyat2) 
    {
        if(localRoot!=null)
        {
            for(List urunler : localRoot.getUrunBilgileri())
            {
                if((double)urunler.get(4)>=fiyat1 && (double)urunler.get(4)<=fiyat2)
                    Musteri.getGeciciSepet().add(urunler);
            }
        }
    }
    public void AdaVeFiyataGoreEkle(TreeNode localRoot,String urunAd,double fiyat1,double fiyat2)
    {
        if(localRoot!=null)
        {
            for(List urunler : localRoot.getUrunBilgileri())
            {
                if( (localRoot.getAd().equals(urunAd) ) && (double)urunler.get(4)>=fiyat1 && (double)urunler.get(4)<=fiyat2)
                    Musteri.getGeciciSepet().add(urunler);
            }
        }
    }
    public void mFiyataGoreUrunBul(TreeNode localRoot,double fiyat1,double fiyat2)
    {
        if(localRoot!=null)
        {
            mFiyataGoreUrunBul(localRoot.getLeftChild(),fiyat1,fiyat2);
            FiyataGoreEkle(localRoot,fiyat1,fiyat2);
            mFiyataGoreUrunBul(localRoot.getRightChild(),fiyat1,fiyat2);
        }
    }
    public void mAdveFiyataGoreUrunBul(TreeNode localRoot,String urunAd,double fiyat1,double fiyat2)
    {
        if(localRoot!=null)
        {
            mAdveFiyataGoreUrunBul(localRoot.getLeftChild(),urunAd,fiyat1,fiyat2);
            AdaVeFiyataGoreEkle(localRoot,urunAd,fiyat1,fiyat2);
            mAdveFiyataGoreUrunBul(localRoot.getRightChild(),urunAd,fiyat1,fiyat2);
        }
        
    }
    public void mUrunFiyatlariniEkle(TreeNode localRoot)
    {
        if(localRoot!=null)
        {
            mUrunFiyatlariniEkle(localRoot.getLeftChild());
            urunFiyatlariniEkle(localRoot);
            mUrunFiyatlariniEkle(localRoot.getRightChild());
        }
    }
    public void urunFiyatlariniEkle(TreeNode localRoot)
    {       
        if(localRoot!=null)
        {
            for(List urunler : localRoot.getUrunBilgileri())
            {   
                Musteri.getQ1().add((double)urunler.get(4));

            }
        }
    }
    public void fiyataGoreEkle(TreeNode localRoot,List<Double> fiyatListesi,int urunSayisi)
    {
        if(localRoot!=null)
        {
            for(List urunler : localRoot.getUrunBilgileri())
            {   
                if(fiyatListesi.contains( (Double)urunler.get(4) ) && Musteri.getGeciciSepet().size()<urunSayisi )
                    Musteri.getGeciciSepet().add(urunler);
            }
        }
        
    }
    public void mfiyataGoreUrunEkle(TreeNode localRoot,List<Double> fiyatListesi,int urunSayisi)
    {
        if(localRoot!=null)
        {
            mfiyataGoreUrunEkle(localRoot.getLeftChild(),fiyatListesi,urunSayisi);
            fiyataGoreEkle(localRoot,fiyatListesi,urunSayisi);
            mfiyataGoreUrunEkle(localRoot.getRightChild(),fiyatListesi,urunSayisi);
        }
    }
    public void mUrunBulveGuncelle(TreeNode localRoot,List urun)
    {
        if(localRoot!=null)
        {
            mUrunBulveGuncelle(localRoot.getLeftChild(), urun);          // agacta siparis edilen urun bulunuyor ve stoktaki sayisi 1 azaltiliyor
            urunBulveGuncelle(localRoot,urun);
            mUrunBulveGuncelle(localRoot.getRightChild(), urun);
        }
    }
    public void urunBulveGuncelle(TreeNode localRoot,List urun)
    {
        if(localRoot !=null)
        {
            for (List urunler : localRoot.getUrunBilgileri())
            {
                if(urunler.equals(urun))
                {
                    int miktar = (int)urunler.get(2)-1;             // Siparis edilen urun 1 azaltılıyor.
                    urunler.set(2, (Object)miktar);
                }             
            }
        }
    }
    public void bilgiler(TreeNode localRoot,int duzey)
    {
        if(localRoot!=null)
        {
            duzey++;            
            if(duzey>derinlik)
                derinlik=duzey;
            duzeydekiElemanSayisi[duzey] +=localRoot.getUrunBilgileri().size();
            
            bilgiler(localRoot.getLeftChild(),duzey);
            bilgiler(localRoot.getRightChild(),duzey);
        }
    }
    
    public void yazdir()
    {
        System.out.print(""+this.kategori+"-");
    }
     /**
     * @return the rightChild
     */
    public TreeNode getRightChild() {
        return rightChild;
    }

    /**
     * @param rightChild the rightChild to set
     */
    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * @return the leftChild
     */
    public TreeNode getLeftChild() {
        return leftChild;
    }

    /**
     * @param leftChild the leftChild to set
     */
    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return the root
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * @return the kategori
     */
    public String getKategori() {
        return kategori;
    }

    /**
     * @param kategori the kategori to set
     */
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    /**
     * @return the derinlik
     */
    public int getDerinlik() {
        return derinlik;
    }

    /**
     * @param derinlik the derinlik to set
     */
    public void setDerinlik(int derinlik) {
        this.derinlik = derinlik;
    }

    /**
     * @return the duzeydekiElemanSayisi
     */
    public int[] getDuzeydekiElemanSayisi() {
        return duzeydekiElemanSayisi;
    }

    /**
     * @param duzeydekiElemanSayisi the duzeydekiElemanSayisi to set
     */
    public void setDuzeydekiElemanSayisi(int[] duzeydekiElemanSayisi) {
        this.duzeydekiElemanSayisi = duzeydekiElemanSayisi;
    }

   

  
    
    
}
