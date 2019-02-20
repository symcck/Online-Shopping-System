/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ORCUN
 */
public class TreeNode {

   
    private TreeNode rightChild;
    private TreeNode leftChild ;
    private TreeNode root;
    private String ad ;
    private List<List> urunBilgileri = new ArrayList<List>();
    
    public TreeNode()
    {
        this.root=null;
        this.ad =null;        
    }
    public TreeNode(String urunAd)
    {
        this.root=null;
        this.ad= urunAd;
    }
  
    
    public void yazdir()
    {
        System.out.print(""+this.ad+"-");
        for(Object a : getUrunBilgileri())
        {
            System.out.print(" "+a);
        }
        System.out.println();
    }
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
     * @return the ad
     */
    public String getAd() {
        return ad;
    }

    /**
     * @param ad the ad to set
     */
    public void setAd(String ad) {
        this.ad = ad;
    }

    /**
     * @return the urunBilgileri
     */
    public List<List> getUrunBilgileri() {
        return urunBilgileri;
    }

    /**
     * @param urunBilgileri the urunBilgileri to set
     */
    public void setUrunBilgileri(List<List> urunBilgileri) {
        this.urunBilgileri.add(urunBilgileri);
    }
    public void urunBilgisiSil(List urunBilgileri)
    {
        this.urunBilgileri.remove(urunBilgileri);
    }
   
    

}
